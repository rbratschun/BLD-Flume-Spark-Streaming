package at.mse.bld.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.flume.FlumeUtils;
import org.apache.spark.streaming.flume.SparkFlumeEvent;
import org.apache.spark.streaming.api.java.*;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Preparing FlumeStream ...");
        TimeUnit.SECONDS.sleep(10);
        SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("FlumeStreamingApp"); // default spark streaming setup
        JavaStreamingContext jsc = new JavaStreamingContext(sparkConf, new Duration(10_000)); // initialize with polling interval
        JavaReceiverInputDStream<SparkFlumeEvent> flumeStream = FlumeUtils.createStream(jsc, "0.0.0.0", 18020); // listen to port 18020
        flumeStream.persist(StorageLevel.MEMORY_ONLY()); // required !

        flumeStream
                .map(WebSiteEvent::map) // map from avro to websiteevent
                .map(ProductStatistics::transform) // transform to productstatistics
                .window(new Duration(60*1000), new Duration(10_000)) // create 1 minute windowDStream
                .foreachRDD(Main::processRdd); // process RDD

        jsc.start();
        jsc.awaitTermination();
    }

    private static void processRdd(JavaRDD<ProductStatistics> statistics, Time time) {
        if(statistics.count() > 0) {
            statistics
                .groupBy(x -> x.getProduct_id()) // group by product
                .map(ProductStatistics::aggregate) // aggregate sum / count by product and time window
                .foreachPartition(new Transaction()); // insert or replace each group
        }
    }
}

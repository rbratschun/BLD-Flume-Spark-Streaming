package at.rbratschun.mse.bld.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.flume.FlumeUtils;
import org.apache.spark.streaming.flume.SparkFlumeEvent;
import org.apache.spark.streaming.api.java.*;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Preparing FlumeStream ...");
        SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("FlumeStreamingApp");
        JavaStreamingContext jsc = new JavaStreamingContext(sparkConf, new Duration(30_000)); // initialize with polling interval
        JavaReceiverInputDStream<SparkFlumeEvent> flumeStream = FlumeUtils.createStream(jsc, "0.0.0.0", 18020); // listen to port 18020
        flumeStream.persist(StorageLevel.MEMORY_ONLY()); // required !

        flumeStream
                .map(WebSiteEvent::map) // map from avro to websiteevent
                .map(ProductStatistics::transform) // transform to productstatistics
                .window(new Duration(60*1000), new Duration(60 * 1000)) // create 1 minute windowDStream
                .foreachRDD(Main::processRdd); // process RDD

        jsc.start();
        jsc.awaitTermination();
    }

    private static void processRdd(JavaRDD<ProductStatistics> statistics, Time time) {
        System.out.println("We have " + statistics.count() + " events");
        if(statistics.count() > 0) {
            statistics
                .groupBy(x -> x.getProduct_id())
                .map(ProductStatistics::aggregate)
                .foreachPartition(new Transaction());
        }
    }
}

package at.mse.bld.spark;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.spark.streaming.flume.SparkFlumeEvent;
import java.io.Serializable;

public class WebSiteEvent implements Serializable {
    private long customer_id;
    private long product_id;
    private long timestamp;
    private float revenue;
    private String type;

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WebSiteEvent() { }

    public WebSiteEvent(long customer_id, long product_id, long timestamp, float revenue, String type) {

        this.customer_id = customer_id;
        this.product_id = product_id;
        this.timestamp = timestamp;
        this.revenue = revenue;
        this.type = type;
    }

    @Override
    public String toString() {
        return "customer_id: " + customer_id + " product: " + product_id + " timestamp: " + timestamp + " revenue: " + revenue;
    }

    public static WebSiteEvent map(SparkFlumeEvent event)throws Exception {
        return new ObjectMapper().readValue(event.event().getBody().array(), WebSiteEvent.class);
    }
}

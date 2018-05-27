package at.rbratschun.mse.bld.generator;

public class WebSiteEvent {
    private long customer_id;
    private long product_id;
    private long timestamp;
    private float revenue;
    private String type;

    public WebSiteEvent(String type, long customer_id, long product_id, long timestamp, float revenue) {
        this.type = type;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.timestamp = timestamp;
        this.revenue = revenue;
    }

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
}

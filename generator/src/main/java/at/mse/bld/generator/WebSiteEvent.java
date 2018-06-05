package at.mse.bld.generator;

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

    public WebSiteEvent(WebSiteEventBuilder builder) {
        this.type = builder.type;
        this.customer_id = builder.customer_id;
        this.product_id = builder.product_id;
        this.timestamp = builder.timestamp;
        this.revenue = builder.revenue;
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

    public static class WebSiteEventBuilder {
        private long customer_id;
        private long product_id;
        private long timestamp;
        private float revenue;
        private String type;

        public WebSiteEvent build() {
            return new WebSiteEvent(this);
        }

        public WebSiteEventBuilder type(String type) {
            this.type = type;
            return this;
        }

        public WebSiteEventBuilder customer(long customer_id) {
            this.customer_id = customer_id;
            return this;
        }

        public WebSiteEventBuilder product(long product) {
            this.product_id = product;
            return this;
        }

        public WebSiteEventBuilder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public WebSiteEventBuilder revenue(float revenue) {
            this.revenue = revenue;
            return this;

    }
    }
}

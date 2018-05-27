package at.rbratschun.mse.bld.monitor;

import java.sql.ResultSet;

public class Entry {
    private long id;
    private String minute;
    private long product_id;
    private double revenue;
    private int purchases;
    private int views;

    public Entry() {}
    public Entry(long id, String minute, long product_id, double revenue, int purchases, int views) {
        this.id = id;
        this.minute = minute;
        this.product_id = product_id;
        this.revenue = revenue;
        this.purchases = purchases;
        this.views = views;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public int getPurchases() {
        return purchases;
    }

    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public static Entry transform(ResultSet resultSet) throws Exception{
        return new Entry(
            resultSet.getLong(1),
            resultSet.getString(2),
            resultSet.getLong(3),
            resultSet.getDouble(4),
            resultSet.getInt(5),
            resultSet.getInt(6)
        );
    }

    @Override
    public String toString() {
        return "{ product_id: " + getProduct_id() + ", minute: " + getMinute() + ", revenue: "+ getRevenue() + ", views: "+getViews() + ", purchases: " + getPurchases() + " }";
    }
}

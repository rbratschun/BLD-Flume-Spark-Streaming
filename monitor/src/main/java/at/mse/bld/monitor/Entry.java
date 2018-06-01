package at.mse.bld.monitor;

import java.sql.ResultSet;

public class Entry {
    private String minute;
    private long product_id;
    private double revenue;
    private int purchases;
    private int views;

    private Entry (long product_id, String minute, double revenue, int purchases, int views) {
        this.product_id = product_id;
        this.minute = minute;
        this.revenue = revenue;
        this.purchases = purchases;
        this.views = views;
    }

    static Entry transform(ResultSet resultSet) throws Exception{
        return new Entry(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getDouble(3),
                resultSet.getInt(4),
                resultSet.getInt(5)
        );
    }

    @Override
    public String toString() {
        return "{ product_id: " + product_id + ", minute: " + minute + ", revenue: "+ revenue + ", views: "+ views + ", purchases: " + purchases + " }";
    }
}

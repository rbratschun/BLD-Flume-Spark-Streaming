package at.rbratschun.mse.bld.monitor;

import java.sql.ResultSet;

public class Entry {
    private String minute;
    private long product_id;
    private double revenue;
    private int purchases;
    private int views;

    private Entry (String minute, long product_id, double revenue, int purchases, int views) {
        this.minute = minute;
        this.product_id = product_id;
        this.revenue = revenue;
        this.purchases = purchases;
        this.views = views;
    }

    static Entry transform(ResultSet resultSet) throws Exception{
        return new Entry(
            resultSet.getString(2),
            resultSet.getLong(3),
            resultSet.getDouble(4),
            resultSet.getInt(5),
            resultSet.getInt(6)
        );
    }

    @Override
    public String toString() {
        return "{ product_id: " + product_id + ", minute: " + minute + ", revenue: "+ revenue + ", views: "+ views + ", purchases: " + purchases + " }";
    }
}

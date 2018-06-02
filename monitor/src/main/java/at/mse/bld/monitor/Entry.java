package at.mse.bld.monitor;

import java.sql.ResultSet;

public class Entry {
    private long product_id;
    private double revenue;
    private int purchases;
    private int views;

    private Entry (long product_id, double revenue, int purchases, int views) {
        this.product_id = product_id;
        this.revenue = revenue;
        this.purchases = purchases;
        this.views = views;
    }

    static Entry transform(ResultSet resultSet) throws Exception{
        return new Entry(
                resultSet.getLong(1),
                resultSet.getDouble(2),
                resultSet.getInt(3),
                resultSet.getInt(4)
        );
    }

    @Override
    public String toString() {
        return String.format("%10s | %10s | %10s | %10s", product_id, revenue, views, purchases);
    }
}

package at.mse.bld.spark;

import org.apache.spark.api.java.function.VoidFunction;

import java.sql.*;
import java.util.Iterator;

public class Transaction implements VoidFunction<Iterator<ProductStatistics>> {

    private static Connection conn;
    private static final String InsertSQL = "INSERT INTO product_statistics (product_id, revenue, views, purchases, minute) VALUES (?, ?, ?, ?, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i'))";

    private static String getConnString() {
        String host = "mysql";
        String port = "3306";
        return "jdbc:mysql://"+host + ":" + port + "/statistics?user=docker&password=docker";
    }

    private static void connect() throws SQLException {
        conn = DriverManager.getConnection(getConnString());
    }

    public void call(Iterator<ProductStatistics> it) {
        try {
            connect();
            while(it.hasNext()) {
                save(it.next());
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    private void save(ProductStatistics p) {
        try {
            PreparedStatement stmt = conn.prepareStatement(InsertSQL);
            stmt.setLong(1, p.getProduct_id());
            stmt.setDouble(2, p.getRevenue());
            stmt.setInt(3, p.getViews());
            stmt.setInt(4, p.getPurchases());
            stmt.executeUpdate();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}

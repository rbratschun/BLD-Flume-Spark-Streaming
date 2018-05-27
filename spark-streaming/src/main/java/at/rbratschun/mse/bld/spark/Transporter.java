package at.rbratschun.mse.bld.spark;

import org.apache.spark.api.java.function.VoidFunction;

import java.sql.*;
import java.util.Iterator;

public class Transporter implements VoidFunction<Iterator<ProductStatistics>> {

    private Connection  conn;

    private static String getConnString() {
        String host = "mysql";
        String port = "3306";
        return "jdbc:mysql://"+host + ":" + port + "/statistics?user=docker&password=docker";
    }

    public void call(Iterator<ProductStatistics> it) {
        try {
            conn = DriverManager.getConnection(getConnString());
            while(it.hasNext()) {
                save(it.next());
            }
            conn.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    private void save(ProductStatistics p) {
        System.out.println(p);
        String sql = "INSERT INTO product_statistics (product_id, revenue, views, purchases, minute) VALUES (?, ?, ?, ?, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i'))";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, p.getProduct_id());
            stmt.setDouble(2, p.getRevenue());
            stmt.setInt(3, p.getViews());
            stmt.setInt(4, p.getPurchases());
            int result = stmt.executeUpdate();
            System.out.println("Result: "+result);
        }
        catch(Exception e) {
            System.out.println(e);
        }

    }

    public static boolean startUp() {
        try {
            Connection conn = DriverManager.getConnection(getConnString());
            String sql =
                    "CREATE TABLE  IF NOT EXISTS product_statistics ( "+
                            "        id int(11) NOT NULL AUTO_INCREMENT, "+
                            "minute varchar(20) NOT NULL, "+
                            "product_id int(11) NOT NULL, "+
                            "revenue DECIMAL(10,2) NOT NULL, "+
                            "purchases int(11) NOT NULL, "+
                            "views int(11) NOT NULL, "+
                            "timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, "+
                            "PRIMARY KEY (id) " +
                            " );";
            Statement stmt = conn.createStatement();
            boolean result = stmt.execute(sql);
            if(conn != null && !conn.isClosed())
                conn.close();
            return result;
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }
}

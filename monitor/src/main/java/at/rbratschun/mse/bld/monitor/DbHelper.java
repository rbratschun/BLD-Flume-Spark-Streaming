package at.rbratschun.mse.bld.monitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class DbHelper {

    private static Connection conn;
    public static void getRecentEntries() throws Exception {
        conn = DriverManager.getConnection("jdbc:mysql://mysql:3306/statistics?user=docker&password=docker");
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM product_statistics WHERE timestamp BETWEEN timestamp(DATE_SUB(NOW(), INTERVAL 5 MINUTE)) AND timestamp(NOW())");

        while(resultSet.next()) {
            System.out.println(Entry.transform(resultSet));
        }
        if(conn != null ) conn.close();
    }
}

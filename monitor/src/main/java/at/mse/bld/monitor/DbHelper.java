package at.mse.bld.monitor;

import java.sql.*;

 class DbHelper {

    private static String getConnString() {
            String host = "mysql";
            String port = "3306";
            return "jdbc:mysql://"+host + ":" + port + "/statistics?user=docker&password=docker";
    }

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(getConnString());
    }

    static void getRecentEntries() throws Exception {
        Connection conn = connect();
        System.out.println("*************** TOP TEN PRODUCTS *****************");
        System.out.format("%10s | %10s | %10s | %10s%n", "Product", "Revenue", "Views", "Purchases");
        ResultSet resultSet = conn.createStatement().executeQuery(DbStatements.RecentEntries);
        while(resultSet.next()) {
            System.out.println(Entry.transform(resultSet));
        }
        conn.close();
        System.out.println("**************************************************");
    }

    static void initialize() {
        // create table if it does not exist
        try (Connection conn = connect() ){
            conn.createStatement().execute(DbStatements.CreateTable);
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
}

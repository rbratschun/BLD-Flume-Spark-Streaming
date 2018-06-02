package at.mse.bld.monitor;

public class DbStatements {
    // TOP 10 products of the last 5 minutes
    public static String RecentEntries = "SELECT product_id, SUM(revenue) AS revenue, SUM(views) AS views, SUM(purchases) AS purchases FROM product_statistics " +
            "WHERE timestamp BETWEEN timestamp(DATE_SUB(NOW(), INTERVAL 5 MINUTE)) AND timestamp(NOW()) " +
            "GROUP BY product_id " +
            "ORDER BY SUM(revenue) DESC LIMIT 10";

    public static String CreateTable = "CREATE TABLE IF NOT EXISTS product_statistics ( "+
                                            "product_id int(11) NOT NULL, "+
                                            "minute varchar(20) NOT NULL, "+
                                            "revenue DECIMAL(10,2) NOT NULL, "+
                                            "purchases int(11) NOT NULL, "+
                                            "views int(11) NOT NULL, "+
                                            "timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, "+
                                            "PRIMARY KEY (product_id, minute) " +
                                        " );";
}

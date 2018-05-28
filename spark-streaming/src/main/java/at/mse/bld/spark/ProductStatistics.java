package at.mse.bld.spark;

import scala.Tuple2;
import java.io.Serializable;

public class ProductStatistics implements Serializable {
    private long product_id;
    private double revenue;
    private int views;
    private int purchases;

    public ProductStatistics() {}


    public ProductStatistics(long product_id, double revenue, int views, int purchases) {
        this.product_id = product_id;
        this.revenue = revenue;
        this.views = views;
        this.purchases = purchases;
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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getPurchases() {
        return purchases;
    }

    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }

    public static ProductStatistics transform(WebSiteEvent event) {
        return new ProductStatistics(
                event.getProduct_id(),
                event.getRevenue(),
                event.getType().equals("view") ? 1 : 0,
                event.getType().equals("view") ? 0 : 1
        );
    }

    @Override
    public String toString() {
        return "{ product_id: " + getProduct_id() + ", revenue: " + getRevenue() + ", views: " + getViews() + ", purchases: " + getPurchases() + " }";
    }

    public static ProductStatistics aggregate(Tuple2<Long, Iterable<ProductStatistics>> group) {
        ProductStatistics temp = new ProductStatistics(group._1, 0, 0,0);
        for(ProductStatistics p: group._2) {
            temp.setRevenue(temp.getRevenue()+p.getRevenue());
            temp.setViews(temp.getViews()+p.getViews());
            temp.setPurchases(temp.getPurchases()+p.getPurchases());
        }
        return temp;
    }
}

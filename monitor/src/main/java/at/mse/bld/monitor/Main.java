package at.mse.bld.monitor;


import java.util.concurrent.TimeUnit;

public class Main {
    public static void main (String args[]) throws Exception {

        System.out.println("Starting monitor, waiting for mysql to complete");
        TimeUnit.SECONDS.sleep(45);
        DbHelper.initialize();

        while(true) {
            try {
                DbHelper.getRecentEntries();
            }
            catch(Exception e) {
                System.out.println(e);
            }
            finally {
                TimeUnit.SECONDS.sleep(5);
            }
        }
    }
}

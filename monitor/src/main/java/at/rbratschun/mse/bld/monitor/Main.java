package at.rbratschun.mse.bld.monitor;

public class Main {
    public static void main (String args[]) throws Exception {

        Thread.sleep(15_000);

        while(true) {
            try {
                DbHelper.getRecentEntries();
            }
            catch(Exception e) {
                System.out.println(e);
            }
            finally {
                Thread.sleep(5_000);
            }
        }
    }
}

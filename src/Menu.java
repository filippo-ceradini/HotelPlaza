public class Menu {

    //makes 1 second wait
    public static void wait1s(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
package Lab3;

public class Producer extends Thread {
    private final Buffer buf;
    private int cnt;

    public Producer(Buffer buf) {
        this.buf = buf;
        this.cnt = 0;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Meal meal = new Meal("Meal" + cnt++);
            System.out.println("Producing new meal: " + meal);
            buf.put(meal);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("I have to end my producing activity)");
    }
}

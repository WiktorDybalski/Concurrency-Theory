package Lab3;

public class Consumer extends Thread {
    private final Buffer buf;

    public Consumer(Buffer buf) {
        this.buf = buf;
    }
    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            System.out.println("Consuming new Meal: " + buf.get());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("I have to end my consuming activity)");
    }
}

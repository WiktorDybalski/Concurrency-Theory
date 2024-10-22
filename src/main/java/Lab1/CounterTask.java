package Lab1;

public class CounterTask implements Runnable {
    private final Counter counter;
    private final boolean isIncrement;

    public CounterTask(Counter counter, boolean isIncrement) {
        this.counter = counter;
        this.isIncrement = isIncrement;
    }

    @Override
    public void run() {
        try {
            if (isIncrement) {
                counter.increment();
            } else {
                counter.decrement();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

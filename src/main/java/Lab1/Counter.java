package Lab1;

public class Counter {
    private int counter = 0;
    private final MySemaphore semaphore = new MySemaphore();

    public void increment() throws InterruptedException {
        semaphore.P();
        try {
            counter++;
            System.out.println(Thread.currentThread().getName() + " zwiększa licznik: " + counter);
        } finally {
            semaphore.V();
        }
    }

    public void decrement() throws InterruptedException {
        semaphore.P();
        try {
            counter--;
            System.out.println(Thread.currentThread().getName() + " zmniejsza licznik: " + counter);
        } finally {
            semaphore.V();
        }
    }

    public synchronized int getCounter() {
        return counter;
    }
}

package Lab1;

public class MySemaphore {
    private boolean stan = true;
    private int czeka = 0;

    public synchronized void P() {
        czeka++;
        if (!stan) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        stan = false;
        czeka--;
    }

    public synchronized void V() {
        stan = true;
        notify();
    }
}

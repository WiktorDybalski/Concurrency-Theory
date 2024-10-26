package Lab4.t4;

import java.util.concurrent.Semaphore;

class Fork {
    private final Semaphore semaphore;

    public Fork() {
        this.semaphore = new Semaphore(1);
    }

    public boolean tryPickUp() throws InterruptedException {
        return semaphore.tryAcquire();
    }

    public void putDown() {
        semaphore.release();
    }
}


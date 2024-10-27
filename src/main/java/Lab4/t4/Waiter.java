package Lab4.t4;

import Lab4.Configurator;

import java.util.concurrent.Semaphore;

public class Waiter {
    private final Semaphore semaphore;

    public Waiter() {
        this.semaphore = new Semaphore(Configurator.getNumberOfPeople());
    }

    public boolean isPossibleToEat() {
        return semaphore.tryAcquire();
    }

    public void doneEating() {
        semaphore.release();
    }
}

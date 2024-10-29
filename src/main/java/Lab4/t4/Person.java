package Lab4.t4;

import Lab4.ResultPrinter;

import java.time.Duration;
import java.time.Instant;

class Person extends Thread {

    private final String name;
    private final Fork leftFork;
    private final Fork rightFork;
    private final Waiter waiter;
    private final ResultPrinter rp;


    public Person(Fork leftFork, Fork rightFork, String name, Waiter waiter, ResultPrinter rp) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = name;
        this.waiter = waiter;
        this.rp = rp;
    }

    private void think() throws InterruptedException {
        System.out.println(name + " is thinking.");
        Thread.sleep((int) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        System.out.println(name + " is eating.");
        Thread.sleep((int) (Math.random() * 1000));
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                think();
                Instant start = Instant.now();

                waiter.acquirePermissionToEat();
                System.out.println(name + ": " + "Waiter approved");
                if (leftFork.tryPickUp()) {
                    if (rightFork.tryPickUp()) {
                        eat();
                        Instant end = Instant.now();
                        Duration timeElapsed = Duration.between(start, end);
                        rp.addTime(timeElapsed.toMillis());

                        leftFork.putDown();
                        rightFork.putDown();
                        waiter.doneEating();
                        System.out.println(name + ": Time taken: " + timeElapsed.toMillis() + " milliseconds");
                    } else {
                        System.out.println(name + ": " + "Unfortunately, It was taken, Picking down right fork");
                        leftFork.putDown();
                    }
                } else {
                    System.out.println(name + ": Left fork was taken, waiting to try again");
                }
            }
        } catch (
                InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

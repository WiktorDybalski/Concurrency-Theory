package Lab4.t2;

import Lab4.ResultPrinter;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Semaphore;

class Person extends Thread {

    private final String name;
    private final Fork leftFork;
    private final Fork rightFork;
    private final ResultPrinter rp;


    public Person(Fork leftFork, Fork rightFork, String name, ResultPrinter rp) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = name;
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
                if (leftFork.tryPickUp()) {
                    System.out.println(name + ": " + "Picking up left fork");
                    if (rightFork.tryPickUp()) {
                        System.out.println(name + ": " + "Picking up right fork");
                        eat();

                        Instant end = Instant.now();
                        Duration timeElapsed = Duration.between(start, end);
                        rp.addTime(timeElapsed.toMillis());

                        System.out.println(name + ": " + "Picking down right fork");
                        rightFork.putDown();
                    } else {
                        System.out.println(name + ": " + "Unfortunately, It was taken, Picking down right fork");
                        leftFork.putDown();
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


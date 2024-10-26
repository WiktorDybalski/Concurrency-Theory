package Lab4.t4;

import java.time.Duration;
import java.time.Instant;

class Person extends Thread {

    private final String name;
    private final Fork leftFork;
    private final Fork rightFork;
    private final Waiter waiter;


    public Person(Fork leftFork, Fork rightFork, String name, Waiter waiter) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = name;
        this.waiter = waiter;
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
            while (true) {
                think();
                Instant start = Instant.now();

                if (waiter.isPossibleToEat()) {
                    System.out.println(name + ": " + "Waiter approved");
                    if (leftFork.tryPickUp()) {
                        if (rightFork.tryPickUp()) {
                            eat();
                            leftFork.putDown();
                            rightFork.putDown();
                            waiter.doneEating();

                            Instant end = Instant.now();
                            Duration timeElapsed = Duration.between(start, end);
                            System.out.println(name + ": Time taken: " + timeElapsed.toMillis() + " milliseconds");
                        } else {
                            System.out.println(name + ": " + "Unfortunately, It was taken, Picking down right fork");
                            leftFork.putDown();
                        }
                    } else {
                        System.out.println(name + ": Left fork was taken, waiting to try again");
                    }
                } else {
                    System.out.println(name + ": Maximum number of people reached");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


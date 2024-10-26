package Lab4.t2;

import java.util.concurrent.Semaphore;

class Person extends Thread {

    private final String name;
    private final Fork leftFork;
    private final Fork rightFork;


    public Person(Fork leftFork, Fork rightFork, String name) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = name;
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
                if (leftFork.tryPickUp()) {
                    System.out.println(name + ": " + "Picking up left fork");
                    if (rightFork.tryPickUp()) {
                        System.out.println(name + ": " + "Picking up right fork");
                        eat();
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


package Lab4.t1;


import Lab4.ResultPrinter;

import java.time.Duration;
import java.time.Instant;

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

    private void doAction(String action) throws InterruptedException {
        System.out.println(System.nanoTime() + ":::::: " + name + ": " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                doAction("I'm thinking");
                Instant start = Instant.now();
                synchronized (leftFork) {
                    doAction("Picking up left fork");
                    synchronized (rightFork) {
                        doAction("Picking up right fork");
                        doAction("I'm eating");
                        Instant end = Instant.now();
                        Duration timeElapsed = Duration.between(start, end);
                        rp.addTime(timeElapsed.toMillis());
                        doAction("Picking down right fork");
                    }
                    doAction("Picking down left fork");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


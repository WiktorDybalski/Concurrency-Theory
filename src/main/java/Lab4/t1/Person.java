package Lab4.t1;


class Person extends Thread {

    private final String name;
    private final Fork leftFork;
    private final Fork rightFork;


    public Person(Fork leftFork, Fork rightFork, String name) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = name;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(System.nanoTime() + ":::::: " + name + ": " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        try {
            while (true) {
                doAction("I'm thinking");
                synchronized (leftFork) {
                    doAction("Picking up left fork");
                    synchronized (rightFork) {
                        doAction("Picking up right fork");
                        doAction("I'm eating");
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


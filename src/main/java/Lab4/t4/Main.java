package Lab4.t4;


import static java.lang.Thread.sleep;

class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread[] people = new Thread[5];
        Fork[] forks = new Fork[5];
        Waiter waiter = new Waiter();

        for(int i = 0; i < 5; i++) {
            forks[i] = new Fork();
        }

        for(int i = 0; i < 5; i++) {
            people[i] = new Person(forks[i], forks[(i + 1) % 5], "Person" + i, waiter);
        }

        for(int i = 0; i < 5; i++) {
            people[i].start();
            sleep(50);
        }
    }
}

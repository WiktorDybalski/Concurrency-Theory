package Lab4.t3;

import static java.lang.Thread.sleep;

class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread[] people = new Thread[5];
        Fork[] forks = new Fork[5];

        for(int i = 0; i < 5; i++) {
            forks[i] = new Fork();
        }

        for(int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                people[i] = new Person(forks[(i + 1) % 5], forks[i], "Person" + i);
            }
             else {
                people[i] = new Person(forks[i], forks[(i + 1) % 5], "Person" + i);
            }
        }
        for(int i = 0; i < 5; i++) {
            people[i].start();
            sleep(50);
        }
    }
}

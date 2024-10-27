package Lab4.t3;

import Lab4.Configurator;
import Lab4.ResultPrinter;

import static java.lang.Thread.sleep;

public class AsymMain {
    public double run() {
        int N = Configurator.getNumberOfPeople();
        ResultPrinter rp = new ResultPrinter();
        Thread[] people = new Thread[N];
        Fork[] forks = new Fork[N];

        for (int i = 0; i < N; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                people[i] = new Person(forks[(i + 1) % N], forks[i], "Person" + i, rp);
            } else {
                people[i] = new Person(forks[i], forks[(i + 1) % N], "Person" + i, rp);
            }
        }
        System.out.println(" ");
        System.out.println("-------------------------");
        System.out.println("Asymmetric Simulation");
        System.out.println("-------------------------");

        try {
            for (int i = 0; i < N; i++) {
                people[i].start();
                sleep(50);
            }
            for (int i = 0; i < N; i++) {
                people[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rp.printResultAndReturn();
    }
}

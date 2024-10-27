package Lab4.t4;


import Lab4.Configurator;
import Lab4.ResultPrinter;

import static java.lang.Thread.sleep;

public class WaiterMain {
    public double run() {
        int N = Configurator.getNumberOfPeople();
        Thread[] people = new Thread[N];
        Fork[] forks = new Fork[N];
        Waiter waiter = new Waiter();
        ResultPrinter rp = new ResultPrinter();

        for (int i = 0; i < N; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < N; i++) {
            people[i] = new Person(forks[i], forks[(i + 1) % N], "Person" + i, waiter, rp);
        }

        System.out.println(" ");
        System.out.println("-------------------------");
        System.out.println("Waiter Simulation");
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

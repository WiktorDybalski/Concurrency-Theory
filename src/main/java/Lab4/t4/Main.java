package Lab4.t4;


import Lab4.ResultPrinter;

import static java.lang.Thread.sleep;

class Main {
    public static void main(String[] args) throws InterruptedException {
        int N = 8;
        Thread[] people = new Thread[N];
        Fork[] forks = new Fork[N];
        Waiter waiter = new Waiter(N - 1);
        ResultPrinter rp = new ResultPrinter(N);

        for (int j = 0; j < 5; j++) {
            System.out.println(" ");
            System.out.println("Starting " + j + " iteration of program");
            System.out.println(" ");
            for(int i = 0; i < N; i++) {
                forks[i] = new Fork();
            }

            for(int i = 0; i < N; i++) {
                people[i] = new Person(forks[i], forks[(i + 1) % N], "Person" + i, waiter, rp);
            }

            for(int i = 0; i < N; i++) {
                people[i].start();
                sleep(50);
            }

            for (int i = 0; i < N; i++) {
                people[i].join();
            }
        }
        rp.printResult();
    }
}

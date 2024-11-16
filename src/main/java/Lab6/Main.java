package Lab6;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int N = 5;
        Random random = new Random();
        List list = new List();
        ThreadFactory threadFactory = new ThreadFactory(list);
        Thread[] threads = new Thread[N];

        for (int i = 0; i < N; i++) {
            int x = random.nextInt(3);
            int value = random.nextInt(100);
            switch (x) {
                case 0 -> threads[i] = threadFactory.createAddThread(value);
                case 1 -> threads[i] = threadFactory.createContainsThread(value);
                case 2 -> threads[i] = threadFactory.createRemoveThread(value);
            }
        }
        for (Thread thread : threads) {
            if (thread != null) {
                thread.start();
            }
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


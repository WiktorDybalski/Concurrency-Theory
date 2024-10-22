package Lab1;

public class Main {

    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread[] incrementThreads = new Thread[10000];
        Thread[] decrementThreads = new Thread[10000];

        for(int i = 0; i < incrementThreads.length; i++) {
            incrementThreads[i] = new Thread(new CounterTask(counter, true));
            decrementThreads[i] = new Thread(new CounterTask(counter, false));
        }

        for(int i = 0; i < incrementThreads.length; i++) {
            incrementThreads[i].start();
            decrementThreads[i].start();
        }

        for(int i = 0; i < incrementThreads.length; i++) {
            try {
                incrementThreads[i].join();
                decrementThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(counter.getCounter());
    }
}
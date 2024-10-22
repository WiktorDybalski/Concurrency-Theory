package Lab3;

public class Main {
    public static void main(String[] args) {
        int queueSize = 10;
        Buffer buffer = new Buffer(queueSize);

        Thread[] producers = new Thread[4];
        Thread[] consumers = new Thread[2];

        for (int i = 0; i < 4; i++) {
            producers[i] = new Producer(buffer);
            producers[i].start();
        }

        for (int i = 0; i < 2; i++) {
            consumers[i] = new Consumer(buffer);
            consumers[i].start();
        }

        for (int i = 0; i < 3; i++) {
            try {
                producers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 2; i++) {
            try {
                consumers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Ending work.");
    }
}

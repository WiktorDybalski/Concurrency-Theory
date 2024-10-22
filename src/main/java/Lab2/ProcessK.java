package Lab2;

public class ProcessK extends Thread {
    @Override
    public void run() {
        while (true) {
            if (!Variables.work) {
                Variables.decrementCounter();
                System.out.println("Proces K: work jest false, zmniejszam counter: " + Variables.getCounter());
            } else {
                System.out.println("Proces K: work jest true, czekam");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package Lab2;

public class ProcessP extends Thread {
    @Override
    public void run() {
        while (true) {
            Variables.setWork(false);
            Variables.incrementCounter();
            System.out.println("Proces P: zmieniam work na false, zwiekszam counter: " + Variables.getCounter());

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Variables.setWork(true);
            System.out.println("Proces P: zmieniam work na true");

            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

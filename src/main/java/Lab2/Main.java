package Lab2;

public class Main {
    public static void main(String[] args) {
        Thread p = new ProcessP();
        Thread k = new ProcessK();
        p.start();
        k.start();
        
        monitorStates();
    }

    private static void monitorStates() {
        new Thread(() -> {
           try {
                while (true) {
                    System.out.println("\n----------------------------");
                    System.out.printf("| %-20s | %-10s |\n", "work", "counter");
                    System.out.println("------------------------------");
                    System.out.printf("| %-20s | %-10d |\n", Variables.getWork(), Variables.getCounter());
                    System.out.println("------------------------------");
                    Thread.sleep(3000);
                }
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
        }).start();
    }
}
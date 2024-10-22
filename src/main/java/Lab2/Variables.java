package Lab2;

public class Variables {
    public static boolean work = true;
    public static int counter = 0;

    public static void incrementCounter() {
        counter++;
    }

    public static void decrementCounter() {
        counter--;
    }

    public synchronized static int getCounter() {
        return counter;
    }

    public synchronized static int getWork() {
        return counter;
    }

    public static void setWork(boolean work) {
        Variables.work = work;
    }
}

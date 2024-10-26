package Lab4;

import java.util.ArrayList;
import java.util.List;

public class ResultPrinter {
    private List<Long> waitingTimes;
    private final int N;

    public ResultPrinter(int N) {
        this.waitingTimes = new ArrayList<>();
        this.N = N;
    }

    public void addTime(long time) {
        waitingTimes.add(time);
    }

    public void printResult() {
        int averageWaitingTime = 0;

        for (Long waitingTime : waitingTimes) {
            averageWaitingTime += waitingTime;
        }
        long result = averageWaitingTime / waitingTimes.size();
        System.out.println(" ");
        System.out.println("-------------------------------------------");
        System.out.println("AverageTime for " + N + " people: " + result + "ms");
        System.out.println("-------------------------------------------");
    }
}

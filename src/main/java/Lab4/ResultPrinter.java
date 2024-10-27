package Lab4;

import java.util.ArrayList;
import java.util.List;

public class ResultPrinter {
    private List<Long> waitingTimes;
    private final int N;

    public ResultPrinter() {
        this.waitingTimes = new ArrayList<>();
        this.N = Configurator.numberOfPeople;
    }

    public void addTime(long time) {
        waitingTimes.add(time);
    }

    public long printResultAndReturn() {
        int averageWaitingTime = 0;

        for (Long waitingTime : waitingTimes) {
            averageWaitingTime += waitingTime;
        }
        long result = averageWaitingTime / waitingTimes.size();
        System.out.println(" ");
        System.out.println("-------------------------------------------");
        System.out.println("AverageTime for " + N + " people: " + result + "ms");
        System.out.println("-------------------------------------------");
        return result;
    }
}

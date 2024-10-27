package Lab4;

import Lab4.t1.NaiveMain;
import Lab4.t2.BetterMain;
import Lab4.t3.AsymMain;
import Lab4.t4.WaiterMain;

public class Main {
    public static void main(String[] args) {
        NaiveMain nm = new NaiveMain();
        BetterMain bm = new BetterMain();
        AsymMain am = new AsymMain();
        WaiterMain wm = new WaiterMain();

        double[] results = {nm.run(), bm.run(), am.run(), wm.run()};
        String[] labels = {"Naive", "Better", "Asymmetric", "Waiter"};

        PlotPrinter.createBoxPlot(results, labels);
    }
}

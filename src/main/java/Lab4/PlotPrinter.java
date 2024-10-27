package Lab4;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;


public class PlotPrinter {
    public static void createBoxPlot(double[] results, String[] labels) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < results.length; i++) {
            dataset.addValue(results[i], "Series", labels[i]);
        }


        JFreeChart barChart = ChartFactory.createBarChart(
                "Bar Chart Example",
                "Category",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );


        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setRangeGridlinePaint(java.awt.Color.BLACK);


        JFrame frame = new JFrame("Bar Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(barChart));
        frame.pack();
        frame.setVisible(true);
    }
}

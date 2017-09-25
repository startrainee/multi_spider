import java.io.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartUtilities;

public class BarChart3D {
    public static void main(String[] args) throws Exception {
        final String fait = "FAIT";
        final String audi = "AUDI";
        final String ford = "FORD";
        final String speed = "Speed";
        final String popular = "Popular";
        final String mailage = "Mailage";
        final String userrating = "User Rating";
        final String safty = "safty";
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(200, fait, speed);
        dataset.addValue(10, audi, speed);
        dataset.addValue(40, ford, speed);

        JFreeChart barChart = ChartFactory.createBarChart3D(
                "Neusoft News Counts",
                "Datas",
                "Numbers",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        int width = 1200; /* Width of the image */
        int height = 900; /* Height of the image */
        File barChart3D = new File("barChart3D.jpeg");
        ChartUtilities.saveChartAsJPEG(barChart3D, barChart, width, height);
    }
}
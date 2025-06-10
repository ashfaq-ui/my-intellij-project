import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

class SimpleBarChart extends JFrame {

    public SimpleBarChart(int aPlus, int a, int bPlus, int  b, int cPlus, int c, int fail, String moduleName) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(aPlus, "Student A", "A+");
        dataset.addValue(a, "Student A", "A");
        dataset.addValue(bPlus, "Student A", "B+");
        dataset.addValue(b, "Student A", "B");
        dataset.addValue(cPlus, "Student A", "C+");
        dataset.addValue(c, "Student A", "C");
        dataset.addValue(fail, "Student A", "Fail");


        JFreeChart chart = ChartFactory.createBarChart(
                "Summary of The "+ moduleName,    // chart title
                "Marks Range",           // domain axis label
                "NO of Students",        // range axis label
                dataset              // data
        );

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);

        setSize(800, 600);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

        });
    }
}
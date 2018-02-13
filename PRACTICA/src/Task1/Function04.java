//Програма Function04 створена для побудови графіка функції "Парабола Нейля"
// 20.05.2017,Уткіна Олександра,КНІТ-1

package Task1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import acm.io.IODialog;

public class Function04 extends JFrame {

	private static final long serialVersionUID = 1L;
	public static double step;
	public static double x_min;
	public static double x_max;
	public static double x;
	public static double y;
	public static double a;

	public static void main(String[] args) {

		IODialog dialog = new IODialog();
		x_min = dialog.readDouble("Enter a x_min (x_min>=0): ");
		if (x_min < 0) {
			JOptionPane.showInputDialog("ERROR: x_min must be > 0 ");
			System.exit(0);
		}
		x_max = dialog.readDouble("Enter a x_max: ");
		if (x_max < x_min) {
			JOptionPane.showInputDialog("ERROR: x_max must be > x_min ");
			System.exit(0);
		}
		a = dialog.readDouble("Enter an a - coefficient ");

		step = dialog.readDouble("Enter a step: ");

		create();
	}

	public static void create() {
		JFrame frame = new JFrame(); // створюємо каркас вікна
		frame.setTitle("Графік Парабола Нейля"); // заголовок вікна
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// вказуємо дію, що
		// має
		// виконатися,
		// коли
		// користувач
		// натисне
		// "хрестик"
		// (припинити
		// роботу)

		// створюємо 1 ряд даних
		XYSeries series = new XYSeries("Графік Парабола Нейля+", false, true);
		XYSeries series1 = new XYSeries("Графік Парабола Нейля-", false, true);
		// додаємо точки на графіку
		while (x_min <= x_max) {

			x = x_min;

			y = (double) (a * (Math.sqrt(Math.pow(x_min, 3))));
			x_min += step;
			double r = y * (-1);
			series.add(x, y);
			series1.add(x, r);
		}

		// зразу ж додаємо ряд в набір даних, створює набір даних і заповнє його
		// серію
		XYSeriesCollection data = new XYSeriesCollection(series);
		data.addSeries(series1);
		//XYSeriesCollection data1 = new XYSeriesCollection(series1);

		// будуємо діаграму на основі отриманих даних
		final JFreeChart chart = ChartFactory.createXYLineChart("Графік Парабола Нейля", // Заголовок
				// діаграми
				"X", // назва осі X
				"Y", // назва осі Y
				data, // дані
				PlotOrientation.VERTICAL, // орієнтація
				true, // включити легенду
				true, // підказки
				false // urls
		);

		

		// створюємо панель для графіка
		final ChartPanel chartPanel = new ChartPanel(chart);

		// final ChartPanel chartPanel1 = new ChartPanel(chart1);

		// встановлюємо розмір діаграми
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		// chartPanel1.setPreferredSize(new java.awt.Dimension(500, 270));

		// додаємо панель на створений нами фрейм
		frame.setContentPane(chartPanel);
		// frame.setContentPane(chartPanel1);

		// підганяємо розміри фрейму
		frame.pack();
		// робимо усе видимим
		frame.setVisible(true);

		try { // збереження
			// екземпляр OutputStream і надаємо ім’я, під яким буде
			// збережене зображення
			OutputStream stream = new FileOutputStream("graphic1.jpg");// Для
			// створення об'єкта FileOutputStream використовується конструктор,
			// що приймає як параметр шлях до файлу для запису.

			ChartUtilities.writeChartAsPNG(stream, chart, 600, 600);
			// збереження// діаграми//
			// у
			// форматі// PNG// розміром// 600х600
		} catch (IOException e) {
			System.out.println("Error" + e.getMessage());
			e.printStackTrace();
		}
	}
}

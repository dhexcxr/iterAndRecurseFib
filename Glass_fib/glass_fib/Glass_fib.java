package glass_fib;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class Glass_fib extends Application {
	
	static final int ITERATIONS_TO_CALC = 20;

	/**
	 * Used to setup timers and measure performance of fibonacci methods.
	 * 
	 * @param args - program arguments (not used)
	 */
			
	public static void main(String[] args) {		
		launch(args);
	}
	
	/**
	 * print out a JavaFX graph comparing the times for calculating a fibonacci number
	 * recursively or iteratively
	 * 
	 * @param recursiveTimes - a list of nanoseconds to calculate each fibonacci number recursively
	 * @param iterativeTimes - a list of nanoseconds to calculate each fibonacci number iteratively
	 */
	
	public void printGraph(List<Long> recursiveTimes, List<Long> iterativeTimes) {
		// TODO document why this method is empty
	}
	
	
	/**
	 * calculate the fibonacci sequence to the number-th digit using a recursive algorithm
	 * 
	 * @param iterations - pass in the number of iterations to perform of the fibonacci sequence
	 * @param numbner - current number in the sequence
	 * 
	 * @return return the number of fibonacci sequence
	 */
	public static long fibRecursive(long iterations) {
		
		if (iterations <= 1) {
			return iterations;
		}
		
		return fibRecursive(iterations - 1) + fibRecursive(iterations - 2);
	}
	
	
	/**
	 * calculate the fibonacci sequence to the number-th digit using an iterative algorithm
	 * 
	 * @param iterations - pass in the number of iterations to perform of the fibonacci sequence
	 * 
	 * @return return the time it took in nanoseconds to calculate the fibonacci sequence
	 */
	public static long fibIterative(int iterations) {
		
		long zeroth = 0;
		long first = 1;
		long nextNumber = 0;
		
		if (iterations == 0) {
			return zeroth;
		}
		
		for (int i = 1; i < iterations; i++) {
			nextNumber = zeroth + first;
			zeroth = first;
			first = nextNumber;
		}
		
		return first;
	}
	
	public static List<Long> calculateRecursive() {
		List<Long> recursiveTimes = new ArrayList<>();
		
		for (int i = 0; i <= ITERATIONS_TO_CALC; i++ ) {
			long recursiveStartTime = System.nanoTime();
			fibRecursive(i);
			long recursiveEndTime = System.nanoTime();
			recursiveTimes.add(recursiveEndTime - recursiveStartTime);
		}
		return recursiveTimes;
	}
	
	public static List<Long> calculateIteratice() {
		List<Long> iterativeTimes = new ArrayList<>();
		
		for (int i = 0; i <= ITERATIONS_TO_CALC; i++ ) {
			long iterativeStartTime = System.nanoTime();
			fibIterative(i);
			long iterativeEndTime = System.nanoTime();
			iterativeTimes.add(iterativeEndTime - iterativeStartTime);
		}
		
		return iterativeTimes;
	}

	
	/**
	 * create the JavaFX window
	 * 
	 */
	@Override
	public void start(Stage arg0) throws Exception {
		
		// define graph axis
		final NumberAxis xAxis = new NumberAxis(0, 42, 1);
		final NumberAxis yAxis = new NumberAxis(10000, 900000000, 10000);
		xAxis.setLabel("n-th Fibonacci number");
		yAxis.setLabel("Time in nanoseconds");
		
		// build the data
		List<Long> recursiveTimes = calculateRecursive();
		Series<Integer, Long> recursiveSeries = new XYChart.Series<>();
		recursiveSeries.setName("Recursive Fibonnaci Calculation Times");
		for (int i = 0; i < recursiveTimes.size(); i++) {
			recursiveSeries.getData().add(new Data<Integer, Long>(i, recursiveTimes.get(i)));
		}
		
		List<Long> iterativeTimes = calculateIteratice();
		Series<Integer, Long> iterativeSeries = new XYChart.Series<>();
		iterativeSeries.setName("Iterative Fibonnaci Calculation Times");
		for (int i = 0; i < iterativeTimes .size(); i++) {
			iterativeSeries.getData().add(new Data<Integer, Long>(i, iterativeTimes .get(i)));
		}
		
		// build the line graph
		LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
	}

}

//Author Name: M. Corey Glass
//Date: 6/19/2022
//Program Name: Glass_fib
//Purpose: time how long is takes to calculate Fibonacci numbers via a recursive algorithm
		// and an iterative algorithm and display a graph of the results 

package glass_fib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.FlowPane;
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
		// calculate Fibonacci numbers from the 0-th element to the ITERATIONS_TO_CALC-th element
		// using a recursive algorithm
		List<Long> recursiveTimes = new ArrayList<>();
		
		for (int i = 0; i <= ITERATIONS_TO_CALC; i++ ) {
			long recursiveStartTime = System.nanoTime();
			fibRecursive(i);
			long recursiveEndTime = System.nanoTime();
			recursiveTimes.add(recursiveEndTime - recursiveStartTime);
		}
		return recursiveTimes;
	}
	
	public static List<Long> calculateIterative() {
		// calculate Fibonacci numbers from the 0-th element to the ITERATIONS_TO_CALC-th element
		// using an iterative algorithm
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
	 * @param stage - default stage parameter
	 * 
	 */
	@Override
	public void start(Stage stage) throws Exception {
				
		// build the data
		List<Long> recursiveTimes = calculateRecursive();
		Series<Number, Number> recursiveSeries = new XYChart.Series<>();
		recursiveSeries.setName("Recursive Fibonnaci Calculation Times");
		for (int i = 0; i < recursiveTimes.size(); i++) {
			recursiveSeries.getData().add(new Data<>(i, recursiveTimes.get(i)));
		}
		
		List<Long> iterativeTimes = calculateIterative();
		Series<Number, Number> iterativeSeries = new XYChart.Series<>();
		iterativeSeries.setName("Iterative Fibonnaci Calculation Times");
		Series<Number, Number> iterativeSeriesForIterativeChart = new XYChart.Series<>();
		iterativeSeriesForIterativeChart.setName("Iterative Fibonnaci Calculation Times");
		for (int i = 0; i < iterativeTimes .size(); i++) {
			iterativeSeries.getData().add(new Data<>(i, iterativeTimes .get(i)));
			iterativeSeriesForIterativeChart.getData().add(new Data<>(i, iterativeTimes .get(i)));
		}
		
		// build the line graph that shows both results
		// define graph axis
		double yMax = (double) Collections.max(recursiveTimes) + 10000;
		final NumberAxis xAxis = new NumberAxis(0, ITERATIONS_TO_CALC, 2);
		final NumberAxis yAxis = new NumberAxis(0, yMax, 5000);
		xAxis.setLabel("n-th Fibonacci number");
		yAxis.setLabel("Time in nanoseconds");

		LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
		lineChart.getData().add(recursiveSeries);
		lineChart.getData().add(iterativeSeries);

		// build the line graph that shows just iterative
		yMax = (double) Collections.max(iterativeTimes) + 100;
		final NumberAxis iterativeXAxis = new NumberAxis(0, ITERATIONS_TO_CALC, 2);
		final NumberAxis iterativeYAxis = new NumberAxis(0, yMax, 100);
		iterativeXAxis.setLabel("n-th Fibonacci number");
		iterativeYAxis.setLabel("Time in nanoseconds"); 
		
		LineChart<Number, Number> iterativeChart = new LineChart<>(iterativeXAxis, iterativeYAxis);
		iterativeChart.getData().add(iterativeSeriesForIterativeChart);
		
		// setup scene
		FlowPane pane = new FlowPane(lineChart, iterativeChart);
		pane.setVgap(8);
		pane.setHgap(4);
		pane.setPrefWidth(1024);
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("Fibonacci Calculation Times");
		stage.show();
	}
}

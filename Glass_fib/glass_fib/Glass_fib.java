package glass_fib;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;

public class Glass_fib extends Application {

	/**
	 * Used to setup timers and measure performance of fibonacci methods.
	 * 
	 * @param args - program arguments (not used)
	 */
			
	public static void main(String[] args) {
		
		int iterationsToCalc = 42;
		
		List<Long> recursiveTimes = new ArrayList<>();
		List<Long> iterativeTimes = new ArrayList<>();
		
		for (int i = 0; i <= iterationsToCalc; i++ ) {
			long recursiveStartTime = System.nanoTime();
			System.out.println("i = " + i + ", recursive sequence num = " + fibRecursive(i));
			long recursiveEndTime = System.nanoTime();
			System.out.println("Total Recursive time - " + (recursiveEndTime - recursiveStartTime) + " nanoseconds");
			recursiveTimes.add(recursiveEndTime - recursiveStartTime);
			
			long iterativeStartTime = System.nanoTime();
			System.out.println("i = " + i + ", iterative sequence num = " + fibIterative(i));
			long iterativeEndTime = System.nanoTime();
			System.out.println("Total Iterative time - " + (iterativeEndTime - iterativeStartTime) + " nanoseconds");
			iterativeTimes.add(iterativeEndTime - iterativeStartTime);
			System.out.println();
		}
		

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

	
	/**
	 * create the JavaFX window
	 * 
	 */
	@Override
	public void start(Stage arg0) throws Exception {
		
		
	}

}

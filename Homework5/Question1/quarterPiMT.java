import java.util.concurrent.*;
import java.util.*;

public class quarterPiMT{

	private static double sum = 0;

	public static void main(String[] args){
		long before = System.currentTimeMillis();
		System.out.println(before);
		ExecutorService executor = Executors.newFixedThreadPool(2000);
		int i;

		for (i = 0; i < 2000; i++)
			executor.execute(new CoordGen());

		executor.shutdown();

		while(!executor.isTerminated()) {}

		double ratio = sum/2000;
		double error = (Math.PI/4 - ratio)/(Math.PI/4) *100;
		System.out.println("From main: " + ratio + "\n"+error+"%");
		System.out.println(System.currentTimeMillis()-before);
	}

	public static class CoordGen implements Runnable {
		@Override
		public void run(){
				CalcQuarterPi calc = new CalcQuarterPi();
				calc.solve();
		}
	}

	public static class CalcQuarterPi{
		private double x = 0;
		private double y = 0;
		private int within = 0;
		private double ratio = 0;
		private int m = 0;

		public void solve(){
			for (m = 0; m < 2000000; m++){
				x = Math.random();
				y = Math.random();
				if(Math.sqrt(x*x+y*y) <= 1){
					within++;
				}
			}
			ratio = ((double)within/2000000);
			total(ratio);	
		}

		public synchronized void total(double ratio){
			sum = sum + ratio;
		}
	}
}






import java.util.*;

public class quarterPi{
	public static void main(String[] args){
		long before = System.currentTimeMillis();
		double xcoord = 0;
		double ycoord = 0;
		long within = 0;
		//long fourBillion = 4000000000L;

		for(long i = 0; i < 4000000000L; i++){
			xcoord = Math.random();
			ycoord = Math.random();
			//System.out.println("("+xcoord+", "+ ycoord +")");
			if (xcoord * xcoord + ycoord * ycoord <= 1) {
				//System.out.println(Math.sqrt(xcoord * xcoord + ycoord * ycoord));
				within++;
			}
		}

		System.out.println(within);
		double ratio = ((double)within/4000000000L);
		System.out.println(ratio + " " + Math.PI/4);

		System.out.println(System.currentTimeMillis() - before);
	}
}
import java.util.Scanner;

public class TwoCirclesEvaluator {
	public static void main(String[] args){
		
		while(true){
			Scanner inputStream = new Scanner(System.in);
			try{
				System.out.println("Enter the coordinates of the center of the first circle\n");
				int x1 = inputStream.nextInt();
				int y1 = inputStream.nextInt();
				System.out.println("Your coordinates for circle 1 are (" + x1 +", " + y1 + ")\n");
				
				System.out.println("Enter the radius for circle 1\n");
				int radiusCirc1 = inputStream.nextInt();
				System.out.println("Your radius for circle 1 is " + radiusCirc1 + "\n");
				
				System.out.println("Enter the coordinates of the center of the second circle\n");
				int x2 = inputStream.nextInt();
				int y2 = inputStream.nextInt();
				System.out.println("Your coordinates for circle 2 are (" + x2 +", " + y2 + ")\n");
				
				System.out.println("Enter the radius for circle 2\n");
				int radiusCirc2 = inputStream.nextInt();
				System.out.println("Your radius for circle 2 is " + radiusCirc2 + "\n");
				
				double distanceBetweenCenters = Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
				
				System.out.println("The distance between centers is " + distanceBetweenCenters + ".\n"
					+ "Circle 1: Center is (" + x1 +", " + y1 + "), with a radius of " + radiusCirc1
					+ ".\n Circle 2: Center is ("+  x2 +", " + y2 + ") with a radius of " + radiusCirc2 + ".\n");
				
				double smallCircle;
				double bigCircle;
				if (radiusCirc1 > radiusCirc2){
					smallCircle = radiusCirc2;
					bigCircle = radiusCirc1;
				} else {
					smallCircle = radiusCirc1;
					bigCircle = radiusCirc2;
				}
				
				if(distanceBetweenCenters - radiusCirc2 == radiusCirc1){
					System.out.println("The two circles touch once.\n\n");
				} else if (radiusCirc1 + radiusCirc2 > distanceBetweenCenters && distanceBetweenCenters + smallCircle <= bigCircle)
					{ System.out.println("A circle is within the other circle.\n\n");
		
				} else if (distanceBetweenCenters > radiusCirc1 + radiusCirc2){
					System.out.println("The two circles are separate from each other.\n\n");
				} else {
					System.out.println("The two circles overlap each other.\n\n");}
			} catch (Exception e){
				System.out.println("Please only use numbers, exiting.\n");
			}
		}	
	}
}

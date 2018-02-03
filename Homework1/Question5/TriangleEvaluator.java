import java.util.*;
/*public class distanceFormula(Point A, Point B){
	
}

public class Point{
	private int xCoordinate;
	private int yCoordinate;
	
	Point(int x, int y) {
		xCoordinate = x;
		yCoordinate = y;
	}
}
*/
public class TriangleEvaluator {
	public static void main(String[] args){
		while(true){
			Scanner inputStream = new Scanner(System.in);
			try {
				System.out.println("Given Three Points, do they make a triangle? \n Enter Coordinates for point 1\n");
				int x1 = inputStream.nextInt();
				int y1 = inputStream.nextInt();
				System.out.println("Enter Coordinates for point 2\n");
				int x2 = inputStream.nextInt();
				int y2 = inputStream.nextInt();
				System.out.println("Enter Coordinates for point 3\n");
				int x3 = inputStream.nextInt();
				int y3 = inputStream.nextInt();
				
				System.out.println("Your points are (" + x1 + "," + y1 +"), (" + x2 + "," + y2 +
						"), (" + x3 + "," + y3 + ")\n");
				
				double side1 = Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
				double side2 = Math.sqrt(Math.pow((x2-x3), 2) + Math.pow((y2-y3), 2));
				double side3 = Math.sqrt(Math.pow((x3-x1), 2) + Math.pow((y3-y1), 2));
				
				//System.out.println("" + side1 + side2 + side3);
				
				if(!(side1 + side2 > side3) || !(side2 + side3 > side1) || !(side3 + side1 > side2)){
					System.out.println("Invalid Triangle");
				} else {
					System.out.println("This is a valid triangle");
				}
			} catch (Exception e){
				System.out.println("Please only input numbers, restarting\n\n");
			}
		}
	}
}

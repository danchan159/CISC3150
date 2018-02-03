import java.util.*;

public class areaCircle {
	public static void main(String[] args){
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter the radius of the circle. I will give you the area of the circle.\n");
		while(userInput.hasNext()){
			try {
				String input = userInput.next();
				double radius = Double.parseDouble(input);
				double area = Math.pow(radius, 2) * Math.PI;
				System.out.println("Your input of " + input + " has an area of "+ area +"\n");
				System.out.println("Enter the radius of the circle. I will give you the area of the circle.\n");
			}
			catch (Exception e){
				System.out.println("Please give a proper number or press CTRL+Z to exit");
			}
		}
		
		userInput.close();
		System.exit(0);
	}
}

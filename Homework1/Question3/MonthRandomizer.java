import java.util.*;

public class MonthRandomizer {
	public static void main(String[] args){
		
		double monthNumber = Math.ceil(Math.random()*12);
		int monthNumberInt = (int)monthNumber;	
		System.out.println(monthNumberInt);

		Vector<String> months = new Vector<String>();
		
		months.addElement(new String("January"));
		months.addElement(new String("February"));
		months.addElement(new String("March"));
		months.addElement(new String("April"));
		months.addElement(new String("May"));
		months.addElement(new String("June"));
		months.addElement(new String("July"));
		months.addElement(new String("August"));
		months.addElement(new String("September"));
		months.addElement(new String("October"));
		months.addElement(new String("November"));
		months.addElement(new String("December"));
		
		System.out.println(months.elementAt(monthNumberInt-1)+ "\n");

		for(int i = 0; i < 12; i++){
			System.out.println(months.elementAt(i));
		}
		
		System.exit(0);
	}
}

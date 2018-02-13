import java.util.*;
import java.lang.*;

public class Calendar{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);

		System.out.println("What year is it?");
		int year = input.nextInt();

		Vector<String> daysWeekContainer = new Vector<String>(0);
		daysWeekContainer.addElement("sunday");
		daysWeekContainer.addElement("monday");
		daysWeekContainer.addElement("tuesday");
		daysWeekContainer.addElement("wednesday");
		daysWeekContainer.addElement("thursday");
		daysWeekContainer.addElement("friday");
		daysWeekContainer.addElement("saturday");

		String dayName = "";

		boolean validDay = false;
		while(!validDay){
			System.out.println("What day of the week did the first day of January fall on?");
			dayName = input.next();
			dayName = dayName.toLowerCase();
			if (!daysWeekContainer.contains(dayName)){
				System.out.println("Please enter the full name of the day, or enter a valid day of the week.");
			} else {
				validDay = true;
			}
		}

		int dayWeekIndex = daysWeekContainer.indexOf(dayName);
		int[] daysMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int[] daysContainer = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,
			25,26,27,28,29,30,31};
		Vector<String> months = new Vector<String>(0);
		months.addElement("January");
		months.addElement("February");
		months.addElement("March");
		months.addElement("April");
		months.addElement("May");
		months.addElement("June");
		months.addElement("July");
		months.addElement("August");
		months.addElement("September");
		months.addElement("October");
		months.addElement("November");
		months.addElement("December");

		int days = 0;
		String restOfTheMonth =  "";

		boolean leapYear = (year%4 == 0);
		if (year%100 == 0){
			if(year%400 == 0){
				leapYear = true;
			} else {
				leapYear = false;
			}
		}

		if (leapYear) {
			daysMonth[1]++;
		}

		for(int monthNumber = 0; monthNumber < months.size(); monthNumber++){
			System.out.printf("%12s%-9s%n", months.elementAt(monthNumber) + " ", year);
			//System.out.printf("%-9s%n", year);

			for (int weekMember = 0; weekMember < daysWeekContainer.size(); weekMember++){
				System.out.printf("%-1s%-1s%1s", daysWeekContainer.elementAt(weekMember).substring(0,1).toUpperCase(), daysWeekContainer.elementAt(weekMember).substring(1,2),  " ");
			}
			System.out.println("");

			for (int firstWeekSpaces = 0; firstWeekSpaces < dayWeekIndex; firstWeekSpaces++){
				System.out.printf("%3s", " ");
			}

			for (int firstWeekSignificant = dayWeekIndex; firstWeekSignificant < daysWeekContainer.size(); firstWeekSignificant++){
				System.out.printf("%2d%1s", daysContainer[firstWeekSignificant - dayWeekIndex], " ");
				days++;
			}

			int daysSoFar = days;
			System.out.println("");

			for (int restOfTheWeeks = 0; restOfTheWeeks < ((daysMonth[monthNumber]- daysSoFar)/7); restOfTheWeeks++){
				for (int restOfTheDays = 0; restOfTheDays < 7; restOfTheDays++){
					restOfTheMonth = restOfTheMonth + String.format("%2s", Integer.toString(daysContainer[days])) + " ";
					days++;
				}
				restOfTheMonth = restOfTheMonth + ";";
			}

			int almostDoneDays = days;

			for (int remainingDays = 0; remainingDays < daysMonth[monthNumber] - almostDoneDays; remainingDays++){
				restOfTheMonth = restOfTheMonth + Integer.toString(daysContainer[days]) + " ";
				days++;
			}

			String[] weeks = restOfTheMonth.split(";");

			for (int weeksSpitter = 0; weeksSpitter < weeks.length; weeksSpitter++){
				System.out.println(weeks[weeksSpitter]);
			}

			System.out.println("");
			dayWeekIndex = (daysMonth[monthNumber]- daysSoFar)%7;
			days = 0;
			restOfTheMonth = "";
		}
	}
}
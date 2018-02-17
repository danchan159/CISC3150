import java.util.*;

public class MyStringDriver{
	public static void main(String[] args){
		char[] word = {'H', 'e', 'l', 'p', ' ', 'M', 'e'};
		String fromMyString;
		MyString drivingString = new MyString(word);
		System.out.println("Making MyString drivingString " + drivingString);
		MyString aMyString = new MyString();
		System.out.println("Making a default MyString " + aMyString);

		System.out.println("The char in drivingString at index 3 is " + drivingString.charAt(3));
		System.out.println("The length of drivingString is " + drivingString.length());
		System.out.println("The first half of drivingString is " + drivingString.substring(0, drivingString.length()/2));
		System.out.println("This is the quiet version of drivingString: " + drivingString.toLowerCase());
		System.out.println("This is the loud version of drivingString: " + drivingString.toUpperCase());
		System.out.println("This is aMyString: " + aMyString.substring(0, aMyString.length()));
		System.out.println("This is the comparison value between drivingString and aMyString: " + drivingString.compareTo(aMyString));
		aMyString = drivingString.getMyString();
		System.out.println("This is aMyString now as drivingString: " + aMyString.substring(0, aMyString.length()));
		System.out.println("This is the updated comparison value between drivingString and aMyString " + drivingString.compareTo(aMyString));
		System.out.println("This is int two billion in MyString format: " + MyString.valueOf(2000000000));
		fromMyString = drivingString.toString();
		System.out.println("This is a String object composed from drivingString: " + fromMyString);
	}
}

class MyString{
	private char[] chars;


	public MyString(){
		char[] tempChar = {'D', 'e', 'f', 'a', 'u', 'l','t'};
		this.chars = tempChar;
	}
	public MyString(char[] chars){
		this.chars = chars;
	}
	
	public char charAt(int index){
		char tempChar = '/';
		if (index < this.chars.length)
			tempChar = this.chars[index];
		return tempChar;
	}

	public int length(){
		return this.chars.length;
	}

	public MyString substring(int begin, int end){
		char[] tempChar = new char[(end-begin)];
		for (int i = 0; i < tempChar.length; i++){
			tempChar[i] = this.chars[begin];
			begin++;
		}
		MyString tempMyString = new MyString(tempChar);

		return tempMyString;
	}

	public MyString toLowerCase(){
		char[] tempChar = new char[this.chars.length];
		for(int it = 0; it < tempChar.length; it++){
			tempChar[it] = this.chars[it];
		}

		for (int i = 0; i < this.chars.length; i++){
			if((int)chars[i] < 91 && (int)chars[i] > 64){
				int asciiChar = (int)chars[i];
				char lowerCaseChar = (char)(asciiChar + 32);
				tempChar[i] = lowerCaseChar;
			}
		}
		MyString tempMyString = new MyString(tempChar);
		return tempMyString;
	}

	public MyString toUpperCase(){
		char[] tempChar = new char[this.chars.length];
		for(int it = 0; it < tempChar.length; it++){
			tempChar[it] = this.chars[it];
		}
		for (int i = 0; i < this.chars.length; i++){
			if((int)chars[i] > 96 && (int)chars[i] < 123){
				int asciiChar = (int)chars[i];
				char upperCaseChar = (char)(asciiChar - 32);
				tempChar[i] = upperCaseChar;
			}
		}
		MyString tempMyString = new MyString(tempChar);
		return tempMyString;
	}
	

	public int compareTo(MyString s){
		int truth = 0;
		int shorterLength;
		if (this.chars.length < s.chars.length){
			shorterLength = this.chars.length;
		} else {
			shorterLength = s.chars.length;
		}
		
		for (int i = 0; i < shorterLength; i++){
			if (this.chars[i] != s.chars[i]){
				truth = this.chars[i] - s.chars[i];
				return truth;
			}
		}
		if (s.chars.length != this.chars.length) {
			truth = this.chars.length - s.chars.length;
			return truth;
		}
		return truth;
	}

	public MyString getMyString(){
		MyString tempMyString = new MyString(this.chars);
		return tempMyString;
	}


	public String toString(){
		String tempString = new String(this.chars);
		return tempString;
	};	

	public static MyString valueOf(int i){
		int[] digitsPos = {10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
		int[] digitsNeg = {-10, -100, -1000, -10000, -100000, -1000000, -10000000, -100000000, -1000000000};
		int numberOfDigits = 0;
		boolean updated = false;
		char[] tempChar;

		if (i == 0){
			numberOfDigits = 1;
			tempChar = new char[1];
			tempChar[0] = (char)(i+48);
		} else if (i > 0) {
			digitSearchPos:
			for (int it = 0; it < digitsPos.length; it++){
				if(i < digitsPos[it]){
					numberOfDigits = it+1;
					updated = true;
					break digitSearchPos;
				}
			}
			if (!updated){
				numberOfDigits = 10;
			}

			tempChar = new char[numberOfDigits];
			for (int digit = tempChar.length - 1; digit > -1; digit--){
				tempChar[digit] = (char)(i%10 + 48);
				i = i/10;
			}
		} else {
			digitSearchNeg:
			for (int ite = 0; ite < digitsNeg.length; ite++){
				if (i > digitsNeg[ite]){
					numberOfDigits = ite+1;
					updated = true;
					break digitSearchNeg;
				}
			}
			if(!updated){
				numberOfDigits = 10;
			}
			i = i - (2*i);
			tempChar = new char[numberOfDigits + 1];
			tempChar[0] = '-';

			for (int digit = tempChar.length - 1; digit > 0; digit--){
				tempChar[digit] = (char)(i%10 + 48);
				i = i/10;
			}
		}
		MyString tempMyString = new MyString(tempChar);
		return tempMyString;
	}
}
import java.util.*;

public class PalindromeCheck{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);

		System.out.println("Please enter a word to check if it is a palindrome");
		String sampleWord = input.next();
		if (! (sampleWord instanceof String)){
			sampleWord = sampleWord.toString();
		}
		boolean isPalindrome = checkPalindrome(sampleWord);
		//System.out.println(isPalindrome);
		if(isPalindrome){
			System.out.println(sampleWord + ", It's true, that is a nice palindrome\n");
		} else {
			System.out.println(sampleWord + ", Try again, not a palindrome\n");
		}
	}

	public static boolean checkPalindrome(String sampleWord){
		boolean isPalindrome = true;
		if (sampleWord.length() == 1) {
			return isPalindrome;
		} else {
			for (int i = 0; i <  sampleWord.length()/2; i++){
				if (sampleWord.charAt(i) != sampleWord.charAt(sampleWord.length() - i - 1) ){
					return !isPalindrome;
				}
			}
		}

		return isPalindrome;

	}
}
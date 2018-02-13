import java.util.*;

public class DelimiterOfScanner{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		input.useDelimiter(",");

		System.out.println("Start typing a phrase, separated by commas");

		while (input.hasNext()){
			System.out.println(input.next());
		}

		System.exit(0);
	}
}
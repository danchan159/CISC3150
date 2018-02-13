import java.util.*;

public class PyramidStacker{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);

		System.out.println("How tall should this pyramid be?");
		int pyramidHeight = input.nextInt();

		for (int pyramidLevel = 1; pyramidLevel <= pyramidHeight; pyramidLevel++){
			for (int emptySpaces = pyramidLevel; emptySpaces < pyramidHeight; emptySpaces++){
				System.out.printf("%3s", " ");
			}
			for (int increasingNumberToMax = 1; increasingNumberToMax <= pyramidLevel; increasingNumberToMax++){
				System.out.printf("%3d", increasingNumberToMax);
			}
			for (int decreasingNumber = pyramidLevel-1; decreasingNumber > 0; decreasingNumber--){
				System.out.printf("%3d", decreasingNumber);
			}
			System.out.print("\n");
		}

		System.exit(0);
	}
}
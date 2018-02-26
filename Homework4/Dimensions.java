import java.util.*;

public class Dimensions{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);

		char[][] comboTree = new char[input.nextInt()][];
		int row = 0;

		while(input.hasNext()){
			comboTree[row] = new char[Integer.parseInt(input.next())];
			for (int i = 0; i < comboTree[row].length; i++){
				String tempString = input.next();
				comboTree[row][i] = tempString.charAt(0);
			}
			row++;
		}
		extract(comboTree);
	}

	private static void extract(char[][] comboTree){
		Stack stringStacker = new Stack();
		int pushItToTheLimit = 0;
		int limit = 0;
		int row = 0;
		int combo = 0;
		int maxCombo = 1;
		for (row = 0; row <comboTree.length; row++){
			maxCombo = maxCombo * comboTree[row].length;
		}
		boolean[] maxHit = new boolean[comboTree.length];
		int[] maxHitTimes = new int[comboTree.length];
		int[] maxIndexRow = new int[comboTree.length];
		for(row = 0; row < comboTree.length; row++){
			maxIndexRow[row] = comboTree[row].length;
		}

		stringStacker.push('/');

		stackConstruction:
		while(!stringStacker.empty()){
			// when the stack string is the length of the comboTree's length
			if(stringStacker.size()  == comboTree.length + 1){
				Object[] comboString = stringStacker.toArray();
				for (int i = 1; i < comboString.length; i++){
					System.out.print(comboString[i]);
				}
				System.out.println("");
				stringStacker.pop();
				combo++;
				continue stackConstruction;
			}
			if(maxIndexRow[limit] < pushItToTheLimit){
				for (row = comboTree.length - 1; row >= 0; row--){
						if (!maxHit[row]){
							limit = row;
							maxHitTimes[row] = maxHitTimes[row] + 1;
							pushItToTheLimit = maxHitTimes[row];
							continue stackConstruction;
						} else {
							stringStacker.pop();
							maxHit[row] = false;
							maxHitTimes[row] = 0;
						}
				}
				continue stackConstruction;
			}
			if (maxIndexRow[limit] == pushItToTheLimit){
				for (row = 0; row < maxHit.length; row++){
					if (maxHitTimes[row] == maxIndexRow[row] -1){
						maxHit[row] = true;
					}
				}
				maxHit[limit] = true;
				pushItToTheLimit++;
				continue stackConstruction;
			}
			if(limit != comboTree.length - 1){
				stringStacker.push(comboTree[limit][pushItToTheLimit]);
				limit++;
				pushItToTheLimit = 0;
				continue stackConstruction;
			}
			if (limit == comboTree.length - 1){
				stringStacker.push(comboTree[limit][pushItToTheLimit]);
				pushItToTheLimit++;
				continue stackConstruction;
			}
			stringStacker.pop();
		}
			
		System.out.println("Quick Check, " + combo + " strings equals " + maxCombo + " maximum strings");
		}
	}
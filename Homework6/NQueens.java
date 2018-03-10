import java.util.*;

public class NQueens{

	private int n;
	private Stack<Integer> foundDeQueen;

	NQueens(){
		n = 1;
		foundDeQueen = new Stack<Integer>();
	}

	NQueens(int n){
		this.n = n;
		foundDeQueen = new Stack<Integer>();
	}


	void printSolution(){
		int i, j;

		System.out.println("The way of: " + foundDeQueen.toString());

		for (i = 0; i < n; i++){
			for (j = 0; j < n; j++){
				if(foundDeQueen.get(i).intValue() == j)
					System.out.print("Q ");
				else System.out.print(". ");
			}
			System.out.println("");
		}
		System.out.println("");
	}


	boolean doYuKnoDeWae(int row, int col){
		for (int i = 0; i < foundDeQueen.size(); i++){
			if (foundDeQueen.get(i).intValue() == col || i+foundDeQueen.get(i).intValue() == row + col || ((i-foundDeQueen.get(i).intValue()) == (row - col))){
				return false;
			}
		}
		return true;
	}


	void findQueens(int row){
		if (!foundDeQueen.empty()){
			if (foundDeQueen.size() == n) {
					printSolution();
					foundDeQueen.pop();
					return;
			}
			int i;
			for (i = 0; i < n; i++) {
				if (doYuKnoDeWae(row, i)) {
					foundDeQueen.push(i);
					findQueens(row + 1);
				}
			}
			foundDeQueen.pop();
		}
	}
	
	void solveFor(){
		// DFS
		for (int i = 0; i<n; i++){
			foundDeQueen.push(i);
			findQueens(1);
		}
	}


	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("How many Queens?");
		int n = scn.nextInt();
		System.out.println("You chose " + n + " Queens. We will find the Queens. This is the way.\n");

		NQueens nQ = new NQueens(n);

		nQ.solveFor();

		System.out.print("We have found the Queens.");
	}
}
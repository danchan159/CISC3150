// handles int and double

import java.util.*;
import java.math.*;

public class CmdCalc{
	private Stack<String> stk;
	private String output;
	private String args[];
	private BigDecimal result;
	private MathContext precision;

	CmdCalc(){
		stk = new Stack<String>();
		output = "";
		result = new BigDecimal(0.0);
		precision = new MathContext(2);
	}

	CmdCalc(String[] args){
		stk = new Stack<String>();
		output = "";
		result = new BigDecimal(0.0);
		precision = new MathContext(2);
		this.args = new String[args.length];
		for(int i = 0; i < args.length; i++){
			this.args[i] = args[i];
		}
	}

	int evaluatePrecedence(String operator){
		if (operator.compareTo("+") == 0 || operator.compareTo("-") == 0){
			return 0;
		} else {
			return 1;
		}
	}

	void infixToPostfix(){
		System.out.println("Your expression was: ");
		for (int i = 0; i < args.length; i++){
			System.out.print(args[i] + " ");
		}
		System.out.println("\n");

		int currentOperatorPrecedence = 0;
		boolean nextIsOperator = false;
		// false means expecting an Operand

		stkConstruct:
		for (int i = 0; i < args.length; i++){
			if(args[i].compareTo("+") == 0 || args[i].compareTo("-") == 0 ||
				args[i].compareTo("*") == 0 || args[i].compareTo("/") == 0){

				nextIsOperator = false;

				if(stk.empty()){
					stk.push(args[i]);
					continue stkConstruct;
				}

				currentOperatorPrecedence = evaluatePrecedence(args[i]);
				try{
						while(currentOperatorPrecedence < evaluatePrecedence(stk.peek())){
							if (stk.peek().compareTo("(") == 0){
								stk.pop();
								continue stkConstruct;
							} else {
								output = output.concat(stk.pop() + " ");
							}
					}
				} catch (Exception e){
					// evaluatePrecedence(stk.peek()) throws EmptyStackException
					// without adding another if (!stk.empty()){ while(currentOp...}
					// unsure how to handle the off by one error
				} finally {
					stk.push(args[i]);
					continue stkConstruct;
				}
			} else if (args[i].compareTo("(") == 0){
				stk.push(args[i]);
				continue stkConstruct;
			} else if(args[i].compareTo(")") == 0){
				try{
					while(stk.peek().compareTo("(") != 0){
						output = output.concat(stk.pop() + " ");
					}
					stk.pop();
				} catch (Exception e){
				}
				continue stkConstruct;
			} 


			if(args[i].matches("-?\\d+(\\.\\d+)?") && !nextIsOperator){
				output = output.concat(args[i] + " ");
				nextIsOperator = true;
				continue stkConstruct;
			} else if(nextIsOperator) {
				throw new QuitMashingOnYourKeyboardException(args[i] + ", expected an operator");
			}

			if(args[i].matches("[^\\+\\-\\*\\/\\(\\)]") && !args[i].matches("-?\\d+(\\.\\d+)?")
				 && nextIsOperator){
				throw new QuitMashingOnYourKeyboardException(args[i]);
			} else {
				throw new AlgebraFailException(args[i]);
			}
		}

		while(!stk.empty()){
			output = output.concat(stk.pop() + " ");
		}
	}

	String evaluatePostfix(){
		System.out.println("The postfix is " + output);
		BigDecimal operand1, operand2;
		
		Scanner scn = new Scanner(output);
		scn.useDelimiter(" ");
		while(scn.hasNext()){
			String pieceInQuestion = scn.next();

			if(pieceInQuestion.matches("-?\\d+(\\.\\d+)?")){
				stk.push(pieceInQuestion);
			} else if (pieceInQuestion.compareTo("+") == 0){
				try{
					operand2 = new BigDecimal(Double.parseDouble(stk.pop()));
					operand1 = new BigDecimal(Double.parseDouble(stk.pop()));
					result = operand1.add(operand2, precision);
					stk.push(result.toString());
				} catch (EmptyStackException e){
					throw new UserIsADumbassException(pieceInQuestion);
				}
			} else if (pieceInQuestion.compareTo("-") == 0){
				try{
					operand2 = new BigDecimal(Double.parseDouble(stk.pop()));
					operand1 = new BigDecimal(Double.parseDouble(stk.pop()));
					result = operand1.subtract(operand2, precision);
					stk.push(result.toString());
				} catch (EmptyStackException e){
					throw new UserIsADumbassException(pieceInQuestion);
				}
			} else if (pieceInQuestion.compareTo("*") == 0){
				try{
					operand2 = new BigDecimal(Double.parseDouble(stk.pop()));
					operand1 = new BigDecimal(Double.parseDouble(stk.pop()));
					result = operand1.multiply(operand2, precision);
					stk.push(result.toString());
				} catch (EmptyStackException e){
					throw new UserIsADumbassException(pieceInQuestion);
				}
			} else if (pieceInQuestion.compareTo("/") == 0){
				if(stk.peek().compareTo("0") == 0){
					throw new ArithmeticException("Don't divide by 0!");
				} else {
					try{ 
						operand2 = new BigDecimal(Double.parseDouble(stk.pop()));
						operand1 = new BigDecimal(Double.parseDouble(stk.pop()));
						result = operand1.divide(operand2, precision);
						stk.push(result.toString());
					} catch (EmptyStackException e){
						throw new UserIsADumbassException(pieceInQuestion);
					}
				}
			}
		}

		if (stk.peek().endsWith(".0")){
			return stk.pop().replace(".0", "");
		}
		return stk.pop();
	}

	public static void main(String[] args){
		if (args.length < 1){
			System.out.println("Please give me stuff");
			return;
		} 
		CmdCalc quickMaths = new CmdCalc(args);

		try {
			quickMaths.infixToPostfix();
			System.out.println(quickMaths.evaluatePostfix());
		} catch (QuitMashingOnYourKeyboardException e){
			System.out.println(e.getMessage());
		} catch (AlgebraFailException e){
			System.out.println(e.getMessage());
		} catch (UserIsADumbassException e){
			System.out.println(e.getMessage());
		} catch (ArithmeticException e){
			System.out.println(e.getMessage());
		}
	}
}



class AlgebraFailException extends IllegalArgumentException{
	private String nan;

	AlgebraFailException(){
	}

	AlgebraFailException(String nan){
		super("Invalid operand: " + nan);
		this.nan = nan;
	}

	public String getNan(){
		return nan;
	}
}

class QuitMashingOnYourKeyboardException extends IllegalArgumentException{
	private String notAnOperator;

	QuitMashingOnYourKeyboardException(){
	}

	QuitMashingOnYourKeyboardException(String notAnOperator){
		super("Bad operator: " + notAnOperator);
		this.notAnOperator = notAnOperator;
	}

	public String getNotAnOperator(){
		return notAnOperator;
	}
}

class UserIsADumbassException extends IllegalArgumentException{

	UserIsADumbassException(){
	}

	UserIsADumbassException(String pieceInQuestion){
		super("Missing a number near \"" + pieceInQuestion + "\"");
	}
}




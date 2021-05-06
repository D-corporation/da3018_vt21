package lab2;

import java.util.Scanner;

// On command line, run using: java -jar lab2.jar < lab2_test_data.txt

public class Rpn {
	public static void main(String [] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNextLine()) { // loops 
			Stack rpn_stack = new Stack(); // note that the stack is defined inside the loop so that it is reset with each iteration
			String input = scanner.nextLine();
			
			try {
				
				if (input.matches(".*[^0-9\\s+-=*/].*") || input.matches("\\s+.*")) { // Check for allowed strings. Only digits and the operators +,-,*,/,=. Also handles strings starting with whitespaces.
					throw new Exception();
				} 
				
				String[] input_split = input.split("\\s"); // Split input on SINGLE whitespaces.
				
				for (String s: input_split){
					if (Rpn.is_double(s)) { // if an element is double, push it to stack.
						rpn_stack.push(Double.parseDouble(s));
					}
					
					else { // if an element is not a double, check cases and use appropriate methods
						switch(s) {
							case "+": 
								Rpn.addition(rpn_stack);
								break;
							case "-":
								Rpn.subtraction(rpn_stack);
								break;
							case "*": 
								Rpn.multiplication(rpn_stack);
								break;
							case "/":
								Rpn.division(rpn_stack);
								break;
							case "=":
								Rpn.equal(rpn_stack);
								break;
							default:
								throw new Exception();	// Catches empty input and to many whitespaces from input_split.
						}
					}
				}
			}
			catch(Exception e){
				System.err.println("Error: Input not written according to RPN. Input should be a list containing only doubles and the operators +, -, *, / and =, separated by single whitespaces.");
			}
		}
		scanner.close();
	}
	
	private static boolean is_double(String string) { // Checks if string can be parsed to double. 
		try {
			Double.parseDouble(string);
			return true;
		}
		catch(NumberFormatException error){
			return false;
		}
	}
	
	private static void addition(Stack stack) {
		double x = stack.pop();
		double y = stack.pop();
		stack.push(y + x);
	}
	
	private static void subtraction(Stack stack) {
		double x = stack.pop();
		double y = stack.pop();
		stack.push(y - x);
	}
	
	private static void multiplication(Stack stack) {
		double x = stack.pop();
		double y = stack.pop();
		stack.push(y*x);
	}
	
	private static void division(Stack stack) {
		double x = stack.pop();
		double y = stack.pop();
		stack.push(y / x);
	}
	private static void equal(Stack stack) {
		System.out.println(stack.pop());
	}
		
	
	 static class Stack{
		
		private double[] stack = new double[0];
		
		public boolean is_empty() {
			if (stack.length== 0){
				return true;
				}
			else {
				return false;
				}
			}
		
		public void push(double x) {
			double[] temporary_stack = new double[stack.length+1]; // Create temporary stack that is one element larger. Replaces old stack.
			
			System.arraycopy(stack, 0, temporary_stack, 0, temporary_stack.length-1); // copy values from stack to temporary stack.
			
			stack = temporary_stack; // Replace old stack
			
			stack[stack.length-1] = x; // Set top stack value to x
			}
		
		public double pop() {
			
			double x = stack[stack.length-1]; // the number to return
			
			double[] temporary_stack = new double[stack.length-1]; // Create temporary stack that is one element smaller. Replaces old stack.
			
			System.arraycopy(stack, 0, temporary_stack, 0, temporary_stack.length); // copy values from stack to temporary stack.
			
			stack = temporary_stack; // Replace old stack
		
			return x;
			}
		}

}

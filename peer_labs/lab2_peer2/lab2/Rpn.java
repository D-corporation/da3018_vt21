package lab2;
import java.util.Scanner;

public class Rpn {
	//Create objects Stack and Scanner
	Stack k = new Stack();
	Scanner scan = new Scanner(System.in);
	
	//First of the arithmetic expressions (addition)
	public void add() {
		double x = Stack.array[Stack.array.length-1];
		double y = Stack.array[Stack.array.length-2];
		k.pop();
		k.pop();
		k.push(x+y);
	}
	
	//Second of the arithmetic expressions (subtraction)
	public void sub() {
		double x = Stack.array[Stack.array.length-1];
		double y = Stack.array[Stack.array.length-2];
		k.pop();
		k.pop();
		k.push(x-y);
	}
	
	//Third of the arithmetic expressions (multiplication)
	public void mul() {
		double x = Stack.array[Stack.array.length-1];
		double y = Stack.array[Stack.array.length-2];
		k.pop();
		k.pop();
		k.push(x*y);
	}
	
	//Last of the standard arithmetic expressions (division)
	public void div() {
		double x = Stack.array[Stack.array.length-1];
		double y = Stack.array[Stack.array.length-2];
		k.pop();
		k.pop();
		k.push(x/y);
	}
	
	//Pops top element of the stack and prints it out in stdout
	public void out() {
		double x = Stack.array[Stack.array.length-1];
		k.pop();
		System.out.println(x);
	}
	
	//Adds Input at the top of the stack
	public void input() {
		System.out.println("Add a number to the stack");
		double var = scan.nextDouble();	//Only works with integers for some reason
		k.push(var);
	}
	
	/*
	 * This was my failed attempt at a looping input and using
	 * correct methods depending on input
	 * scan.nextLine() reads the wrong line and doesn't let the
	 * user input
	 * 
	public void use() {
		boolean running = true;
		String input = scan.nextLine();
		do {
			input = scan.nextLine();
			if (input.equals("+")) {
				add();
			} else if (input.equals("-")) {
				sub();
			} else if (input.equals("*")) {
				mul();
			} else if (input.equals("/")) {
				div();
			}  else if (input.equals("=")) {
				out();
			}	else {
				input();
			}
		} while (running == true);
	}*/
}

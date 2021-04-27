package lab2;

public class Main {
	//Main method
	//We use it for some testing
	public static void main(String[] args) {
		//Create object from the Stack class
		Stack s = new Stack();
		Rpn r = new Rpn();
		
		//Use is_empty()
		System.out.println("The stack is empty: " + s.is_empty());
		for (int i = 0; i<Stack.array.length; i++) {
			System.out.println(Stack.array[i] + " ");
		}
		
		//Add elements through push()
		s.push(2.5);
		s.push(3.4);
		s.push(23);
		s.push(4.6);
		s.push(0.1);
		s.push(45);
		s.push(2.2);
		for (int i = 0; i<Stack.array.length; i++) {
			System.out.println(Stack.array[i] + " was added");
		}
		
		//Remove elements through pop()
		System.out.println(s.pop() + " was removed");
		
		//is_empty called again
		System.out.println("The stack is empty: " + s.is_empty());
		
		//Testing user input
		r.input();
		
		//Testing =
		System.out.println("Printing top of stack:");
		r.out();
		
		//Testing +
		r.add();
		
		//Testing -
		r.sub();
		
		//Testing *
		r.mul();
		
		//Testing /
		r.div();
		System.out.println("Printing top of stack after doing +,-,*,/:");
		r.out();
		
		//is_empty called again
		System.out.println("The stack is empty: " + s.is_empty());
	}
}

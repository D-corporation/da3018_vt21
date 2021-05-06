/*
An evaluator using a stack to store data and
Reverse Polish Notation (RPN) to evalute operations.

To run the program, type "java -jar RPNEvaluator.jar" on
the command line.
Now you can start typing numbers and operators.
Numbers will be added to the stack.
Operators pops the top two numbers of the stack, do the operation,
and adds the result to the stack.
You separate numbers and operations with spaces.
To add the numbers to the stack and do the operations, you need
to press Enter.

You need at least two numbers in the stack to perform operations!
Less than that will give an error message.

Approved operations are:
- Addition (+),
- Subtraction (-),
- Multiplication (*), and
- Division (/).
The first number used in the operation is the very top of the stack.

To see the top of the stack, you can use the equal (=) operation.
This operation also pops the top of the stack, so it gets displayed
and removed.

When you want to exit the program, simply press Ctrl + D.

To see examples of operations, see the test_data.txt file.
*/

import java.util.Scanner;

public class RPNEvaluator {

  public static void main(String[] args) {

    Stack my_stack = new Stack();
    Scanner sc = new Scanner(System.in);

    while (sc.hasNext()) {
      // Loop that runs until user press Ctrl + D
      String user_input = sc.next();
      String[] divided_input = user_input.split(" "); // Array of input

      for (int i = 0; i < divided_input.length;i++) {
        try {
          // Test if number, if true push to stack
          double d = Double.parseDouble(divided_input[i]);
          my_stack.push(d);

        } catch (NumberFormatException e) {
          // If not, check for operator and perform operation
          char c = divided_input[i].charAt(0);
          switch (c) {
            case '+':
              my_stack.push(my_stack.pop() + my_stack.pop());
              break;
            case '-':
              my_stack.push(my_stack.pop() - my_stack.pop());
              break;
            case '*':
              my_stack.push(my_stack.pop() * my_stack.pop());
              break;
            case '/':
              my_stack.push(my_stack.pop() / my_stack.pop());
              break;
            case '=':
              System.out.println(my_stack.pop());
              break;
          }
        }
      }
    }
  }
}

class Stack {

  private double[] stack_array = new double[1000]; // Saved values
  private int top = 0; // Keeps track of top number

  public boolean is_empty() {
    if (top == 0) {
      return true;
    } else {
      return false;
    }
  }

  public void push(double x) {
    stack_array[top] = x;
    top++;
  }

  public double pop() {
    top--;
    return stack_array[top];
  }

}

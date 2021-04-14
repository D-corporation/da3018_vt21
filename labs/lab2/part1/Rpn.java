import java.util.Scanner;

public class Rpn {
    public static void main(String[] args) {

        // creates a scanner object
        Scanner scan  = new Scanner(System.in);

        // user inputs the Rpn expression
        String expression = scan.nextLine();
        
        // maybe not necessary but debugger recommends closing the scanner object 
        scan.close();

        // splits the string by each space and creates an array
        String[] expression_arr = expression.split(" ");
        
        // creates a object of class Stack from part 1 of lab
        Stack stk = new Stack();

        // used later in switch cases
        double num1 = 0;
        double num2 = 0;
        double result = 0;

        // loops through each ele(ment) of the created array expression_arr 
        for (String ele: expression_arr) {

            switch (ele) {
                // case for each operator
                // if there is no operator the default will be to push a double to the stack
                case "+":
                    num1 = stk.pop();
                    num2 = stk.pop();
                    
                    stk.push(num1 + num2);
                    break;

                case "-":
                    num1 = stk.pop();
                    num2 = stk.pop();
                
                    stk.push(num2 - num1);
                    break;
                
                case "*":
                    num1 = stk.pop();
                    num2 = stk.pop();

                    stk.push(num1 * num2);

                    break;

                case "/":
                    num1 = stk.pop();
                    num2 = stk.pop();

                    stk.push(num2 / num1);
                    break;

                case "=":
                    result = stk.pop();
                    System.out.println(result);

                    break;

                default:
                    // converts string to double and pushes to stack stk
                    // possible error if ele is just a normal string(not a string number)
                    stk.push(Double.parseDouble(ele));

            }
        }
    }
}
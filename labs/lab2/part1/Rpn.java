import java.util.Scanner;

public class Rpn {
    public static void main(String[] args) {

        // creates a scanner object
        Scanner scan  = new Scanner(System.in);

        // user inputs the Rpn expression
        String expression = scan.nextLine();
        
        // splits the string by each space and creates an array
        String[] expression_arr = expression.split(" ");
        
        // creates a object of class Stack from part 1 of lab
        Stack stk = new Stack();

        // loops through each ele(ment) of the created array expression_arr 
        for (String ele: expression_arr) {
           
            switch (ele) {
                case "+":
                    num1 = stk.pop();
                    num2 = stk.pop();
                    
                    stk.push(num1 + num2);
                    break;

                case "-":
                    num1 = stk.pop();
                    num2 = stk.pop();
                
                    stk.push(num1 - num2);
                    break;
                
                case "*":
                    num1 = stk.pop();
                    num2 = stk.pop();

                    stk.push(num1 * num2);

                    break;

                case "/":
                    num1 = stk.pop();
                    num2 = stk.pop();

                    stk.push(num1 / num2);
                    break;

                case "=":
                    result = stk.pop();
                    system.out.println(result);

                    break;

                default:
                    stk.push(Double.parseDouble(ele));

            }
        }
    }
}
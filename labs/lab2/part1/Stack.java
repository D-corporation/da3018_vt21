public class Stack {

    // when the stack is empty the top = -1
    // increases with 1 at each push
    // decreases with -1 at each pop
    private int top = -1; 

    // max number of elements in the stack
    // N-1: the stack is full
    // N: stack overflow
    private int N = 1000;

    private double[] array = new double[N];

    boolean is_empty() {

        // if top is -1 the stack is empty, returning true
        // else it is not empty, returning false
        if (top == -1) {
            return true;
        } else {
            return false;
        }
    } 

    void push(double x) {
        // top increases by 1
        top += 1;

        // inputs value x to the array at index top
        array[top] = x;
    }

    double pop() {
        // saves the value that are goint to be "popped" temporarily for return value
        double popped_value = array[top];

        // sets array value at index top to default value for double arrays which is 0.0d 
        array[top] = 0.0d;

        // top decreases by 1
        top -= 1;

        return popped_value;

    }
    
}
public class Stack {

    // when the stack is empty the top = -1
    // increases with 1 at each push
    // decreases with -1 at each pop
    private int top = -1; 

    // max number of elements in the stack
    // N-1: the stack is full
    // N: stack overflow
    private int N = 1000;

    private double[] array = new double[N-1];

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
        this.top += 1;

        array[top] = x;
    }

    double pop() {

        double popped_value = array[top];
        array[top] = 0.0;
        top -= 1;
        return popped_value;

    }
    
}
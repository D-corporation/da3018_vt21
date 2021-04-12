public class Rpn {
    public static void main(String[] args) {
        
        // Creates an object of stack
        Stack stk = new Stack();

        // pushes 
        stk.push(10);
        stk.push(19.1983);

        // pops
        System.out.println(stk.pop());

        System.out.println(stk.is_empty());
        
    }
}
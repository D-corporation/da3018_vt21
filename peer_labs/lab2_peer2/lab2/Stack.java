package lab2;

public class Stack {
	//We declare an empty global array
	public static double[] array = {};
	
	//The first operator checks whether our array
	//is empty or not
	public boolean is_empty() {
		if (array.length == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//Second operation adds an element x to the array
	//We declare holder so that we can add an extra element
	//by copying array and leaving an empty slot for x to fill
	public void push(double x) {
		double[] holder = new double[array.length+1];
		for (int i = 0; i < array.length; i++) {
			holder[i] = array[i];
		}
		holder[holder.length-1] = x;
		array = holder;
	}
	
	//The third operation will remove the last element of array
	//For this we define an array shorter with 1 fewer elements
	//holder lets us safely get all excluding last elements
	//in our array and return the removed element
	public double pop() {
		double[] holder = array;
		double[] shorter = new double[holder.length-1];
		
		for (int i = 0; i < shorter.length; i++) {
			shorter[i] = holder[i];
		}
		
		array = shorter;
		return holder[holder.length-1];
	}
	
	
}
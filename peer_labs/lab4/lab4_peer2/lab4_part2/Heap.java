package lab4_part2;

/*
 * Implement a min heap, the generic programming version
 *
 * Java does not support arrays for generic types very well, (see the
 * constuctor!) so in the stub below you find some examples of dealing with
 * that.  As the book "Thinking in Java" points out, stuff like this exists also
 * in the Java libraries.
 *
 * There is little reason to work directly with arrays when programming in
 * modern Java, one is probably better off with an ArrayList or other suitable
 * standard datastructure, but in this class we try to work "low level" and
 * using a high level datastructure to implement a heap beats the purpose
 * of trying to make our own datastructure!
 */
import java.lang.Exception;

public class Heap<E extends Comparable<E>> {
    private E[] heap_array;
    private int n_elems = 0;
    private int capacity;

    /**
     * Constructor
     * Note how we no longer use the simple Java array creation.
     * That is not possible with generics, for technical reasons.
     *
     * The tricks below causes compiler warnings, which are supressed here
     * because they are planned (in some sense) and supported by common
     * Java practice.
     */
    @SuppressWarnings("unchecked")
    public Heap(Class<E> c, int _capacity) {
        capacity = _capacity;
        heap_array = (E[]) java.lang.reflect.Array.newInstance(c, capacity+1);
    }

    /**
     * Private method for maintaining the heap.
     * @param i, index of the element to heapify from
     */
    private void heapify(int i) {
    	if((2*i) > n_elems) {
    		// Do nothing since there are no defined children
    	}
    	else if(n_elems == (2*i)) { // Case when left child is defined but right child is not
    		E current = heap_array[i];
    		E left_child = heap_array[2*i];
    		
    		if(left_child.compareTo(current) < 0) { // Switch current node and its left child if heap property is not met
    			heap_array[i] = left_child;
    			heap_array[2*i] = current;
    		}
    	
    	}
    	else { // Cases when both children are defined
    		E current = heap_array[i];
	        E left_child = heap_array[2*i];
	        E right_child = heap_array[2*i+1];
	        
	        if(left_child.compareTo(current) < 0 || right_child.compareTo(current) < 0) { // If current is "larger" than one of its kids we need to switch it for the smallest child
	        	if(left_child.compareTo(right_child) < 0) { // If left child is smallest
	        		heap_array[i] = left_child;
	        		heap_array[2*i] = current;
	        		heapify(2*i);
	        	}
	        	else { // If right child is smallest
	        		heap_array[i] = right_child;
	        		heap_array[2*i+1] = current;
	        		heapify(2*i+1);
	        	}
	        }
    	} 
    }

    public int capacity() {
        return capacity;
    }

    public int size() {
        return n_elems;
    }

    public boolean isEmpty() {
        return n_elems == 0;
    }

    /**
     * Add an element to the heap and ensure the heap property
     * Throws an exception if trying to add elements to a full heap.
     * @param x Element to add
     */
    public void insert(E x) throws Exception {
    	if(n_elems == capacity) {
    		throw new Exception("Error: can't insert " + x + " since the heap is full.");
    	}
    	
    	else {
    		n_elems++;
    		heap_array[n_elems] = x;
    		
    		for(int i = Integer.min(n_elems, capacity/2) ; i >= 1; i--){ // min(n_elems, capacity/2) since if n_elems > capacity/2 we don't need to perform heapify on the leaves.
    			this.heapify(i);
    		}
    	}
    }

    /**
     * Remove and return smallest element, and maintain the heap property.
     * Throws an exception if trying to extract an element from an empty heap.
     */
    public E extractMin() throws Exception {
    	if(n_elems == 0) {
    		throw new Exception("Error: can't extract from an empty heap");
    	}
    	
    	E return_value = heap_array[1];
    	
    	heap_array[1] = heap_array[n_elems]; // Put last element in array in first place
    	n_elems--; // Decrease number of elements counter by one
    	
    	this.heapify(1); // heapify the first element in the array. This is the only element that does not satisfy the heap property.
    	
    	return return_value;
    }

    /**
     * For convenience, a small program to test the code.
     * There are better ways of doing this kind of testing. See `junit`!
     *
     */
    static public void main(String args[]) {
        // Declare two heaps. Both should work nicely!
        // This time around, we store doubles instead of ints in one of the heaps.
        // Notice that we use wrapper classes Double and Integer instead of the primitive type double.
        // Java's primitive types have these wrappers for when a class is needed.
        Heap<Double> h1 = new Heap<Double>(Double.class, 100);
        Heap<Integer> h2 = new Heap<Integer>(Integer.class, 10);
        int data[] = {1, 4, 10, 14, 7, 9, 3, 2, 8, 16};


        //
        // Insert 1 element to heap 1, and several to heap 2.
        //
        System.out.println("Inserting data.");
        try {
            h1.insert(7.0);       // Insert a single element in heap 1

            // Insert several elements in heap 2. Heap 1 must not be affected.
            for (int x: data) {
                h2.insert(x);
            }
        } catch (Exception e) {
            System.err.println("During insertion:");
            System.err.println(e);
            System.exit(1);
        }

        if (h2.size() != data.length) {
            System.err.println("Error! Wrong number of elements in heap 2.");
        }


        //
        // Time to empty heap 2. Does that work?
        //
        try {
            System.out.println("Contents of heap 2, should come out sorted:");
            while (! h2.isEmpty()) {
                int x = h2.extractMin();
                System.out.println(x);
            }
            if (! h2.isEmpty()) {
                System.err.println("Error! Heap 2 has not been emptied!");
            }
            if (h1.size() != 1) {
                System.err.println("Error! Wrong number of elements in heap 1.");
            }

        } catch (Exception e) {
            System.err.println("During extraction:");
            System.err.println(e);
            System.exit(1);
        }

    }
}

package lab4_part1;

/*
 * Implement a min heap 
 */
import java.lang.Exception; 

public class Heap {                    // Note: just the skeleton of a class!
    private int heap_array[];
    private int n_elems = 0;
    private int capacity;

    // Constructor
    Heap(int _capacity) {
        capacity = _capacity;
        heap_array = new int[capacity+1];
    }

    /**
     * Private method for maintaining the heap.
     * @param i, index of the element to heapify from
     */
    public void heapify(int i) {
    	if((2*i) > n_elems) {
    		// Do nothing since there are no defined children
    	}
    	else if(n_elems == (2*i)) { // Case when left child is defined but right child is not
    		int current = heap_array[i];
    		int left_child = heap_array[2*i];
    		
    		if(current > left_child) { // Switch current node and its left child if heap property is not met
    			heap_array[i] = left_child;
    			heap_array[2*i] = current;
    		}
    	
    	}
    	else { // Cases when both children are defined
    		int current = heap_array[i];
	        int left_child = heap_array[2*i];
	        int right_child = heap_array[2*i+1];
	
	        if(current > left_child || current > right_child) { // If current is larger than one of its kids we need to switch it for the smallest child
	        	if(left_child < right_child) { // If left child is smallest
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
    public void insert(int x) throws Exception {
    	if(n_elems == capacity) {
    		throw new Exception("Error: can't insert " + x + " since the heap is full.");
    	}
    	
    	else {
    		n_elems++; // FIXME: should come after insertion to array, element not at root
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
    public int extractMin() throws Exception {
    	if(n_elems == 0) {
    		throw new Exception("Error: can't extract from an empty heap");
    	}
    	int return_value = heap_array[1];
    	
    	heap_array[1] = heap_array[n_elems]; // Put last element in array in first place
    	heap_array[n_elems] = 0; // set last element equal to zero
    	n_elems--; // Decrease number of elements counter by one
    	
    	this.heapify(1); // heapify the first element in the array. This is the only element that does not satisfy the heap property.
    	
    	return return_value;
    }

    /**
     * For convenience, a small program to test the code.
     * There are better ways of doing this kind of testing. See `junit`!
     * @throws Exception 
     *
     */
    static public void main(String args[]) { // A simple test program
        // Declare two heaps. Both should work nicely!
        Heap h1 = new Heap(100);
        Heap h2 = new Heap(10);
        int data[] = {1, 4, 10, 14, 7, 9, 3, 2, 8, 16};


        //
        // Insert 1 element to heap 1, and several to heap 2.
        //
        System.out.println("Inserting data.");
        try {
            h1.insert(7);       // Insert a single element in heap 1

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


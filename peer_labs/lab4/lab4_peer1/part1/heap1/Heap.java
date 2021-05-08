package heap1;

/**
 * Part 1:
 * Implement a min heap
 */
import java.lang.Exception;

public class Heap {                    
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
     * @param i, index of the element to heapify from and i should be the parent index
     */
    private void heapify(int i) {
    	int parent_index = i;
    	int left_index = 2*i;
    	int right_index = 2*i + 1;
    	if (left_index <= n_elems && right_index <= n_elems) {
    		int left_child = left(parent_index);
            int right_child = right(parent_index);
            int parent = heap_array[parent_index];
            if (parent > left_child || parent > right_child) {
            	if (left_child > right_child) {
            		heap_array[parent_index] = right_child;
            		heap_array[2*parent_index+1] = parent;
            		parent_index = 2*parent_index+1;
            		heapify(parent_index);
            	} else {
            		heap_array[parent_index] = left_child;
            		heap_array[2*parent_index] = parent;
            		parent_index = 2*parent_index;
            		heapify(parent_index);
            	}
            } else {
            	;
            } 
    	} else if (left_index <= n_elems && right_index > n_elems) {
            	int left_child = left(parent_index);
            	int parent = heap_array[parent_index];
            	if (parent > left_child) {
            		heap_array[parent_index] = left_child;
            		heap_array[2*parent_index] = parent;
            	} else {
            		;
            	}
    	} else {
    		;
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
        if (n_elems == capacity) {
        	throw new Exception("Heap is full.");
        } else {
        	inserthelp(x); // call a private method that hides the implementation details
        }
    }
    
    /**
     * Helper function to return the right child
     * @param i
     * @return
     */
    private int right(int i) {
    	return heap_array[2*i +1];
    }
    
    /**
     * Helper function to return left child
     * @param i
     * @return
     */
    private int left(int i) {
    	return heap_array[2*i];
    }
    
    /**
     * Helper function to return parent
     * @param i
     * @return
     */
    private int parent(int i) {
    	return heap_array[(int)Math.floor(i/2)];
    }
    
    /**
     * Private method to insert an integer into the heap
     * @param x
     */
    
    private void inserthelp(int x) {
    	n_elems += 1; // update the no. of elements in the array
    	heap_array[n_elems] = x; // put new element at the end of array
    	int current_elem = heap_array[n_elems];
    	int current_index = n_elems;
    	int parent = parent(current_index);
    	int parent_index = (int)Math.floor(current_index/2);
    	while (parent_index != 0 && parent > current_elem) {
    		if (parent_index == 0) {
    			;
    		} else {
        		heap_array[current_index] = parent;
        		heap_array[parent_index] = current_elem;
        		current_index = parent_index;
        		current_elem = heap_array[current_index];
        		parent = parent(current_index);
        		parent_index = (int)Math.floor(current_index/2); // next parent index
        	}
    	}
    	
    }


    /**
     * Remove and return smallest element, and maintain the heap property.
     * Throws an exception if trying to extract an element from an empty heap.
     */
    public int extractMin() throws Exception {
        if (isEmpty()) {
        	throw new Exception("Heap is empty.");
        } else {
        	int min_val = heap_array[1];
        	heap_array[1] = heap_array[n_elems];
        	heap_array[n_elems] = 0;
        	n_elems -= 1;
        	heapify(1);
        	return min_val;
        }
    }

    /**
     * For convenience, a small program to test the code.
     * There are better ways of doing this kind of testing. See `junit`!
     *
     */
    static public void main(String args[]) { // A simple test program
        // Declare two heaps. Both should work nicely!
        Heap h1 = new Heap(100);
        Heap h2 = new Heap(10);
        int data[] = {16, -4, 10, 14, 7, 9, 3, 2, 8, 1};


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
        
        /*
         * For my own testing. 
         */
//        try {
//        	int x = h1.extractMin();
//        	System.out.println(x);
//        	h1.extractMin();
//        } catch (Exception e) {
//        	System.out.println(e);
//        }
        
        
        
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

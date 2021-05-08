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
		heap_array = new int[capacity];
	}

	/**
	 * Removed the original heapify method due to it being
	 * a bad name, since heapify turns an array into 
	 * a heap, but we already have the array therefore
	 * using siftup and siftdown it makes it more
	 * usable in the already "premade" array we have. 
	 */

	/**
	 * In the instructions for the implemenation
	 * of the heap-min is said that the smallest
	 * element should be 1, which I think is
	 * wrong since it is used in the heap-max, and 
	 * because this is a heap- ming if the element is 1, 
	 * then what about 0? therefore in my siftdown implementation
	 * of the if statment it is "<" and not =<".
	 */
	
	//Siftdown (also called Heapify down) does two comparisons
	//per iteration between the left child and the right child
	//elements, than swaps the smallest one. This is used when 
	//removing the top element from the heap. It is done by
	//swaping the top element with the last element (leaf) and
	//removes the last element.  
	private void siftdown(int root) {
		int left = getLeft(root);
		int right = getRight(root);
		int smallest = root;
		// If the left child is the smallest compared to the root. 
		if (left < n_elems && heap_array[left] < heap_array[smallest])
			smallest = left;
		// If the right child is the smallest compared to the root.
		if (right < n_elems && heap_array[right] < heap_array[smallest])
			smallest = right;
		// If the smallest is the root it is done, otherwise
		// it will swap the smallest element with the root
		// and maintain the heap property. 
		if (smallest == root)
			return;
		int swap = heap_array[root];
		heap_array[root] = heap_array[smallest];
		heap_array[smallest] = swap;
		siftdown(smallest);
	}

	// Siftup (also called Heapify up) does one comparison 
	// per iteration between the child (current element) and
	// its parent element. This is used when a new element
	// is inserted into the heap, and adds to the bottom of
	// the heap tree. It moves up the heap tree and makes
	// comparison to the current parent element and swaps 
	// if needed. 
	private void siftup(int child) {
		if(child == 0) 
			return;
		int parent = getParent(child);
		// If child is larger than the parent
		// then it is done, otherwise it will 
		// swap them for maintaining the heap property.
		if (heap_array[child] >= heap_array[parent]) 
			return;
		int swap = heap_array[child];
		heap_array[child] = heap_array[parent];
		heap_array[parent] = swap;
		siftup(parent);
	}

	private int getLeft(int i) {
		return i * 2 + 1;
	}

	private int getRight(int i) {
		return i * 2 + 2;
	}

	private int getParent(int i) {
		return (i - 1) / 2;
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

	// It is like push, adds element to the array
	// and pushes the elements to the top.
	public void insert(int x) throws Exception {
		if (n_elems == capacity)
			throw new Exception("Heap is full");
		// Add new element to last position.
		heap_array[n_elems] = x;
		n_elems++;
		// Must know how many elements are added.
		// and configure it correctly.  
		siftup(n_elems - 1);
	}

	/**
	 * Remove and return smallest element, and maintain the heap property.
	 * Throws an exception if trying to extract an element from an empty heap.
	 */

	// It is like pop, returns the node of min
	// values from min heap after removing it
	// from the heap.  
	public int extractMin() throws Exception {
		// Gets the smallest element.
		int root = heap_array[0];
		// Gets the last element and decrements the element. 
		n_elems--;
		heap_array[0] = heap_array[n_elems];
		siftdown(0);
		return root; 	
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

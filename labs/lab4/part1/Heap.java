/*
 * Implement a min heap
 */
import java.lang.Exception;
import java.util.NoSuchElementException;

public class Heap {                    // Note: just the skeleton of a class!
    private int heap_array[];
    private int n_elems = 0; // heapsize
    private int capacity;

    // Constructor
    Heap(int _capacity) {
        capacity = _capacity;
        heap_array = new int[capacity];
    }

    /**
     * Private method for maintaining the heap.
     * @param i, index of the element to heapify from
     */
    private void heapify(int i) {
        
        int left = 2*i + 1; 
        int right = 2*i + 2;
        int smallest; 
        
        if (left < n_elems) { // checks if the left child exists
            if (heap_array[left] < heap_array[i]) { // check if left child of i is less than node i key
                smallest = left; // if left node is smaller than node i key, set smallest to left node key
            } else {
                smallest = i; // else we set smallest to node i
            }
            
            if (right < n_elems) { // checks if right child exists
                if (heap_array[right] < heap_array[smallest]) { // check if right node key of i is less than node i key
                    smallest = right; 
                }
            }

            if (smallest != i) {
                //swap
                int temp = heap_array[i];
                heap_array[i] = heap_array[smallest];
                heap_array[smallest] = temp;
    
                heapify(smallest);
            }
        } 

        // if parent of i is greater than node i swap and heapify
        if (heap_array [i] < heap_array[parent(i)]) {
            int temp = heap_array[i];
            heap_array[i] = heap_array[parent(i)];
            heap_array[parent(i)] = temp;

            heapify(parent(i));
        }
    }

    /**
     * 
     * @param i node
     * @return Index of parent of i
     */
    private int parent(int i) {
        return (int) Math.floor((i-1)/2);
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

        if (n_elems == capacity) { // checks if heap is full
            throw new NoSuchElementException("Heap is full");
        }

        heap_array[n_elems++] = x;

        if (n_elems > 1) {
            heapify(n_elems - 1);
        }

    }

    /**
     * Remove and return smallest element, and maintain the heap property.
     * Throws an exception if trying to extract an element from an empty heap.
     */
    public int extractMin() throws Exception {

        int root = heap_array[0]; // min element to return
        heap_array[0] = heap_array[n_elems - 1]; // set root to last element in heap
        heap_array[n_elems - 1] = 0; // "delete" last element in heap 
        n_elems--; // decrease number of elemnts
        heapify(0); // since heap property is broken we heapify the array

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

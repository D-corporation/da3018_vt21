package src;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

public class CourseArray {
    public ArrayList<String> courses = new ArrayList<String>(); // Store course names in this attribute


    CourseArray() {
        // Empty, but still needed.
    }

    /*
     * Copy constructor — new object with the same data as 'other'
     */
    CourseArray(CourseArray other) {
        courses.addAll(other.courses);     // Copy items from 'other' to this object
    }

    /*
     * selectionSort – implements the Selection Sort algorithm.
     *
     * Input: none
     * Output: none
     * Side effect: sorts the 'courses' attribute
     * Time complexity: quadratic in the number of elements in 'courses' array.
     */
    public void selectionSort() {
        // TODO: To implement

        int n = this.courses.size(); // number of elements to be sorted
        int min_index; // index of presumably the smallest element

        for (int i = 0; i < n - 1; i++) {
            min_index = i; 

            for (int j = i + 1; j < n; j++) {
                
                if (courses.get(j).compareTo(courses.get(min_index)) < 0) { // check if 
                    min_index = j;
                }
            }

            swap(min_index, i); 
        }
        
    }

    /**
     * Swap element at index1 for element at index2
     * @param index1 - integer of first index
     * @param index2 - integer of second index
     */
    private void swap(int index1, int index2) {
        String temp = courses.get(index1);

        courses.set(index1, courses.get(index2));
        courses.set(index2, temp);
    }



    /*
     * mergeSort - implements the Merge Sort algorithm
     *
     * Input: none
     * Output: none
     * Side effect: sorts the 'courses' attribute
     * Time complexity: O(n lg n) comparisons, where n is the number of elements in the course array.
     */
    public void mergeSort() {
        courses = mergeSort(courses);
    }

    private ArrayList<String> mergeSort(ArrayList<String> A) {
        
        int n = A.size();
        int mid;

        if (n < 2) {
            return A;
        } else {
            mid = (int) Math.floor(n/2);
            ArrayList<String> half1 = mergeSort(new ArrayList<> (A.subList(0, mid)));
            ArrayList<String> half2 = mergeSort(new ArrayList<> (A.subList(mid, n)));

            return merge(half1, half2);
        }

    }



    private ArrayList<String> merge(ArrayList<String> sortedArray1, ArrayList<String> sortedArray2) {
        int n1 = sortedArray1.size(); 
        int n2 = sortedArray2.size(); 
        
        ArrayList<String> mergedList = new ArrayList<>();
        
        int i = 0;
        int j = 0;

        while (i < n1 && j < n2) {
            if (sortedArray1.get(i).compareTo(sortedArray2.get(j)) < 0) {
                mergedList.add(sortedArray1.get(i)); 
                i++;
            } else {
                mergedList.add(sortedArray2.get(j));
                j++;
            }
        }

        while (i < n1) {
            mergedList.add(sortedArray1.get(i)); 
            i++;
        }

        while (j < n2) {
            mergedList.add(sortedArray2.get(j));
            j++;
        }

        return mergedList;
    }  

    /*
     * javaSort - use Java's library support for sorting.
     *
     * Input: none
     * Output: none
     * Side effect: sorts the 'courses' attribute
     */
    public void javaSort() {
        Collections.sort(courses);
    }


    /*
     * loadData - Convenience function. Reads lines from stdin and put them in 'courses'.
     */
    private void loadData() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String c = sc.nextLine();
            courses.add(c);
        }
    }

    public boolean sorted() {
        for (int i=1; i<courses.size(); i++) {
            if (courses.get(i).compareTo(courses.get(i-1)) < 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        // We create 3 CourseArray objects. They contain the same
        // data, but we can apply three different sorting algorithms on them independently.
        CourseArray courses1 = new CourseArray();
        courses1.loadData();    // Read course names from stdin

        CourseArray courses2 = new CourseArray(courses1); // Copy the data to two more arrays using the copy-constructor
        CourseArray courses3 = new CourseArray(courses1);

        long checkpoints[] = new long[4]; // To store timestamps in
        // Start tests
        checkpoints[0] = System.currentTimeMillis();
        // courses1.selectionSort();
        checkpoints[1] = System.currentTimeMillis();
        courses2.mergeSort();
        checkpoints[2] = System.currentTimeMillis();
        courses3.javaSort();
        checkpoints[3] = System.currentTimeMillis();

        // Ensure correct sorting
        if (!courses1.sorted()) {
            System.out.println("courses1 is not sorted");
        }
        if (!courses2.sorted()) {
            System.out.println("courses2 is not sorted");
        }
        if (!courses3.sorted()) {
            System.out.println("courses3 is not sorted");
        }

        String algs[] = {"", "selectionSort", "mergeSort", "javaSort"};
        // Output timing results
        for (int i=1; i<4; i++) {
            System.out.format("Time for task %s: %d ms", algs[i], checkpoints[i] - checkpoints[i-1]);
            System.out.println();
        }
    }
}

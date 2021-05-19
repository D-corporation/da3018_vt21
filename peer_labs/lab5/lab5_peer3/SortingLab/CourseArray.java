package SortingLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.Enumeration;


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
        // To implement
    	String currentMax = courses.get(0);
    	int currentMaxIndex = 0;
    	for(int i = courses.size(); i > 0; i--) {
    		for(int j = 0; j < i; j++) {
    			if(courses.get(j).compareTo(currentMax) >= 0){
    				currentMax = courses.get(j);
    				currentMaxIndex = j;
    			}
    		}
    		courses.add(i, currentMax);
    		courses.remove(currentMaxIndex);
    		currentMax = courses.get(0);
    		currentMaxIndex = 0;
    	}
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
        // To implement
    	courses = mergeSort(courses);
    }
    
    private ArrayList<String> mergeSort(ArrayList<String> a){
    	if(a.size() < 2) {
    		return a;
    	}
    	int midpoint = a.size() / 2;
    	ArrayList<String> left = new ArrayList<String>(a.subList(0, midpoint));
    	ArrayList<String> right = new ArrayList<String>(a.subList(midpoint, a.size()));
    	left = mergeSort(left);
    	right = mergeSort(right);
    	return merge(left, right);
    }
    
    private ArrayList<String> merge(ArrayList<String> left, ArrayList<String> right){
    	ArrayList<String> result = new ArrayList<String>();
    	
    	int i = 0; //This keeps track of our progress in right, haven't come up with a better name
    	
    	for(String s: left) {
    		while(right.size() > i && s.compareTo(right.get(i)) > 0) {
    			result.add(right.get(i));
    			i++;
    		}
    		result.add(s);
    	}
    	
//    	while(i < right.size()) {
//    		result.add(right.get(i));
//    		i++;
//    	}
    	
    	result.addAll(right.subList(i, right.size()));
    	
    	return result;
    }

    /*
     * javaSort - use Java's library support for sorting.
     *
     * Input: none
     * Output: none
     * Side effect: sorts the 'courses' attribute
     */
    public void javaSort() {
        // To implement
    	Collections.sort(this.courses);
    }
    
    
    /*
     * The longestSharedPrefix method has timecomplexity < O(k * n + n) = O(k * n).
     * 
     * This i because construction of the tree has O(k * n) time complexity:
     * the tree has at most height k and and at every level you go through the loop at most n times
     * since the total amount of elements among all buckets on a level is at most n.
     * 
     * Once the tree is constructed, at every subroot you do m-1 comparisons where m is the number
     * of children to that subroot to determine the set of longest shared prefixes to "pass upwards" in the tree.
     * If there is only one child to the subroot you do a constant amount of work, and there will never be more than n/2 subroots at a level k.
     * You therefore perform at most n/2 amount of constant work at every level k.
     * so this part of the algorithm takes at most k * n/2 * C + n * D amount of operations
     * for some constants C and D.
     * 
     * So in total we get O(k * n + k * n/2 + n) = O(k * n)
     */
    
    public ArrayList<String> longestSharedPrefix(){
    	return longestSharedPrefix(courses, 0);
    }
    
    private ArrayList<String> longestSharedPrefix(ArrayList<String> aList, int i) {
    	
    	Hashtable<String, ArrayList<String>> buckets = new Hashtable<String, ArrayList<String>>();
    	ArrayList<String> result = new ArrayList<String>();
    	
    	for(String s: aList) {		//This for loop is called a total of k * n times and performs a constant amount of work
    		if(s.length() > i) {
    			if(buckets.containsKey(s.substring(0, i + 1))) {
    				buckets.get(s.substring(0, i + 1)).add(s);
    			} else {
    				ArrayList<String> bucketList = new ArrayList<String>();
    				bucketList.add(s);
    				buckets.put(s.substring(0, i + 1), bucketList);
    			}
    		}
    	}
    	
    	
    	
    	for(Enumeration<String> bucket = buckets.keys(); bucket.hasMoreElements();) {
    		ArrayList<String> currentBucket = buckets.get(bucket.nextElement());
    		if(currentBucket.size() >= 2) {
    			ArrayList<String> candidateResult = longestSharedPrefix(currentBucket, i + 1);
    			if(result.isEmpty() || candidateResult.get(0).length() > result.get(0).length())  //the second comparison and the else is only called if there is more than one child so if there's only a sinlge child at this node it's a constant amount of work
    			{	
    				result = candidateResult;
    			} else if (candidateResult.get(0).length() == result.get(0).length()) {
    				result.addAll(candidateResult);
    			}
    		}
    	}
    	
    	if(result.size() == 0) {  //this only happens if we're at a subroot whose children are leaves
    		result.add(aList.get(0).substring(0, i));
    		return result;
    	} 
    	
    	return result;
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
        sc.close();
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
        CourseArray courses4 = new CourseArray(courses1);

        long checkpoints[] = new long[5]; // To store timestamps in
        // Start tests
        checkpoints[0] = System.currentTimeMillis();
        courses1.selectionSort();
        checkpoints[1] = System.currentTimeMillis();
        courses2.mergeSort();
        checkpoints[2] = System.currentTimeMillis();
        courses3.javaSort();
        checkpoints[3] = System.currentTimeMillis();
        ArrayList<String> prefix = courses4.longestSharedPrefix();
        checkpoints[4] = System.currentTimeMillis();
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

        String algs[] = {"", "selectionSort", "mergeSort", "javaSort", "longestSharedPrefix"};
        // Output timing results
        for (int i=1; i<5; i++) {
            System.out.format("Time for task %s: %d ms", algs[i], checkpoints[i] - checkpoints[i-1]);
            System.out.println();
        }
        System.out.println("longest prefix(es) is: " + prefix);
    }
}

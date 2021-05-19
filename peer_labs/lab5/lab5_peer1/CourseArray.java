

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
    
    private void swap(int i, int j) {
    	String string_at_i = courses.get(i);
    	courses.set(i, courses.get(j));
    	courses.set(j, string_at_i);    	
    }
    
    private int max_at_index(int startIndex) {
    	int currentMaxAtIndex = startIndex;
        for (int i = startIndex; i<courses.size(); i++) {
        	String currentMax=courses.get(currentMaxAtIndex);

        	if (currentMax.compareTo(courses.get(i))>0) {
        		currentMaxAtIndex = i;
        	}
        }
        return currentMaxAtIndex;

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
        for (int i = 0; i<courses.size(); i++) {
        	int j = max_at_index(i);
        	swap(i,j);
        }
    }
    
    private ArrayList<String> recursiveMerge(ArrayList<String> a, ArrayList<String> b) {
    	if (a.size()==0) { return b; }
    	if (b.size()==0) { return a; }
    	
    	String a_0=a.get(0);
    	String b_0=b.get(0);
        ArrayList<String> sorted_list = new ArrayList<String>();

    	if (a_0.compareTo(b_0)<0) {
    		sorted_list.add(a.remove(0));
    		sorted_list.addAll(merge(a, b));
    	}
    	else {
    		sorted_list.add(b.remove(0));
    		sorted_list.addAll(merge(a, b));
    	}

		return sorted_list;
    }
    
    
    private ArrayList<String> merge(ArrayList<String> a, ArrayList<String> b) {
        
    	ArrayList<String> merged_list = new ArrayList<String>();
    	int i=0;
    	int j=0;
    	
    	while (i<a.size() && j<b.size()) {
           	String a_i=a.get(i);
        	String b_j=b.get(j);
        	if (a_i.compareTo(b_j)<0) {
        		merged_list.add(a_i);
        		i++;
        	}
        	else {
        		merged_list.add(b_j);     
            	j++;
        	}	
    	}
    	
    	while (i<a.size()) {
    		merged_list.add(a.get(i));
    		i++;
    	}
    	
    	while (j<b.size()) {
    		merged_list.add(b.get(j));
    		j++;
    	}
    	
		return merged_list;
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
    
    private ArrayList<String> mergeSort(ArrayList<String> aList) {
    	if (aList.size()<2) {
    		return aList;
    	}
    	
  		int mid = aList.size()/2;
		ArrayList<String> part1 = new ArrayList<String>(aList.subList(0, mid));
		ArrayList<String> part2 = new ArrayList<String>(aList.subList(mid, aList.size()));

		return merge(mergeSort(part1), mergeSort(part2));    	
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
     * sharedPrefix - finds the longest shared prefix of two strings.
     *
     * Input: string a, string b, 
     * Output: longest shared prefix
     * Side effect: none
     * Complexity: O(k), where k is the longest course name in the set. 
     */
    private String sharedPrefix(String a, String b) {  
    	if (a.length()==0) { return ""; } 
    	if (b.length()==0) { return ""; } 
    	if(a.charAt(0) != b.charAt(0)) { return ""; }
    	
		return a.substring(0,1)+sharedPrefix(a.substring(1), b.substring(1));
    }
    
    /*
     * currentLongestPrefix - finds the longest shared prefix of the string contained at the "start" of the array list.
     *
     * Input: int startIndex, 
     * Output: longest prefix of 
     * Side effect: none
     * Complexity: O(k*n), where k is the longest course name in the set,
     *                           n is the number of courses. 
     */
    private String currentLongestPrefix(int startIndex) {
    	String currentLongestPrefix = "";
    	
    	if (courses.size()-startIndex!=0) {
    		for (int i=startIndex+1; i<courses.size(); i++) {
    			String kandidate = sharedPrefix(courses.get(startIndex), courses.get(i));
    			if (currentLongestPrefix.length() < kandidate.length()) {
    				currentLongestPrefix=kandidate;
    			}
    		}
    	}
    	return currentLongestPrefix;
    }
    /*
     * currentLongestPrefix - finds the longest shared prefix of the string contained at the "start" of the array list.
     *
     * Input: none
     * Output: longest prefix in the course set
     * Side effect: none
     * Complexity: O(k*n^2), where k is the longest course name in the set,
     *                           n is the number of courses. 
     */
    public String longestPrefix() {
    	String currentLongestPrefix = "";
    	
		for (int i=0; i<courses.size(); i++) {
			String kandidate = currentLongestPrefix(i);
			if (currentLongestPrefix.length() < kandidate.length()) {
				currentLongestPrefix=kandidate;
			}
		}
    	return currentLongestPrefix;
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
    
    public void print() {
        for (int i=0; i<courses.size(); i++) {
        	System.out.println(courses.get(i));
        }
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
        String longest =courses4.longestPrefix();
        checkpoints[4] = System.currentTimeMillis();
        
        System.out.println(longest);

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

        String algs[] = {"", "selectionSort", "mergeSort", "javaSort", "longestPrefix"};
        // Output timing results
        for (int i=1; i<5; i++) {
            System.out.format("Time for task %s: %d ms", algs[i], checkpoints[i] - checkpoints[i-1]);
            System.out.println();
        }
    }
}

package heap2;

/**
 * Part 3
 * Program for command line processing:
 * 
 * Type 1) 
 * Program is contained in package "heap2", which is a directory. The java files
 * are contained in the "heap2" directory. So one can only run the java files
 * from one level above "heap2". Do this at command line:
 * javac heap2/Course.java
 * java heap2/Course < course_data.txt
 * 
 * Type 2) 
 * Do this at command line
 * java -jar course.jar < course_data.txt
 */

import java.util.Scanner;


public class Course implements Comparable<Course> {
    private String code;
    private String name;

    public Course(String courseCode, String courseName) {
        code = courseCode;
        name = courseName;
    }
 /**
  * Public implementation of the compareTo method required of Comparable
  */
    public int compareTo(Course b) {
    	return compare(this.code, b.code); // private method that does the comparison
    }
    
    /**
     * Private helper function for compareTo method
     * @param x: main string
     * @param y: comparison string
     * @return: -1 if main string is smaller than comparison string;
     * 			1 if main string is bigger than comparison string;
     * 			0 if main string is equals to comparison string
     */
    
    private static int compare(String x, String y) {
    	if (x.compareTo(y)<0) {
    		return -1;
    	} else if (x.compareTo(y)>0) {
    		return 1;
    	} else {
    		return 0;
    	}
    }
    
    
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
		Heap<Course> course_heap = new Heap<Course>(Course.class, 100); //kept heap capacity at 100
		try {
		while (sc.hasNextLine()) {
    		String input = sc.nextLine();
    		String[] array = input.split(" ", 2);
            Course new_course = new Course(array[0], array[1]);
            course_heap.insert(new_course);

    		}
    		sc.close();
    		Course x = course_heap.extractMin();
    		String y = x.code;
    		String z = x.name;
    		System.out.println(y + " " + z);
    	} catch (Exception e) {
    		System.err.println(e);
	}
    		
   }
    /**
	 * For testing using console. For my own reference. 
	 */
   
//    	try {
//    	Heap<Course> course_heap = new Heap<Course>(Course.class, 13);
//			course_heap.insert(new Course("DA3018", "Computer Science"));
//			//courses.insert(new Course("MM2001", "Matematik I"));
//			course_heap.insert(new Course("DA2004", "Programmeringsteknik"));
//			course_heap.insert(new Course("MM2001", "Matematik I"));
//			course_heap.insert(new Course("MT7015", "Statistical Learning"));
//			course_heap.insert(new Course("DA4000", "Intro to Machine Learning"));
//			course_heap.insert(new Course("MM5016", "Numerical Analysis"));
//			course_heap.insert(new Course("DA4002", "Software development"));
//			course_heap.insert(new Course("DA5001", "Numerical Analysis II"));
//			course_heap.insert(new Course("DA4005", "Algorithm and Complexity"));
//			course_heap.insert(new Course("DA4003", "Programming paradigms"));
//			course_heap.insert(new Course("DA4001", "Database technique"));
//			course_heap.insert(new Course("MT7039", "Unsupervised Learning"));
//
//			Course x = course_heap.extractMin();
//			String y = x.code;
//			String z = x.name;
//			System.out.println(y + " " + z);
//			
//		} catch (Exception e) {
//			System.err.println(e);
//		}
//		
//    }
    
  }


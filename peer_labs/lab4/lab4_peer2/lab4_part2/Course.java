package lab4_part2;

import java.util.Scanner;

public class Course implements Comparable<Course> {
    private String code;
    private String name;

    public Course(String courseCode, String courseName) {
        code = courseCode;
        name = courseName;
    }
    
    static public void main(String args[]) throws Exception {
    	Heap<Course> course_heap = new Heap<Course>(Course.class, 100);
    	
    	Scanner sc = new Scanner(System.in);
    	
    	while(sc.hasNextLine()) {
    		String input = sc.nextLine().stripTrailing(); // stripTrailing removes all whitespaces at the end of the string.
    		
    		String [] course_info = input.split("\\s+", 2);
    		
    		if(input.isEmpty()) { // if input is an empty row, skip to next iteration
    			continue;
    		}
    		
    		if(course_info.length == 1) { // if input length is equal to one we are given to little information. Throw exception
    			sc.close();
    			throw new Exception("Error: invalid input length. See line with course code " + "\"" + course_info[0] +"\"");
    		}
    		
    		Course course = new Course(course_info[0], course_info[1]);
    		
    		course_heap.insert(course);    		
    	}
    	
    	Course first = course_heap.extractMin();
    	
    	System.out.println(first.code + " " + first.name);
    	
	sc.close();
    }


	public int compareTo(Course course) {
		return this.code.compareTo(course.code);
	}

}


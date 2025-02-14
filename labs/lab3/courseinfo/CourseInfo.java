package courseinfo;

import courseinfo.BinarySearchTree.BSTNode;

public class CourseInfo {

	public static void main(String[] args) {
		BinarySearchTree<BSTNode> courses = new BinarySearchTree<BSTNode>();

		courses.insert("DA3018", "Computer Science", 7.5);
		courses.insert("MM2001", "Matematik I", 30.0);
		courses.insert("DA2004", "Programmeringsteknik", 7.5);
		courses.insert("DA4002", "Mjukvaruutveckling", 7.5);
		// courses.insert("DA4002", "test", 60);
		courses.insert("DA4004", "Introduktion till maskininlarning", 7.5);
		courses.insert("DA4005", "Algoritmer och komplexitet", 7.5);
		courses.insert("MM5016", "Numerisk analys I", 7.5);
		
		int n = courses.size();
		System.out.printf("There are %d courses in the database.\n", n);
		
		String name = courses.find("DA4002").getCourseName();
		System.out.printf("Name: %s\n", name);
		
		// FIXME:
		for (BSTNode node: courses) {
			System.out.println(node.getCourseCode());
		}

	}

}

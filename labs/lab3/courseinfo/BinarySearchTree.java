package courseinfo;

import java.util.Iterator;
import java.util.NoSuchElementException;
// import java.lang.Iterable;

/**
 * Store course information in a binary search tree
 * @param <BSTNode>
 *
 */
public class BinarySearchTree<BSTNode> implements Iterable<BSTNode> {
	/**
	 * Attributes
     */
	BSTNode root=null;      // The root of the tree. Start with an empty tree.

	public BinarySearchTree() {
		// Empty constructor?
	}

	/**
	 * Public interface for inserting data into the datastructure. Utilizes
	 * a private, more technical method.
	 * @param courseCode, eg "DA3018"
	 * @param courseName, eg "Computer Science"
	 * @param courseCredits, eg 7,5
	 */
	public void insert(String courseCode, String courseName, double courseCredits) {
		BSTNode node = new BSTNode(courseCode, courseName, courseCredits);
		root = insert(root, node); // Call to private method with the same name, but other parameters
	}

	/**
	 * Insert 'node' into the tree pointed at by 'root'.
	 * @returns The node that should be the root of this subtree.
	 * @param root
	 * @param node
	 *
	 * WARNING! This method has a bug, it does not behave according to specification!
	 */
	private BSTNode insert(BSTNode root, BSTNode node) {
		if (root==null) {
			return node; // The easy case. Let the current node be the root of a new (sub?)tree.
		} else {
			String currentKey = root.getCourseCode();
			BSTNode left = root.getLeftChild();
			BSTNode right = root.getRightChild();

			if (node.getCourseCode().compareTo(currentKey) < 0) { // left string "before" right string
				left = insert(left, node); // go left
			} else if (node.getCourseCode().compareTo(currentKey) > 0) { // left string "after" right string
				right = insert(right, node); // go right: fixes bug
			} else if (node.getCourseCode().compareTo(currentKey) == 0) {
				root = node; // in the case of we are inserting new course info in an alreay existing node
			}

			root.setChildren(left, right);
			return root;
		}
	}

	/**
	 * size: Count the number of nodes in the search tree
	 */

	public int size() {
	    return size(this.root); 
	}

	private int size(BSTNode node) {
		if (node == null) {
			return 0;
		} else {
			return (size(node.getLeftChild()) + size(node.getRightChild()) + 1);
		}
	}

	/**
	 * find: Find a course given a course code
	 */
	public BSTNode find(String courseCode) {

		return (find(courseCode, this.root));
	
	}

	private BSTNode find(String courseCode, BSTNode node) {
		// TODO: optimize and check for certain conditions like node is null.
		if (node == null) {
			// We need to return a BSTNode object
			// problem is if we return null we will get an error
			// when we call courses.find("courseCode").getCourseName()
			// instead we create a new BSTNode with CourseName = Course code does not exist
			BSTNode nullNode = new BSTNode("", "Course code does not exist", 0.0);
			return nullNode;
		} else if (node.getCourseCode() == courseCode) {
			return node;
		} else if (courseCode.compareTo(node.getCourseCode()) < 0) {
			return (find(courseCode, node.getLeftChild()));
		} else {
			return (find(courseCode, node.getRightChild()));
		}
	}

	public Iterator<BSTNode> iterator() {
		// return an objec that implements Iterable<BSTNode> !
		// TODO: Implement methods
		// 	- hasNext()
		// 	- next()
		// check page 288 Intoduction to algorithms, InOrder Tree Walk

		return new Iterator<BSTNode>();

	}
	
	private class NodeIterator implements Iterator<BSTNode> {
		
		private BSTNode current;

		public NodeIterator() {
			this.current = root;
		}

		public boolean hasNext() {
			// FIXME: should not return false
			return this.current != null;
		}
		public BSTNode next() {
			return this.current.getLeftChild();
		}


	}


	/**
	 * Nodes in the search tree
	 * This class should be sufficient for the project.
	 *
	 */
	public class BSTNode {
		private String courseCode;
		private String courseName;
		private double credits;
		private BSTNode left = null;
		private BSTNode right = null;

		/**
		 * Constructor
		 *
		 */
		public BSTNode(String code, String name, double credits) {
			this.courseCode = code;
			this.courseName = name;
			this.credits = credits;
		}

		/**
		 * Specify the children of the current node
		 * @param left
		 * @param right
		 */
		public void setChildren(BSTNode left, BSTNode right) {
			this.left = left;
			this.right = right;
		}

		public String getCourseCode() {
			return courseCode;
		}

		public String getCourseName() {
			return courseName;
		}

		public double getCredits() {
			return credits;
		}

		public BSTNode getLeftChild() {
			return left;
		}

		public BSTNode getRightChild() {
			return right;
		}



	}
}

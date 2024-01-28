package bst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BinarySearchTree<E> {
	BinaryNode<E> root; // Används också i BSTVisaulizer
	int size; // Används också i BSTVisaulizer
	private Comparator<E> comp;

	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		size = 0;
		root = null;
		comp = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
	}

	/**
	 * Constructs an empty binary search tree, sorted according to the specified
	 * comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		size = 0;
		root = null;
		this.comp = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {

		if (root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		}

		return add(root, x);
	}

	private boolean add(BinaryNode<E> node, E x) {
		if (comp.compare(node.element, x) == 0) { // if the root of the new subtree is the same we return false
			return false;
		}

		if (comp.compare(x, node.element) < 0) { // if the element is less than the root of the subtree we go left
			if (node.left == null) { // if root is null on the left side we can just insert the element
				node.left = new BinaryNode<E>(x);
				size++;
				return true;
			} else { // else we go to the next left subtree
				return add(node.left, x);
			}
		} else if (comp.compare(x, node.element) > 0) {
			if (node.right == null) {
				node.right = new BinaryNode<E>(x);
				size++;
				return true;
			} else {
				return add(node.right, x);
			}
		}
		return false;
	}

	public int nbrLeaves() {
		return nbrLeaves(root);
	}

	private int nbrLeaves(BinaryNode<E> n) {
		if (n == null) {
			return 0;
		} else if (n.left == null && n.right == null) {
			return 1;
		} else {
			return nbrLeaves(n.left) + nbrLeaves(n.right);
		}
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}

	private int height(BinaryNode<E> root) {
		if (root == null) {
			return 0;
		}

		int left = 0;
		int right = 0;

		if (root.left != null) {
			left = height(root.left);
		}

		if (root.right != null) {
			right = height(root.right);
		}

		int max = Math.max(left, right);

		return 1 + max;
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}

	private void printTree(BinaryNode<E> root) {
		if (root == null) {
			return;
		} else {
			printTree(root.left);
			System.out.println(root.element + "");
			printTree(root.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> sorted = new ArrayList<>();
		toArray(root, sorted);

		root = buildTree(sorted, 0, sorted.size() - 1);
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if (n != null) {
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	}

	/*
	 * Builds a complete tree from the elements from position first to
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (first > last) {
			return null;
		}

		int mid = (first + last) / 2;

		BinaryNode<E> n = new BinaryNode<E>(sorted.get(mid));

		n.left = buildTree(sorted, first, mid - 1);
		n.right = buildTree(sorted, mid + 1, last);

		return n;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.add(50);
		bst.add(20);
		bst.add(10);
		bst.add(80);
		bst.add(60);
		bst.add(70);
		bst.add(90);
		bst.rebuild();

		BSTVisualizer vis = new BSTVisualizer("Binary Search Tree", 500, 500);
		vis.drawTree(bst);
	}

}

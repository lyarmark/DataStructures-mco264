package balancedBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BalancedBinaryTree<T extends Comparable<T>> {
	private BNode<T> root;
	private boolean found; // used by remove methods

	public BalancedBinaryTree() {
		root = null; // empty tree
	}

	// recursive insert method
	public boolean insertRecur(T data) {
		BNode<T> tree = root;
		if (root == null) {
			root = new BNode<T>(data);
			return true;
		} else {
			return insertTryAgain(tree, data);
		}
	}

	private boolean insertTryAgain(BNode<T> root, T data) {
		if (data.compareTo(root.getData()) < 0) {
			// this data value belongs in left branch
			if (root.getLC() == null) {
				// left child is empty, insert data right there
				root.setLC(new BNode<T>(data));
				return true;
			} else {// find next available spot along left branch
				return insertTryAgain(root.getLC(), data);

			}
		} else

		if (data.compareTo(root.getData()) > 0) {
			// this data value belongs in right branch
			if (root.getRC() == null) {
				root.setRC(new BNode<T>(data));
				return true;

			} else { // find next available spot along right branch
				return insertTryAgain(root.getRC(), data);

			}

		}

		else
			return false; // duplicate value

	}

	public boolean removeVal(T value) {
		// to remove a value must start searching for it at the root
		root = removeNode(value, root);
		return found; // return value in instance variable set by private method
	}

	private BNode<T> removeNode(T value, BNode<T> tree) {
		// looks for value in the subtree
		if (tree == null)
			found = false;
		else if (value.compareTo(tree.getData()) < 0)
			// recursive call further down the left side of tree
			// might have to reset links if a node further down
			// is set to null
			tree.setLC(removeNode(value, tree.getLC()));
		else if (value.compareTo(tree.getData()) > 0)
			// recursive call further down the right side of the tree
			// might have to reset links if a node further down
			// is set to null
			tree.setRC(removeNode(value, tree.getRC()));
		else { // found the value , now remove that data from
				// the tree
			tree = removeData(tree);
			found = true;

		}
		return tree;
	}

	private BNode<T> removeData(BNode<T> tree) {
		// case 1 and 2: subtree just has one child branch so return that
		// branch and link that branch to previous
		// part of tree, basically eliminating the BNode
		// in which the data was found
		if (tree.getLC() == null)
			return tree.getRC();
		else if (tree.getRC() == null)
			return tree.getLC();
		else { // data is in a BNode that has two children.
				// It is too complicated to remove this type of Node
				// Instead do the following:
				// a. Replace the data in that BNode with data that
				// logically precedes that data - this data will be found in
				// a leaf BNode
				// b. eliminate the leaf BNode by reinvoking the
				// removeNode() method
			T data = findPredecessor(tree.getLC());
			tree.setData(data);
			tree.setLC(removeNode(data, tree.getLC()));
			return tree;

		}

	}

	private T findPredecessor(BNode<T> tree) {
		// the Node that contains data that precedes a Node
		// can be found by going down till one hits the right most leaf
		// of its left branch
		while (tree.getRC() != null) {
			tree = tree.getRC();

		}
		return tree.getData();
	}

	public void traverseInOrder() {
		ArrayList<BNode<T>> data = getSortedTree(this.root);
		System.out.println(data.toString());
	}

	private ArrayList<BNode<T>> getSortedTree(BNode<T> root) {
		ArrayList<BNode<T>> data = new ArrayList<BNode<T>>();
		traverseI(root.getLC(), data);
		data.add(root);
		traverseI(root.getRC(), data);

		return data;
	}

	private void traverseI(BNode<T> root, ArrayList<BNode<T>> data) {
		if (root == null)
			// returns to the previous iteration it was doing of this method
			return; // anchor case
		traverseI(root.getLC(), data);
		data.add(root);
		traverseI(root.getRC(), data);
	}

	public void balanceTree() {
		ArrayList<BNode<T>> data = getSortedTree(root);

		int rootIndex = data.size() / 2;
		root = data.get((int) rootIndex);
		balanceBranch(data, root, rootIndex);
	}

	private void balanceBranch(List<BNode<T>> splitData, BNode<T> currentRoot, int middleIndex) {
		// currentRoot is set to 2nd quarter

		// set left child with 1st quarter
		int half1 = middleIndex / 2; // mid of 2nd 1/2, starting count at 0

		if (splitData.get(half1) != currentRoot) {
			currentRoot.setLC(splitData.get(half1));
			currentRoot.getLC().setLC(null);
			currentRoot.getLC().setRC(null);

		} else {
			currentRoot.setLC(null);
		}

		// set right child to 3rd quarter
		int half2 = (splitData.size() - (middleIndex + 1)) / 2;
		int quarter3 = middleIndex + 1 + half2; // size of 2nd half /2

		if (splitData.get(splitData.size() - 1) != currentRoot) {
			currentRoot.setRC(splitData.get(quarter3));
			currentRoot.getRC().setLC(null);
			currentRoot.getRC().setRC(null);

		} else {
			currentRoot.setRC(null);
		}

		// only need recursive call if list has more than current root and
		// children
		// balance left
		if (splitData.size() > 3) {

			balanceBranch(splitData.subList(0, (middleIndex)), currentRoot.getLC(), half1);
			// balance right
			balanceBranch(splitData.subList(middleIndex + 1, splitData.size()), currentRoot.getRC(), half2);

		} else {
			return;
		}
	}
}

package balancedBinaryTree;

import java.util.ArrayList;

public class UseBTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BalancedBinaryTree<String> myData = new BalancedBinaryTree<String>();

		myData.insertRecur("F");
		myData.insertRecur("D");
		myData.insertRecur("Z");
		myData.insertRecur("K");
		myData.insertRecur("A");
		myData.insertRecur("E");
		myData.insertRecur("G");
		myData.insertRecur("S");
		myData.insertRecur("M");
		myData.insertRecur("N");
		myData.insertRecur("L");
		myData.insertRecur("U");
		myData.insertRecur("W");

		System.out.println("traverse InOrder");
		myData.traverseInOrder();

		System.out.println("\ntraverse PreOrder");
		myData.traversePreOrder();

		System.out.println("\nBalancing ...");
		myData.balanceTree();

		System.out.println("traverse InOrder");
		myData.traverseInOrder();

		System.out.println("\ntraverse PreOrder");
		myData.traversePreOrder();

		System.out.println("\nGet existing data");
		System.out.println(myData.get("D"));

		System.out.println("Removing data ...");
		myData.removeVal("D");

		System.out.println("Get non existent data");
		System.out.println(myData.get("D"));

		System.out.println("\nBalancing again");
		myData.balanceTree();

		System.out.println("traverse InOrder");
		myData.traverseInOrder();

		System.out.println("\ntraverse PreOrder");
		myData.traversePreOrder();
	}

}

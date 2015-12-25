package trees;

import java.util.ArrayList;

public class UseBTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BalancedBinaryTree<String> myData= new BalancedBinaryTree<String>();
		/*myData.insert("corn");
		myData.insert("potatoes");
		myData.insert("apples");
		myData.insert("carrots");
		myData.insert("stringbeans");
		myData.insert("squash");
		myData.insert("onions");
		System.out.println("PreOrder");
		*/
		myData.insertRecur("F");
		myData.insertRecur("D");
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
		//System.out.println("PreOrder");
		//myData.traversePreOrder();
		System.out.println("InOrder");
		ArrayList<String> list = myData.traverseInOrder();
		System.out.println(list);
		myData.removeVal("D");
		System.out.println("InOrder after deletion");
		myData.traverse();
		
	}

}

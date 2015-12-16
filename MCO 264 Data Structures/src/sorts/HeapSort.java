package sorts;

public class HeapSort {
	//meant for sorting tree, but also works with array
	//a tree will be lopsided it it starts with a 1 (hence, Btrees)
	
	//Rule 1.	parent needs to be greater than children
	//Rule 2.	fill one level before starting the next level
	
	//every child has 2 parents
	
	//if you try adding a number to a tree that is greater than the parent,
	//switch the numbers in the tree
	//and the location in the array
	
	//find out who a number's parent is by dividing it by:
	//(currentIndex-1)/2
	//if it's bigger, swap. then compare again
	
	//top will always be biggest
	
	//find children:
	//(index*2), (index*2 +1)	?is this true?
	
	//parent of an element = (index-1)/2
	
	//NEXT CLASS STARTED HERE
	/*
	 * a vector is like an arraylist
	 * the Heap is a wrapper. the vector can only be used like a heap
	 * like making a stack as a linkedList.
	 * the linkedlist can only be used like a stack
	 * 
	 * a heap keeps track of:
	 * number of elements = level we're up to
	 * aka size = next position
	 * and
	 * last value entered
	 * 
	 * code needs to be general enough to work under almost any condition
	 * 
	 * 
	 * after finish entering values into heap, need to sort the array.
	 * the heap is not by definition sorted, it just makes it easier to sort
	 * prereq is that heap is not sorted
	 * because assume that first element is largest and ever parent is greater than children
	 */
	
}

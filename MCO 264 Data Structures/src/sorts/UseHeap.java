package sorts;

import java.util.Random;
public class UseHeap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Heap<Integer> values = new Heap<Integer>();
       Random numGenerator = new Random();
       Integer nextVal;
       for (int i = 0 ; i< 5; i++){
    	   nextVal = numGenerator.nextInt(100);
    	   values.add(nextVal);  //add value to the heap
    	   
    	   
       }
       //display the heap
       values.display();
       
       //sort the heap
       values.sort();
       
       //display the sorted heap
       values.display();
	}

}
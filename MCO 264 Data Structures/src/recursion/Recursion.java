package recursion;

public class Recursion {
	
	//recursion keeps a stack with the different values in the variables at each point
	//stored on a stack, and it keeps pushing
	//then pops each one as the variable value
	
	
	//simple message
	public static void Message(int num) {
		for (int i = num; i < num; i++) {
			System.out.println("Recursion is fun");
		}
	}
	
	//recur message
	public static void RecurMessage(int num) {
		//anchor case
		if (num == 0) return;
		
		System.out.println("Recursion is fun");
		System.out.println("number = " + num);
		RecurMessage(num-1);

		System.out.println("num = " + num);
	}
	
	public static void main(String[] args) {
		RecurMessage(5);
	}
}

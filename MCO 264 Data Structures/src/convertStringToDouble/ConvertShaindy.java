package convertStringToDouble;

public class ConvertShaindy {

	public static void main(String[] args){
		
		String s1 = "43.23";
		double d = atof(s1);
		System.out.println(d);
	}
	
	public static double atof(String s1){
		double result = 0;
		
		for(int i= s1.length()-1; i >= 0; i--){
			
			if(s1.charAt(i)== '.'){
				
				for(int j = i; j > 0; j--){
				result /= 10;
				}
			}
			
			else{
				if(result > 1){
					result = result  + ((s1.charAt(i) - '0') * 10);
				}
				else{
					result += s1.charAt(i) - '0';
				}
			}
		}
		
		return result;
	}
}
package convertStringToDouble;

public class ConvertLeah {
	public static void main(String[] args) {

		String s1 = "43.32";
		System.out.println(atof(s1));

	}

	public static double atof(String s1) {

		double rightOfDecimal = 0;
		double leftOfDecimal = 0;
		int numberOfDecimalSpaces = -1;
		double answer;

		for (int i = s1.length() - 1; i >= 0; i--) {
			if (String.valueOf(s1.charAt(i)).equals(".")) {
				numberOfDecimalSpaces = s1.length() - i - 1;
				continue;
			}

			if (numberOfDecimalSpaces == -1) {
				rightOfDecimal += Integer.parseInt(String.valueOf(s1.charAt(i)))
						* (Math.pow(10, (s1.length() - 1) - i));
			}

			else {
				leftOfDecimal += Integer.parseInt(String.valueOf(s1.charAt(i))) * (Math.pow(10, numberOfDecimalSpaces - i - 1));
			}
		}

		answer = leftOfDecimal + (rightOfDecimal * (Math.pow(.1, numberOfDecimalSpaces)));
		return answer;
	}
}

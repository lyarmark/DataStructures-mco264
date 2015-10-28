package school;

public enum Grade {
	APLUS(4.0), A(4.0), AMINUS(3.7), BPLUS(3.3), B(.0), BMINUS(2.7), CPLUS(2.3), C(2.0), CMINUS(1.7), DPLUS(1.3), D(1.0), Dminus(
			.7), F(0.0);

	private double grade;

	private Grade(double grade) {
		this.grade = grade;
	}

	public Double getName() {
		return grade;
	}

	public static Grade validateGrade(String validateGrade) throws InvalidDataException {
		for (Grade grade : Grade.values()) {
			/*
			 * String parameter may be either a letter grade or a double grade
			 * (case in point: Students.txt provides the string value of state
			 * and Teachers.txt provides enum type of state)
			 */

			// check by enum value
			if (grade.toString().equalsIgnoreCase(validateGrade)) {
				return grade;
			}
			// if validate grade is a double
			// WILL THIS WORK
			else if (validateGrade.matches("^[0-9]*\\.?[0-9]*$")) {
				if (
						// check by double value
						grade.getName().compareTo(Double.parseDouble(validateGrade)) == 0) {
					return grade;
				}
			}
		}
		return null;
	}
}

package school;

public enum Semester {
	FALL, SPRING, SUMMER1, SUMMER2;

	public static Semester validateSemester(String semester) {
		for (Semester s : Semester.values()) {
			if (s.toString().equalsIgnoreCase(semester)) {
				return s;
			}
		}
		return null;
	}

}
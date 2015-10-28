package school;

public enum EmployeeType {
	ACCOUNTANT, CHAIRPERSON,
	CLERK, DEAN, PROFESSOR,
	INSTRUCTOR, LABTECHNICIAN,
	MAINTENANCE, TUTOR,
	SECRETARY, SECURITY;
	
	public static EmployeeType validateEmployeeType(String validateType) {
		for (EmployeeType employeeType: EmployeeType.values()) {
			if (employeeType.toString().equalsIgnoreCase(validateType)) {
				return employeeType;
			}
		}
		return null;
	}
}

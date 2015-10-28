package school;

public enum Gender {
	M("Male"), F("Female");
	
	private Character gender;
	
	private Gender(String gender) {
		this.gender = gender.charAt(0);
	}
	
	public Character getName() {
		return Character.toUpperCase(this.gender);
	}
	
	public static Gender validateGender(String validateGender) {
		for (Gender gender : Gender.values()) {
			// check by string constant
			if (gender.getName() == Character.toUpperCase(validateGender.charAt(0))
			// check by enum value
					|| gender.toString().compareTo(validateGender) == 0) {
				return gender;
			}
		}
		// gender not found
		return null;
	}
}

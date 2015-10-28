package school;

public enum Major {
	ACCT ("Accounting"), ART ("ART"), BIOL ("Biology"),
	CHEM ("Chemistry"), CPSC ("Computer Science"),
	ECON ("Economics"), EDUC ("Education"), ENGL ("English"), ENGR ("Engineering"),
	FRCH ("French"), GEOG ("Geology"), GERM ("German"), GREE ("Greek"),
	HIST ("HISTORY"), MATH ("MATH") , MUSC ("Music"), NURS ("Nursing"),
	PHIL ("Philosophy"),  PE ("Physical Ed"),PHYS ("Physics"), POLS ("Political Science"), PSYC ("Psychology"),
	RELI ("Religion"), SOCI ("Sociology"), SPEE ("Speech") , UDCD ("Undecided");
	
	private String major;
	private Major(String major) {
		this.major = major;
	}
	
	public String getName() {
		return this.major;
	}
	
	public static Major validateMajor(String validateMajor){
		for (Major major: Major.values()){
			//check by string constant
			if (major.getName().equalsIgnoreCase(validateMajor)
					//check by enum value
					|| major.toString().equalsIgnoreCase(validateMajor)) {
				return major;
				}
		 }
		//couldn't find the major
		 return null;
	}
}

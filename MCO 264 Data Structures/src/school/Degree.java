package school;

public enum Degree {
	BA("Bachelor of Arts") ,BS("Bachelor of Science"),
	MA("Master of Arts"), MS("Master of Science"),
	Phd("Doctorate"), CPA("Certified Public Accountant");
	
	private String degree;
	private Degree(String degree) {
		this.degree = degree;
	}
	
	public String getName() {
		return this.degree;
	}
}

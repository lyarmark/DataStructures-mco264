package school;

public enum Section {
	BA, BB, BC, FA, FB, FC, FD, FE, OL;
	
	public static Section validateSection(String validateSection) {
		for (Section section: Section.values()) {
			if (section.toString().equalsIgnoreCase(validateSection)) {
				return section;
			}
		}
		//section not found
		return null;
	}
}

package pharmacy;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CompanyNameIndex implements Comparable<CompanyNameIndex>, Serializable {
	private String companyName;
	private Long location;
	private boolean isActive;

	public CompanyNameIndex(String name, Long location) throws MissingDataException {
		if ((name == null) || (name == "") || (location == null)) {
			throw new MissingDataException();
		}
		this.companyName = name;
		this.location = location;
		this.isActive = true;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public Long getLocation() {
		return this.location;
	}

	public boolean isActive() {
		return this.isActive;
	}

	public void setInactive() {
		this.isActive = false;
	}

	@Override
	public int compareTo(CompanyNameIndex other) {
		return this.companyName.compareTo(other.companyName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (this.getClass() != obj.getClass()) {
			return false;
		}

		CompanyNameIndex other = (CompanyNameIndex) obj;

		if (this.getCompanyName() != null) {
			if (other.getCompanyName() == null) {
				return false;
			}
			if (!this.getCompanyName().equals(other)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nCompany name: " + this.getCompanyName());
		buffer.append("\nLocation: " + this.getLocation());
		buffer.append("\nCompany name status: ");
		if (this.isActive == true) {
			buffer.append("Active");
		} else if (this.isActive() == false) {
			buffer.append("Inactive");
		} else {
			buffer.append("Unknown");
		}
		return buffer.toString();
	}
}

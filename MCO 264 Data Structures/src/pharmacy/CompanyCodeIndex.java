package pharmacy;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CompanyCodeIndex implements Comparable<CompanyCodeIndex>, Serializable {
	private String companyCode;
	private Long location;
	private boolean isActive;

	public CompanyCodeIndex(String code, Long location) throws MissingDataException {
		if ((code == null) || (code == "") || (location == null)) {
			throw new MissingDataException();
		}
		this.companyCode = code;
		this.location = location;
		this.isActive = true;
	}

	public String getCompanyCode() {
		return this.companyCode;
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
	public int compareTo(CompanyCodeIndex other) {
		return this.companyCode.compareTo(other.companyCode);
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

		CompanyCodeIndex other = (CompanyCodeIndex) obj;

		if (this.getCompanyCode() != null) {
			if (other.getCompanyCode() == null) {
				return false;
			}
			if (!this.getCompanyCode().equals(other)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nCompany code: " + this.getCompanyCode());
		buffer.append("\nLocation: " + this.getLocation());
		buffer.append("\nCompany code status: ");
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

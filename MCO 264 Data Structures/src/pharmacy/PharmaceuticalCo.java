package pharmacy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class PharmaceuticalCo {

	private String companyCode;
	private String companyName;
	private String phoneNumber;

	// constructors
	public PharmaceuticalCo(String code, String name, String phoneNumber) throws MissingDataException {
		if ((code == null) || (code == "") || (name == null) || (name == "") || (phoneNumber == null)
				|| (phoneNumber == "")) {
			throw new MissingDataException();
		}
		this.companyCode = code;
		this.companyName = name;
		this.phoneNumber = phoneNumber;
	}

	public PharmaceuticalCo(Scanner readFile) throws FileNotFoundException {
		// read the next three strings from a text file and construct an
		// instance of PharmaceuticalCo
		this.companyCode = readFile.next();
		this.companyName = readFile.next();
		this.phoneNumber = readFile.nextLine();

	}

	public PharmaceuticalCo(RandomAccessFile raFile, Long location) throws IOException {
		// that will set up an instance of PharmaceuticalCo based on data read
		// starting at a particular location in the file
		raFile.seek(location);
		this.companyCode = raFile.readUTF().trim();
		this.companyName = raFile.readUTF().trim();
		this.phoneNumber = raFile.readUTF();
	}

	// getters
	public String getCompanyCode() {
		return this.companyCode;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws InvalidDataException {
		if (!validatePhoneNumber(phoneNumber)) {
			throw new InvalidDataException();
		} else {
			this.phoneNumber = phoneNumber;
		}
	}

	public static Boolean validatePhoneNumber(String phoneNumber) {
		// either phone is a 10 digit number or it is null
		if (phoneNumber.length() == 10) {
			return true;
		}
		return false;
	}

	public Long writeToFile(RandomAccessFile raFile, Long location) throws IOException {
		raFile.seek(location);
		raFile.writeUTF(String.format("%-4s", this.companyCode));
		raFile.writeUTF(String.format("%-25s", this.companyName));
		raFile.writeUTF(String.format("%-10s", this.phoneNumber));
		return location;
	}

	public int compareTo(PharmaceuticalCo other) {
		return this.compareTo(other);
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

		PharmaceuticalCo other = (PharmaceuticalCo) obj;
		if (this.getCompanyCode() == null) {
			if (other.getCompanyCode() != null) {
				return false;
			}
		}

		if (!this.getCompanyCode().equals(other.getCompanyCode())) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nPharmaceutical Company:");
		buffer.append("\nCode: " + this.getCompanyCode());
		buffer.append("\nName: " + this.getCompanyName());
		buffer.append("\nPhone Number: " + this.getPhoneNumber());
		return buffer.toString();
	}
}

package pharmacy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import school.NotFoundException;

public class PharmacyList implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<CompanyCodeIndex> codeIndex = new ArrayList<CompanyCodeIndex>();
	private ArrayList<CompanyNameIndex> nameIndex = new ArrayList<CompanyNameIndex>();
	transient RandomAccessFile raFile;

	public void connectToData(String fileName) throws FileNotFoundException {
		raFile = new RandomAccessFile(fileName, "rw");
	}

	public void closeFile() throws IOException {
		raFile.close();
	}

	public void addCompany(String companyCode, String companyName, String phoneNumber) throws MissingDataException,
			DuplicateDataException, IOException {
		if (codeIndex.contains(companyCode) || nameIndex.contains(companyName)) {
			throw new DuplicateDataException();
		}
		PharmaceuticalCo pC = new PharmaceuticalCo(companyCode, companyName, phoneNumber);
		raFile = new RandomAccessFile(("./companies.txt"), "rw");
		long location = raFile.length();
		location = pC.writeToFile(raFile, location);
		codeIndex.add(new CompanyCodeIndex(companyCode, location));
		Collections.sort(this.codeIndex);
		nameIndex.add(new CompanyNameIndex(companyName, location));
		Collections.sort(this.nameIndex);
	}

	public PharmaceuticalCo findCompanyCode(String companyCode) throws NotFoundException, IOException {
		for (CompanyCodeIndex code : codeIndex) {
			if (code.getCompanyCode().equalsIgnoreCase(companyCode)) {
				if (code.isActive() == true) {
					PharmaceuticalCo pC = new PharmaceuticalCo(raFile, code.getLocation());
					return pC;
				}
			}
		}
		throw new NotFoundException();
	}

	public PharmaceuticalCo findCompanyName(String companyName) throws NotFoundException, IOException {
		for (CompanyNameIndex name : nameIndex) {
			if (name.getCompanyName().trim().equalsIgnoreCase(companyName)) {
				PharmaceuticalCo pC = new PharmaceuticalCo(raFile, name.getLocation());
				return pC;
			}
		}
		throw new NotFoundException();
	}

	public void modifyCompanyPhone(String companyCode, String newNumber) throws IOException, NotFoundException,
			InvalidDataException {
		boolean found = false;
		for (CompanyCodeIndex code : codeIndex) {
			if (code.getCompanyCode().equalsIgnoreCase(companyCode)) {
				if (code.isActive() == true) {
					found = true;
					PharmaceuticalCo pC = new PharmaceuticalCo(raFile, code.getLocation());
					pC.setPhoneNumber(newNumber);
					pC.writeToFile(raFile, code.getLocation());
					break;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	public ArrayList<CompanyCodeIndex> getCodeIndex() {
		return codeIndex;
	}

	public ArrayList<CompanyNameIndex> getNameIndex() {
		return nameIndex;
	}

	public RandomAccessFile getRaFile() {
		return raFile;
	}

	public void removeCompany(String code, String name) {
		for (CompanyCodeIndex code2 : this.getCodeIndex()) {
			if (code2.getCompanyCode().equalsIgnoreCase(code)) {
				code2.setInactive();
			}
		}
		for (CompanyNameIndex name2 : this.getNameIndex()) {
			if (name2.getCompanyName().equalsIgnoreCase(name)) {
				name2.setInactive();
			}
		}
	}
}

package pharmacy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import school.NotFoundException;

public class Main {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		PharmacyList pL = new PharmacyList();
		// from notes
		System.out.println("Has this application been set up previously? (Y/N)");
		String answer = keyboard.nextLine();
		if (answer.equalsIgnoreCase("N")) {
			try {
				File file = new File("pharmacyCompanies.txt");
				Scanner readFile = new Scanner(file);
				while (readFile.hasNext()) {
					PharmaceuticalCo pC = new PharmaceuticalCo(readFile);
					pL.addCompany(pC.getCompanyCode(), pC.getCompanyName(), pC.getPhoneNumber());
				}
				readFile.close();
			} catch (MissingDataException e) {
				System.out.println(e.toString());
			} catch (DuplicateDataException e) {
				System.out.println(e.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (answer.equalsIgnoreCase("Y")) {
			try {
				ObjectInputStream input = new ObjectInputStream(new FileInputStream("companies.ser"));
				pL = (PharmacyList) input.readObject();
				pL.connectToData("companies.txt");
				input.close();
			} catch (IOException e) {
				System.out.println("There was a problem with the input file. Contact IT.");
				System.exit(1);
			} catch (ClassNotFoundException e) {
				System.out.println("Data in file doesn't correspond to required fields");
				System.exit(1);
			}
		}

		int choice;
		do {
			choice = menu(keyboard);
			switch (choice) {
			case 1: {
				System.out.println("Please enter the company code:");
				String code = keyboard.next();
				System.out.println("Please enter the company name:");
				String name = keyboard.next();
				System.out.println("Please enter the company phone number:");
				String phone = keyboard.next();

				try {
					pL.addCompany(code, name, phone);
				} catch (MissingDataException e) {
					System.out.println(e.toString());
				} catch (DuplicateDataException e) {
					System.out.println(e.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}

			case 2: {
				System.out.println("Please enter the company code:");
				String code = keyboard.next();
				System.out.println("Please enter the company name:");
				String name = keyboard.nextLine();
				pL.removeCompany(code, name);

				break;
			}

			case 3: {
				System.out.println("Please enter the company code:");
				String code = keyboard.next();
				System.out.println("Please enter the new phone number");
				String phone = keyboard.next();
				try {
					PharmaceuticalCo pC = pL.findCompanyCode(code);
					pC.setPhoneNumber(phone);
				} catch (NotFoundException e) {
					System.out.println(e.toString());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InvalidDataException e) {
					System.out.println(e.toString());
				}
				break;
			}

			case 4: {
				// note that you must exit the program to save changes
				// the information you see may not be current
				int choice2;
				do {
					System.out
							.println("Would you like to enter the\n1.\tCompany code\n2.\tCompany name\n0.\tMain Menu");
					choice2 = keyboard.nextInt();
					switch (choice2) {
					case 1: {
						System.out.println("Please enter the company code:");
						String code = keyboard.next();
						try {
							PharmaceuticalCo pC = pL.findCompanyCode(code);
							System.out.println(pC.toString());
							break;
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					case 2: {
						keyboard.nextLine(); // flush buffer
						System.out.println("Please enter the company name:");
						String name = keyboard.nextLine();
						try {
							PharmaceuticalCo pC = pL.findCompanyName(name);
							System.out.println(pC.toString());
							break;
						} catch (NotFoundException e) {
							System.out.println(e.toString());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					case 0: {
						break;
					}
					}
				} while ((choice2 == 1) || (choice2 == 2));
				break;
			}

			case 5: {
				for (CompanyNameIndex name : pL.getNameIndex()) {
					if (name.isActive()) {
						try {
							System.out.println(new PharmaceuticalCo(pL.getRaFile(), name.getLocation()));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				break;
			}

			case 0: {
				System.out.println("Exiting program ... have a wonderful day!");
				try {
					// Write object with ObjectOutputStream
					ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("companies.ser"));
					objOut.writeObject(pL);
					System.exit(0);
				} catch (IOException e) {

					System.out.println("Problem with input file");
				}
			}
			}
		} while ((choice > 0) && (choice <= 5));
	}

	public static int menu(Scanner keyboard) {
		System.out.println("\nPlease select a choice:");
		System.out.println("\n1.\tAdd a pharmaceutical company" + "\n2.\tRemove a company"
				+ "\n3.\tModify company phone number" + "\n4.\tDisplay company information"
				+ "\n5.\tList the information about each company on file" + "\n0.\tExit");

		return keyboard.nextInt();
	}
}
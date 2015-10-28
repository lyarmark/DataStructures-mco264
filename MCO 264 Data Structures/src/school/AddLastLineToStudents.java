package school;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class AddLastLineToStudents {
	public static void main(String[] args) {
		File old = new File("students x.txt");
		File add = new File("add.txt");
		Writer new1 = null;
		try {
			new1 = new FileWriter("students3.txt");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		FileWriter write = null;
		try {
			write = new FileWriter("students3.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Scanner readOld = new Scanner(old);
			Scanner readAdd = new Scanner(add);
			BufferedWriter writeNew = new BufferedWriter(new1);
			while (readOld.hasNextLine()) {

				// String m = readOld.nextLine();
				for (int i = 0; i < 6; i++) {
					String s = readOld.nextLine();
					if (s.equalsIgnoreCase("43665")) {
						boolean test = true;
						if (test) {
							test = false;
						}
					}
					if (i == 3) {
						if (s.length() != 5) {
							StringBuilder sb = new StringBuilder();
							for (int m = 0; m < 5; m++) {
								sb.append(s.charAt(m));
							}
							s = sb.toString();
						}
					}
					writeNew.write(s);
					writeNew.newLine();
					if (i == 4) {
						String s2 = readAdd.nextLine();
						writeNew.write(s2);
						writeNew.newLine();
					}
				}
			}
			write.write(writeNew.toString());
			readOld.close();
			readAdd.close();
			writeNew.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
		}

	}
}

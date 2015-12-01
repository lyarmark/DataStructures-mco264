package recursionHomework;

import java.io.File;
import java.text.DecimalFormat;

public class Main {

	public static void main(String[] args) {

		File file = new File("C:\\Users\\LeahYarmark\\desktop");
		File[] list = file.listFiles();

		printFiles(list, 0, 0);
	}

	public static void printFiles(File[] list, int arrayIndex, int fileIndex) {
		if (arrayIndex >= list.length)
			return;

		// print file name and size
		if (fileIndex == 0) {
			System.out.print(list[arrayIndex].getName() + "\t");
			System.out.println(readableFileSize(list[arrayIndex].length()));
		}

		// print tabs for file hierarchy
		else {
			for (int i = 0; i < fileIndex; i++) {
				System.out.print("\t.");
			}
			System.out.print(list[arrayIndex].getName() + "\t");
			System.out.println(readableFileSize(list[arrayIndex].length()));
		}

		if (list[arrayIndex].isDirectory()) {
			// can just pass in 0, but use this variable for readability
			int arrayIndex2 = 0;
			printFiles(list[arrayIndex].listFiles(), arrayIndex2, fileIndex + 1);
		}

		printFiles(list, arrayIndex + 1, fileIndex);
	}

	// copied from stack overflow
	public static String readableFileSize(long size) {
		if (size <= 0)
			return "0";
		final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
}

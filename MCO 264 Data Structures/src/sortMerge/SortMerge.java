package sortMerge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SortMerge {

	public static void main(String[] args) {
		int amount = 30;
		String unsortedFileName = "unsortedNumbers.txt";
		String sortedFileName = "sortedNumbers.txt";
		ArrayList<String> fileNames = new ArrayList<String>();
		try {
			Scanner read = new Scanner(unsortedFileName);

			generateNumberFile(amount, unsortedFileName);
			// sortNumbers(unsortedFileName, sortedFileName, 1);
			splitAndSort(sortedFileName, unsortedFileName, fileNames);
			merge(fileNames, fileNames.size() - 1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void generateNumberFile(int amount, String unsortedFileName) throws FileNotFoundException {
		Random generator = new Random();
		PrintWriter writeRandom = new PrintWriter(unsortedFileName);
		for (int i = 0; i < amount; i++) {
			writeRandom.println(generator.nextInt(100));
		}
		writeRandom.close();
	}

	private static void splitAndSort(String sortedFileName, String unsortedFileName, ArrayList<String> fileNames)
			throws FileNotFoundException {
		Scanner read = new Scanner(new File(unsortedFileName));
		int fileCount = 1;
		while (read.hasNext()) {
			int[] sortedArray = new int[10];

			for (int i = 0; i < sortedArray.length; i++) {
				if (read.hasNext()) {
					sortedArray[i] = read.nextInt();
				} else {
					break;
				}
			}

			Arrays.sort(sortedArray);
			File sortedFile = new File(fileCount++ + sortedFileName);
			fileNames.add(sortedFile.getName());
			PrintWriter writeSorted = new PrintWriter(sortedFile);
			for (int i = 0; i < sortedArray.length; i++) {
				writeSorted.println(sortedArray[i]);
			}
			writeSorted.close();
		}
	}

	private static void merge(ArrayList<String> fileNames, int fileCount) throws FileNotFoundException {
		merge(fileNames.get(fileCount), fileNames.get((fileCount) - 1), fileNames.size() - fileCount);
		if (fileCount > 1) {
			merge(fileNames, fileCount - 1);
		}
	}

	private static void merge(String fileName1, String fileName2, int fileCount) throws FileNotFoundException {
		int[] array1 = new int[10 * fileCount];
		int[] array2 = new int[10];
		Scanner read1 = new Scanner(new File(fileName1));
		Scanner read2 = new Scanner(new File(fileName2));
		while (read1.hasNext()) {
			for (int i = 0; i < array1.length; i++) {
				array1[i] = read1.nextInt();
			}
		}
		while (read2.hasNext()) {
			for (int i = 0; i < array2.length; i++) {
				array2[i] = read2.nextInt();
			}
		}
		read1.close();
		read2.close();

		int[] array3 = new int[array1.length + array2.length];
		for (int i = 0; i < array1.length; i++) {
			array3[i] = array1[i];
		}
		for (int i = 0; i < array2.length; i++) {
			array3[array1.length + i] = array2[i];
		}
		Arrays.sort(array3);
		PrintWriter writeMerge = new PrintWriter(fileName2);
		for (int i = 0; i < array3.length; i++) {
			writeMerge.println(array3[i]);
		}
		writeMerge.close();
	}
}

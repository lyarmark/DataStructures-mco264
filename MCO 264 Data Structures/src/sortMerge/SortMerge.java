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
		ArrayList<File> files = new ArrayList<File>();
		try {
			generateNumberFile(amount, unsortedFileName);
			splitAndSort(sortedFileName, unsortedFileName, files);
			merge(files, files.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void generateNumberFile(int amount, String unsortedFileName) throws FileNotFoundException {
		Random generator = new Random();
		PrintWriter writeRandom = new PrintWriter(unsortedFileName);
		for (int i = 0; i < amount; i++) {
			writeRandom.println(generator.nextInt(100));
		}
		writeRandom.close();
	}

	private static void splitAndSort(String sortedFileName, String unsortedFileName, ArrayList<File> files)
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

			quickSort(sortedArray, 0, sortedArray.length - 1);

			File sortedFile = new File(fileCount++ + sortedFileName);
			files.add(sortedFile);
			PrintWriter writeSorted = new PrintWriter(sortedFile);
			for (int i = 0; i < sortedArray.length; i++) {
				writeSorted.println(sortedArray[i]);
			}
			writeSorted.close();
		}
	}

	private static void merge(ArrayList<File> files, int fileCount) throws FileNotFoundException {
		sortMerge(files.get(fileCount - 1), files.get((fileCount) - 2), files);
		if (fileCount > 2) {
			merge(files, fileCount - 1);
		}
	}

	private static void sortMerge(File file1, File file2, ArrayList<File> files) throws FileNotFoundException {
		Scanner read1 = new Scanner(file1);
		Scanner read2 = new Scanner(file2);
		File mergedSort = new File("sortedFiles" + files.size() + ".txt");
		PrintWriter writeMerge = new PrintWriter(mergedSort);

		Integer a = read1.nextInt();
		Integer b = read2.nextInt();
		while (a != null && b != null) {
			if (a < b) {
				writeMerge.println(a);
				if (read1.hasNext()) {
					a = read1.nextInt();
				} else {
					a = null;
				}
			} else {
				writeMerge.println(b);
				if (read2.hasNext()) {
					b = read2.nextInt();
				} else {
					b = null;
				}
			}
		}

		if (a == null) {
			writeMerge.println(b);
			while (read2.hasNext()) {
				writeMerge.println(read2.next());
			}
		} else if (b == null) {
			writeMerge.println(a);
			while (read1.hasNext()) {
				writeMerge.println(read1.nextInt());
			}
		}

		read1.close();
		read2.close();
		writeMerge.close();
		files.remove(file1);
		files.remove(file2);
		files.add(mergedSort);
	}

	private static void quickSort(int[] array, int low, int high) {
		int index = quickSortRecur(array, low, high);
		if (low < index - 1) {
			quickSort(array, low, index - 1);
		}
		if (index < high) {
			quickSort(array, index, high);
		}
	}

	private static int quickSortRecur(int[] array, int low, int high) {
		int i = low, j = high;
		int pivot = array[(high + low) / 2];
		while (i <= j) {
			while (array[i] < pivot) {
				i++;
			}
			while (array[j] > pivot) {
				j--;
			}
			if (i <= j) {
				int temp = array[i];
				array[i++] = array[j];
				array[j--] = temp;
			}
		}
		return i;
	}
}

package sorts;

public class ShellSort<T extends Comparable<T>> {

	public void sort(T n[], int N) {
		int Gap;
		T temp;
		int i, j, k;

		for (Gap = N / 2; Gap > 0; Gap = Gap == 2 ? 1 : (int) (Gap / 2)) {
			System.out.println("Gap " + Gap);
			for (i = Gap; i < N; i++) {

				temp = n[i];
				System.out.println("temp " + temp);
				j = i;

				for (; j >= Gap && temp.compareTo(n[j - Gap]) < 0; j -= Gap) {
					System.out.println(" swap " + n[j] + " with " + n[j - Gap]);
					n[j] = n[j - Gap];
				}

				System.out.println("  put " + " instead of n[ " + j + "] " + n[j]);
				n[j] = temp;

			}
			System.out.println();
			for (k = 0; k < 16; k++)
				System.out.println(n[k] + "   ");

		}
	}
}
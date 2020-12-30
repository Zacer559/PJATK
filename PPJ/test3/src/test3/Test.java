package test3;

public class Test {

	public static void main(String[] args) {
		int[][] arr = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };

		int[][] brr = two(arr);
		for (int i = 0; i < brr.length; i++) {
			for (int j = 0; j < brr.length; j++) {

				System.out.print(brr[i][j] + " ");

			}
			System.out.println();

		}

	}

	public static int[][] two(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {

				System.out.print(arr[i][j] + " ");

			}
			System.out.println();

		}

		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			int p = -1;
			int diagonal = arr.length - 1 - i;
			for (int j = 0; j < arr.length; j++) {
				if (j != diagonal) {
					sum = sum + arr[i][j];
				} else {
					p = j;
					continue;
				}
			}
			arr[i][p] = sum;

		}
		return arr;

	}

}

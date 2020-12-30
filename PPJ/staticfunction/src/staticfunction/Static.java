package staticfunction;

import java.util.Arrays;

public class Static {

	public int[][] arr;

	public static int[] length(int[][] arr) {
		int[] ret = new int[arr.length];
		for (int i = 0; i < arr.length; i++)

		{
			int max = arr[i][1];
			for (int j = 0; j < arr[i].length; j++)
				if (arr[i][j] > max) {
					max = arr[i][j];
				}
			ret[i] = max;
		}
		return ret;
	}

	public static int[] even(int[][] arr2) {
		int[] arr = new int[2];
		int even = 0;
		int odd = 0;
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr2[i].length; j++) {
				if (arr2[i][j] % 2 == 0)
					even = even + 1;
				if (arr2[i][j] % 2 == 1)
					odd = odd + 1;

			}

		}
		arr[0] = even;
		arr[1] = odd;
		return arr;
	}

	public static int[][] inner(int[][] arr3) {
		int[][] arrr = new int[arr3.length - 2][arr3[1].length - 2];
		for (int i = 1, i2 = 0; i < arr3.length - 1; i++, i2++) {
			for (int j = 1, j2 = 0; j < arr3[1].length -1 ; j++, j2++) {

				arrr[i2][j2] = arr3[i][j];

			}

		}
		return arrr;

	}

	public static void main(String[] args) {
		int[][] arr2 = { { 1, 2, 3, 4, 5 }, { 1, 9 }, { 3, 1, 8 } };
		int[][] arr3 = { { 1, 2, 3, 4, 5, 6 }, { 2, 3, 4, 5, 6, 7 }, { 3, 4, 5, 6, 7, 8 }, { 4, 5, 6, 7, 8, 9 } };
		int[][] arr = { { 1, 3 }, { 3, 4, 5, 8 }, { 6, 8 }, { 1, 9, 6 } };
		System.out.println(Arrays.toString(length(arr)));
		System.out.println(Arrays.toString(even(arr2)));
		System.out.println(Arrays.deepToString(inner(arr3)));
	}

}

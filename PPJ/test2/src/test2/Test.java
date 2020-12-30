package test2;

public class Test {

	public static int even(int[] arr) {
		int[] a = arr;
		int even = 0;
		int first = -1;
		int last = -1;
		int res;
		for (int i = 0; i < a.length; i++) {
			if (a[i] % 2 == 0)
				even++;

		}

		if (even > 0) {
			for (int i = 0; i < a.length; i++) {
				if (a[i] % 2 == 0) {
					first = i;
					break;

				}

			}
			for (int j = arr.length - 1; j >= 0; j--) {
				if (a[j] % 2 == 0) {
					last = j;
					break;

				}

			}
			res = last - first;
		} else {
			res = -1;
		}
		return res;

	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		System.out.println(even(arr));

	}

}

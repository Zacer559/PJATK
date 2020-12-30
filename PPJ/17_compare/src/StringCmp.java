
import java.util.Arrays;

public class StringCmp {
	public static boolean isLess(String a, String b) {
		if (a.length() < b.length()) {
			return true;
		} else if (a.length() == b.length()) {
			if (a.compareTo(b) < 0)
				return true;
			else
				return false;

		} else
			return false;

	}

	public static void sortSel(String[] a) {
		for (int i = 0; i < a.length; i++) {
			String small = a[i];
			int count = 0;
			int count2 = 0;
			for (int j = i + 1; j < a.length; j++) {
				if (isLess(small, a[j]) == false) {
					small = a[j];
					count = j;
					count2++;

				}

			}
			if (count2 > 0) {
				a[count] = a[i];
				a[i] = small;
			}
		}

	}

	public static void main(String[] args) {
		String[] arr = { "Kate", "Bea", "Mary", "Bea", "Zoe" };
		String s = "Kate";
		String d = "Mary";
		System.out.println(isLess(s, d));
		System.out.println(Arrays.toString(arr));
		sortSel(arr);
		System.out.println(Arrays.toString(arr));
	}
}

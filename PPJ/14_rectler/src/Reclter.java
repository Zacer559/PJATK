import java.util.Arrays;

public class Reclter {

	public static int fiboR(int n) {
		if (n >= 0 && n < 2) {
			return n;
		} else
			return fiboR(n - 1) + fiboR(n - 2);
	}

	public static int fiboI(int n) {
		int a = 0;
		int b = 1;
		int buff;
		for (int i = 0; i < n; i++) {
			buff = b;
			b = b + a;
			a = buff;

		}

		return a;
	}

	public static int factR(int n) {
		if (n <= 1)
			return 1;
		return n * factR(n - 1);
	}

	public static int factI(int n) {
		int a = 1;
		for (int i = 1; i <= n; i++) {
			a = a * i;

		}

		return a;
	}

	public static int gcdR(int a, int b) {
		if (a == b)
			return a;
		if (a > b)
			a = a - b;
		if (b > a)
			b = b - a;
		return gcdR(a, b);

	}

	public static int gcdI(int a, int b) {
		while (true) {
			if (a == b) {
				return a;
			}
			if (a > b)
				a = a - b;
			if (b > a)
				b = b - a;

		}

	}

	public static int maxElem(int[] arr, int from, int to) {
		if (from == to)
			return arr[0];
		if (arr[from] > arr[0])
			arr[0] = arr[from];
		from++;

		return maxElem(arr, from, to);
		
		
		
		
		

	}

	public static void reverse(int[] arr, int from, int to) {
		int buff;
		to=to-1;
		if (to  - from <= 0)
		{return ;}
		else {
			buff = arr[from];
			arr[from] = arr[to];
			arr[to] = buff;
			from++;
			
			 reverse(arr, from, to);

		}

	}

	public static boolean isPalindrom(String s) {
		if (s.length() <= 1)
			return true;

		if (s.charAt(0) == s.charAt(s.length() - 1)) {

			return isPalindrom(s.substring(1, s.length() - 1));
		}

		else
			return false;

	}

	public static void main(String[] args) {
		int n = 5;
		int p = 10;
		int[] arr = {3,8,2,9,7 };
		int from = 0;
		int b=arr[0];
		int to = arr.length;
		String s = "kajek";
		System.out.println(fiboR(45));
		System.out.println(fiboI(45));
		System.out.println(factR(10));
		System.out.println(factI(10));
		System.out.println(gcdR(12125, 40643));
		System.out.println(gcdI(12125, 40643));
		System.out.println(maxElem(arr, from, to));
		arr[0]=b;
		reverse(arr,from,to);
		System.out.println(Arrays.toString(arr));
		System.out.println(isPalindrom("radar"));
		System.out.println(isPalindrom("rover"));
	}

}

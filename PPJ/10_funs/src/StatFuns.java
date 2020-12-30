public class StatFuns {
	static int maxOfThree(int a, int b, int c) {
		int max = a;
		if (b > max)
			max = b;
		if (c > max)
			max = c;
		return max;
	}

	static int greatestDivisor(int n) {
		int g = 1;
		for (int i = 1; i < n; i++) {
			if (n % i == 0) {
				g = i;
			}
		}
		return g;
	}

	static boolean areRelativelyPrime(int a, int b) {
		int max = a;
		int d = 0;
		if (b > max)
			max = b;
		for (int i = 2; i <= max; i++)

		{
			if (a % i == 0 && b % i == 0) {
				d++;

			}

		}
		return d==0;

	}

	static boolean isPerfect(int n) {
		int pr = 1;

		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				pr = pr + i;
			}

		}

		return n == pr;

	}

	public static void main(String[] args) {
		int x = 2, y = 3, z = 1;
		System.out.println("Max of " + x + ", " + y + ", " + z + " is " + maxOfThree(x, y, z));
		for (int a = 12; a < 16; ++a)
			System.out.println("Greatest divisor of " + a + " is " + greatestDivisor(a));
		for (int m = 11, n = 5; m >= 3; m -= 2, n += 2)
			if (areRelativelyPrime(m, n))
				System.out.println(m + " and " + n + " are relatively prime");
		for (int m = 2; m <= 100; ++m)
			if (isPerfect(m))
				System.out.println(m + " is perfect");
	}
}
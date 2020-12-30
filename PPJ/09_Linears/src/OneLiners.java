



public class OneLiners {
	public static boolean hasTwoRoots(double a, double b, double c) {
		return (a!=0 && b * b - 4 * a * c > 0) ;
	}

	public static boolean monot(double a, double b, double c) {
		return (b>a && c>b || a>b && b>c);
	}

	public static double maxEl(double a, double b, double c) {
		double max=a;
		if (b>max) max=b;
		if (c>max) max=c;
		return max;
	}

	public static int numPos(double a, double b, double c) {
		int i=0;
		if (a>0) i++;
		if (b>0) i++;
		if (c>0) i++;
		return i;
	}



	public static void main(String[] args) {
		double a = 2, b = 3, c = 1;
		System.out.println("(a, b, c,)=(" + a + ", " + b + ", " + c + "): hasTwoRoots? " + hasTwoRoots(a, b, c));

		a = 0;
		b = 2;
		c = 1;
		System.out.println("(a, b, c,)=(" + a + ", " + b + ", " + c + "): hasTwoRoots? " + hasTwoRoots(a, b, c));
		a = 2;
		b = 1;
		c = -1;
		System.out.println("(a, b, c,)=(" + a + ", " + b + ", " + c + "): monot? " + monot(a, b, c));
		a = 2;
		b = 1;
		c = 2;
		System.out.println("(a, b, c,)=(" + a + ", " + b + ", " + c + "): monot? " + monot(a, b, c));
		a = 2;
		b = 1;
		c = 2;
		System.out.println("(a, b, c,)=(" + a + ", " + b + ", " + c + "): maxEl = " + maxEl(a, b, c));
		a = 2;
		b = 2;
		c = 4;
		System.out.println("(a, b, c,)=(" + a + ", " + b + ", " + c + "): maxEl = " + maxEl(a, b, c));
		a = -2;
		b = 1;
		c = -3;
		System.out.println("(a, b, c,)=(" + a + ", " + b + ", " + c + "): numPos = " + numPos(a, b, c));
		a = -2;
		b = 1;
		c = 3;
		System.out.println("(a, b, c,)=(" + a + ", " + b + ", " + c + "): numPos = " + numPos(a, b, c));
	}
}
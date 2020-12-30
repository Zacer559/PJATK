package quadraticequasion;

import java.util.Locale;
import java.util.Scanner;

public class QuadraticEquation {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		System.out.println("Write a,b and c");
		double a = scan.nextDouble();
		double b = scan.nextDouble();
		double c = scan.nextDouble();
		double delta;
		double x1;
		double x2;
		double delta2;

		if (a == 0) {

			if (b == 0) {

				if (c == 0) {
					System.out.println("X can be any number");
				} else {
					System.out.println("no solutions");
				}
			} else {
				System.out.println("Solution is" + (-c / b));
			}
		} else {
			delta = (b * b) - (4 * a * c);
			if (delta < 0) {
				System.out.println("No solutions");
			} else {
				delta2 = Math.sqrt(delta);
				x1 = (-b + delta2) / 2 * a;
				x2 = (-b - delta2) / 2 * a;
				System.out.println("Solutions are:" + x1 + x2);
			}

		}
	}
}

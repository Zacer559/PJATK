
public class CalculatingMachine {
	String name;

	public CalculatingMachine(String n) {
		name = n;

	}

	public String calculate(double x, double y) {
		return name + " " + (x + y);

	}

	public static void printRes(CalculatingMachine[] a, double x, double y) {
		for (CalculatingMachine p : a) {
			System.out.println(p.calculate(x, y));

		}

	}

}

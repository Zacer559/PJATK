
public class Calculator extends CalculatingMachine {

	public Calculator(String n) {
		super(n);

	}

	public String calculate(double x, double y) {

		return super.calculate(x, y) + " " + (x - y);

	}

}

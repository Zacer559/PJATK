
public class Computer extends Calculator {
	
	public Computer(String n) {
		super(n);

	}
	
	public String calculate(double x, double y)
	{
		

		return super.calculate(x, y)+" "+(x*y)+" "+(x/y);
	
	
	
	

}
}
package num;

public class Rational {
	private int num,denom;
	public Rational(int num, int denom)
	{this.num=num;
	this.denom=denom;
		
	}
	
	public double getnum()
	{	double numm=(num);
		double a=Double.parseDouble(num)/Double.parseDouble(denom);
		return a;
	}
	public Rational getOpposite()
	{
		int numm=denom;
		int denomm=num;
		if (denomm==0)
		{throw new ArithmeticException ("UPS");
		}
		Rational c = new Rational(numm,denomm);
		return c;
		
		
		
		
	}
	
	

}

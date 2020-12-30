
public class IllHead extends Patient {
	public String ill;
	public String treat;
	public IllHead(String n)
	{	
		super(n);
		ill="head";
		treat="aspirin";
	}
	public String illness()
	{
		return ill;
		
	}
	public String treatment()
	{
		return treat;
		
	}
	
	
	
}

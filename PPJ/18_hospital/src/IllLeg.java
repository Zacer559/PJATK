
public class IllLeg extends Patient {
	public String ill;
	public String treat;
	public IllLeg(String n)
	{
		super(n);
		ill="Leg";
		treat="plaster";
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

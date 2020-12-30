package project;

public class Employee implements IAggregable<Employee, Float>, IDeeplyCloneable<Employee> {
	
	private String name;
	private float sallaryAnnum;
	
			
	
	
	public Employee(String name,float sallaryAnnum) {
		this.name= name;
		this.sallaryAnnum = sallaryAnnum;
	}
	

	public Float aggregate(Float intermediateResult) {
		 if  (intermediateResult == null) {
             return sallaryAnnum;
         }
         if (sallaryAnnum > intermediateResult) {
 			return intermediateResult;
 		} else {
 			return sallaryAnnum;
 		}
	}
	
	public Employee deepClone() {
	
		return new Employee(name,sallaryAnnum);
	}
	
	public String getName() {
		return name;
	}
	
	public float getSallaryAnnum() {
		return sallaryAnnum;
	}
		

	
}
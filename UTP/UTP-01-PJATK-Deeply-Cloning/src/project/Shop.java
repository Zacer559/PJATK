package project;

public class Shop implements IAggregable<Shop, Float>, IDeeplyCloneable<Shop> {

	private  String name;
	private Float incomeAnnum;
	
	
	public Shop(String name, float incomeAnnum) {
		this.name = name;
		this.incomeAnnum = incomeAnnum;
	}
	

	@Override
	public Float aggregate(Float intermediateResult) {
		 if  (intermediateResult == null) {
             return incomeAnnum;
         }
         if ( incomeAnnum > intermediateResult ) {
 			return intermediateResult;
 		} else {
 			return incomeAnnum;
 		}
	}

	@Override
	public Shop deepClone() {

		return new Shop(name,incomeAnnum);
	}
	
	

	public String getName() {
		return name;
	}
	
	public Float getIncomeAnnum() {
		return incomeAnnum;
	}
	
	
}

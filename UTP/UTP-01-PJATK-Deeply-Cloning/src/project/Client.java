package project;

public class Client implements IDeeplyCloneable<Client>, IAggregable<Client, Float> {

   private  String name;
   private  int age;
   private  float spendingAmmount;
      
   
   public Client(String name, int age,float spendingAmmount) {
	  this.name = name;
	  this.age = age;	
	  this.spendingAmmount = spendingAmmount;
	   
   }
   
   

    @Override
    public Float aggregate(Float intermediateResult) {
    	 if  (intermediateResult == null) {
             return spendingAmmount;
         }
         if (spendingAmmount > intermediateResult) {
 			return intermediateResult;
 		} else {
 			return spendingAmmount;
 		}
    }

    @Override
    public Client deepClone() {
    	
    	return  new Client(this.name,this.age,this.spendingAmmount);
    }

public String getName() {
	return name;
}

public int getAge() {
	return age;
}

public float getSpendingAmmount() {
	return spendingAmmount;
}
    
    
}

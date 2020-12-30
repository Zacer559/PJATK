
public class Person {
	String name;
	Car car;
	private Person Person;
	 public Person(String name) { this.name=name;
	 car=null;}
	 
	 public Person buys(String make, int price) { 
		 car = new Car(make,price);
		 return this.Person;
	 }
	 public String getName() { return name;}
	 public void showCars() { car.showCars(); }
	 public void showCarsRev() { car.showCarsRev();}
	// public int getTotalPrice() {  
	//	 int sum=0;
	//	 for (Car : )
	//	 {
			 
	//	 }
		 
	// }
	 public boolean hasCar(String make) { 
		 if (car==null) return false;
		 else return true;
	 }
	// public Car mostExpensive() {  }

	
	

}

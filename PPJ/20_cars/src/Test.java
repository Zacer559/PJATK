
public class Test {
	public static void main(String[] args) {
		Person john = new Person("John");
		john.buys("Ford", 20000)
		.buys("Opel", 16000)
		.buys("Fiat", 12000)
		.showCars();
		System.out.println();
		
		john.showCarsRev();
		System.out.println();
		
		System.out.println("Total price of " +
		john.getName() + "'s cars: " + " ");
			//	john.getTotalPrice());
		System.out.println("Does " + john.getName() +
				" have a ford? " + john.hasCar("ford"));
		System.out.println("Does " + john.getName() +
				" have a bmw? " + john.hasCar("bmw"));
		System.out.println(john.getName() + "'s most " +
				"expensive car is " );// john.mostExpensive());
	}

}

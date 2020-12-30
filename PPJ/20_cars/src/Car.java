
public class Car {
	String m;
	int price;
	private Car next;

	public Car(String m, int p, Car n) {
		this.m = m;
		price = p;
		next = n;
	}

	public Car(String make, int price) {
		m = make;
		this.price = price;
		next=null;
		//next = new Car(make, price);
	}

	public String getMake() {
		return m;
	}

	public int getPrice() {
		return price;
	}

	public Car getNext() {
		return next;
	}

	public void showCars() {
		printCar(this);
	}

	private void printCar(Car car) {
		if (car == null)
			return;
		System.out.print(car + " ");
		printCar(car.getNext());
	}

	public void showCarsRev() {
		printCarReverse(this);
	}

	private void printCarReverse(Car car) {
		if (car.getNext() != null) {
			printCarReverse(car.getNext());

		}

	}

	public String toString() {
		return ("Car" + m + "price" + price);
	}

}

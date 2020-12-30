
public class Car {
	private int distance = 0;
	private int fuel = 35;

	public void fill() {
		if (Math.random() <= 0.1) {
			throw new Explosion("BOOOM");
		}

		fuel = fuel + (int) (Math.random() * 21 + 15);
		System.out.println("Current fuel level " + fuel);

	}

	public void drive100km() throws NotEnoughGas {
		if (fuel < 10) {
			throw new NotEnoughGas("Not enough gas");
		}

		fuel = fuel - 10;
		distance = distance + 100;
		System.out.println("Kilometers: " + distance + " Fuel: " + fuel);

	}

}

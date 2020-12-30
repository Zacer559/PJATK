
public class Excepts {
		public static void main(String[] args) {
			Car car = new Car();
			while (true) {
				try {
					car.drive100km();
				} catch (NotEnoughGas e) {
					System.err.println(e.getMessage());
					car.fill();
				}
			}
		}
	}


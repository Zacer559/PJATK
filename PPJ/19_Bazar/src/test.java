
public class test {

	public static void main(String[] args) {
		Box box1 = new Box("Box1", new Product[] {
				new Product("Carrot", 15),
				new Product("Apples", 20) });
		Box box2 = new Box("Box2",
				new Product[] { new Product("Potato", 10),
						new Product("Carrot", 12) });
		Storage sto = new Storage(new Box[] { box1, box2 });
		System.out.println("Tot. quantity of product: " + sto.totQuant("Carrot"));
	}
}

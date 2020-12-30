public class Computers {
	public static void main(String[] args) {
		CalculatingMachine[] arr = {
				new Computer("Cray"),
				new CalculatingMachine("Abacus"),
				new Calculator("HP") };
		CalculatingMachine.printRes(arr, 21, 7);
	}
}
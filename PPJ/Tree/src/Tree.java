import java.util.Scanner;

public class Tree {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int p=scan.nextInt();
		for (int i = 0; i < p; i++) {
			
			for (int k = 0; k < (2 * i + 1); k++)
				System.out.print(" ");
			
			for (int j = 0; j < p - i; j++)
				System.out.print("*");

			System.out.println();
		}
	}
}
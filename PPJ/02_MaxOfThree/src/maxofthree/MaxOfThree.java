package maxofthree;

import java.util.Scanner;
import java.util.Locale;
public class MaxOfThree {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		System.out.println("Write three numbers" );
		double a = scan.nextDouble();
		double b = scan.nextDouble();
		double c = scan.nextDouble();
		double m = a;
		
		if (b>m) { m = b; }
		if (c>m) { m = c; }
		
		System.out.println("The maximum value is " +m );
		
		
		
		
		
		

	}

}

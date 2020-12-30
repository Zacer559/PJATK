import java.util.Scanner;
import java.util.Locale;
public class Calculator {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scan= new Scanner(System.in);
		System.out.println("Enter two numbers");
		double a=scan.nextDouble();
		double b=scan.nextDouble();
		System.out.println("Enter type of equasion ( + or - or * or / )");
		String s1=scan.next();
		
		
	switch (s1) {
		case "+": System.out.println(a+b); break;
		case "-": System.out.println(a-b); break;
		case "*": System.out.println(a*b);break;
		case "/": System.out.println(a/b); break;
		default: System.out.println("Bad input");
		
	}

}
}
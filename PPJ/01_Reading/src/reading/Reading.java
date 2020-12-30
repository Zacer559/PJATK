package reading;
import java.util.Scanner;
public class Reading {

	public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your name");
			String name = scan.next();
			System.out.println("Enter your age");
			int age = scan.nextInt();
		
			System.out.println("Your name is " + name + " and you are " + age + " years old");
	
				
		scan.close();
		
	}

}

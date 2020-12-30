import java.util.Scanner;
public class Integer {

	public static void main(String[] args) {
		int n=1;
		int f=1;
		int i=1;
		int max=0;
		int num=0;
		Scanner scan = new Scanner(System.in);
		while (n!=0) { n = scan.nextInt();
		
		if (n==f) {i++;}
		else {i=1;}
		f=n;
		if (i>max) {max=i; num=n;}
		
		} 
		scan.close();
		System.out.println("Lenght is " + max +" and value is " +num);

	}

}

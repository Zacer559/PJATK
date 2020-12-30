import java.util.Scanner;
public class Difference {

	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		int a=scan.nextInt();
		int b=scan.nextInt();
		int c=scan.nextInt();
		int d=scan.nextInt();
		int min=a;
		int max=a;
		scan.close();
		//max
		if (b>max) {max=b;};
		if (c>max) {max=c;};
		if (d>max) {max=d;};
		//min
		if (b<min) {min=b;}
		if (c<min) {min=c;}
		if (d<min) {min=d;}
		System.out.println("The difference is " + (max-min) );
		
		
		
	}

}

package test1;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int p=scan.nextInt();
		int p2=p;
		for (int i = 0; i <p; i++) {
			
			for (int j=0;j<p-p2;j++)
			{System.out.print(" ");
				

				
			}	
			System.out.print("*");
			for (int z=(p-i)*2; z>3 ;z--)
			{
				System.out.print (" ");
				
			}
			if (i+1!=p)
				{System.out.print("*");}
			System.out.println();
			p2--;

		}

	}
}

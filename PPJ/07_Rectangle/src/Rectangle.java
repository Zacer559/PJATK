import java.util.Scanner;
public class Rectangle {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int h= scan.nextInt();
		int w= scan.nextInt();
		
		for (int i=1; i<=w;i++)
		{System.out.print("*");}
		
		
		
		
		for (int i=1; i<=(h-2); i++)
		{ System.out.print("\n*");
			for (int j=1; j<=(w-2);j++)
			{System.out.print(" ");}
			System.out.print("*\n");
			
			
			
		}
		for (int i=1; i<=w;i++)
		{System.out.print("*");}

}
}
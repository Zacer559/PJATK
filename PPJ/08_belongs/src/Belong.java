import java.util.Scanner;
public class Belong {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a= scan.nextInt();
		int b= scan.nextInt();
		int c= scan.nextInt();
		int d= scan.nextInt();
		scan.close();
		for (int n=-5; n<=5; n++)
		{boolean isInAB = (n>=a && n<=b);
		boolean isInCD = (n>=c && n<=d);
		
		boolean isInSUM = (isInAB || isInCD);
		boolean isInInter = (isInAB && isInCD);
		boolean isInDif = (isInAB ^ isInCD);
		System.out.println(n+": "+"inSum? "+ isInSUM + "; inIntersect? " +isInInter+"; inSymDiff? "+ isInDif );
		
		
		
		
		}
	}

}

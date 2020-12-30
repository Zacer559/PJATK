package bytes;

import java.util.Scanner;

public class Byte {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter an int");
		int n = scan.nextInt();

		scan.close();
// *1*
		int numberOf1s = 0;
		int p = 1;
		for (int i = 0; i < 32; i++) {
			int d = ((p << i) & n);
			if (d != 0) {
				++numberOf1s;
			}

		}
		System.out.println("No. of 1s is " + numberOf1s);
// *2*
		boolean is7thBitSet = false;
		int y = (p << 8 & n);
		is7thBitSet = (y != 0);
		System.out.println("Is 7th bit set? " + is7thBitSet);
// *3* 
		int mostSignificant = -1;
		for (int i = 0; i < 32; i++) {
			int d = ((p << i) & n);
			if (d != 0) {
				mostSignificant = i;
			}

		}

		System.out.println("Most significant bit set: " + mostSignificant);
// *4* 
		int swapped = 0;
			int s1 = n & 255;
			int s2 = n & 65280;
			swapped= n>>>16;
			swapped = swapped <<8;
			swapped= swapped&s1;
			swapped=swapped<<8;
			swapped=swapped&s2;
			
		
		
		System.out.println("With 2 least significant bytes" + " swapped: " + swapped);
	}
}
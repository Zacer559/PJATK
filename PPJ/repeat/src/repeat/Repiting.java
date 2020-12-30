package repeat;

public class Repiting {
	public static void main(String[] args) {
		int[] a = { 2, 3, 4, 3, 6, 7, 4, 8, 2, 9 };
		int[] b = { 2, 3, 6, 8, 1, 5 };

		
		
		
		
		
		for (int i = 0; i < a.length; i++) {
			int d = 0;
			int m=0;
			for (int p=i; p>=0; p--)
			{ if (a[p]==a[i]) {p++;}
			
			
			if (m==0){	
			for (int j = 0; j < b.length; j++) {
				if (a[i] == b[j])
					d++;

			}

			if (d == 0)
				System.out.print(a[i]);

		}}
			
		}
	}
}

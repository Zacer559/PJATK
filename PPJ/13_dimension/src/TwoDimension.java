
public class TwoDimension {

	public static void main(String[] args) {
		int[][] arr = { { 1, 3, 2 }, { 3, 4, 8 }, { 2, 6, 8 }, { 1, 8, 5 } };
		int row = 0;
		int column=0;
		int max = 0;
		for (int i = 0; i < arr[1].length; i++) {
			max = max + arr[1][i];

		}

		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = 0; j < arr[i].length; j++) {
				sum = sum + arr[i][j];

			}
			if (max < sum) {
				max = sum;
				row = i;
			}

		}
		System.out.println(max + " " + row);
		
	max=0;
	for (int i=0; i<arr[1].length; i++)
	{ int sum=0;
		for	(int j=0; j<arr.length;j++)
		{
			sum=sum+arr[j][i];
			
			
		}
		if (max<sum) {max=sum; column=i;}
		
	}
	System.out.println(max + " " + column);
	}
		
		
		
		
		
		
		

	

	}



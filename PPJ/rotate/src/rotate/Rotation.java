package rotate;

import java.util.Arrays;

public class Rotation {

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4, 5 };
		int buff1 = arr[0];
		for (int i = 0; i < arr.length - 1; i++) {
			arr[i] = arr[i + 1];

		}
		
		arr[arr.length - 1] = buff1;
		
		System.out.println(Arrays.toString(arr));

	}

}

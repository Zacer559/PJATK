import java.util.Arrays;
public class ODD {

	public static void main(String[] args) {
		int s1=-1;
		int buff;
		int s2=-1;
		int [] a = {2,1,1,5,7,9,6};
		System.out.println(Arrays.toString(a));
		
		
		for (int i=0; i<a.length;++i)
		{
			if (a[i]%2==1)
			{
				s1=i;
				break;
				
			}
			
			
		}
		if (s1==-1) System.out.print("no odd Number");
		for (int i=a.length-1; i>=0 ; --i)
			
		{
			if (a[i]%2==0) {
			s2=i;
			break;
			}}
		if (s2==-1) {System.out.print("no even Number");
		
		}
			
			
		
	
	buff=a[s2];
	a[s2]=a[s1];
	a[s1]=buff;
	System.out.println(Arrays.toString(a));
	
		
		
		
		
		
	}

}

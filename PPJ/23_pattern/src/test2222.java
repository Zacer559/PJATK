
public class test2222 {

	public static void main(String[] args) {
	String s="abcd";
	System.out.println(reverse(s));

	}
	
	public static String reverse(String s)
	{String old=s;
	String neww="";
	for (int i=old.length()-1; i>=0;i--)
	{neww=neww+Character.toString(old.charAt(i));

	}
		return neww;
		
		
	}
	
	
	

}
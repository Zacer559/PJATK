import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Http {
	
	public static void main(String[] args) {
		String[] arr = new  String[20];
		String str="www.wrong but this https://a.bb is ok and this http://ccc.dd as well as https://a.bb again or www.xx.yy but not http://com ";
		String pat = "((https?.//)|(www\\.))\\w+\\.\\w+";
		
		
		Matcher m = Pattern.compile(pat).matcher(str);
		int i=0;
		while(m.find())
		{	
			System.out.println(m.group());
		}
	}

}

package testtttt;

public class Glowna {

	public static void main(String[] args) {
		
			String p="ab5675424c55";
			p=p.replaceAll("\\D+", "");
			if (p.length()==0) {System.out.println("0");}
			else
			{int j=Integer.parseInt(p);
			System.out.println(j);
		}

	}
}

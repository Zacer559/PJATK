import javax.swing.JOptionPane;

public class Numbers {

	public static void main(String[] args) {
		String s = JOptionPane.showInputDialog(null, "enter a");
		int a = Integer.parseInt(s);
		s = JOptionPane.showInputDialog(null, "enter b");
		int b = Integer.parseInt(s);
		s = JOptionPane.showInputDialog(null, "enter c");
		int c = Integer.parseInt(s);

		int max3 = a;
		int mid2 = a;
		int min1 = a;
		if (b >=max3) {
			max3 = b;
		}
		if (c >=max3) {
			max3 = c;
		}
		
		if (min1>=b) {min1=b;}
		if (min1>=c) {min1=c;}
		
		 if (max3>=b && min1<=b) {mid2=b;}
		else if (max3>=c && min1<=c) {mid2=c;}
		
		

		JOptionPane.showMessageDialog(null, min1 + " "+mid2+" " + max3);
	}

}

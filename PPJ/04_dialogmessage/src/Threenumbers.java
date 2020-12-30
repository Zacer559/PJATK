import javax.swing.JOptionPane;

public class Threenumbers {

	public static void main(String[] args) {
		String s = JOptionPane.showInputDialog(null, "Enter a");
		double a = Double.parseDouble(s);
		s = JOptionPane.showInputDialog(null, "Enter a");
		double b = Double.parseDouble(s);
		s = JOptionPane.showInputDialog(null, "Enter a");
		double c = Double.parseDouble(s);

		if (a == b) {
			JOptionPane.showMessageDialog(null, "OK");
		} else if (b == c) { JOptionPane.showMessageDialog(null, "OK");
		} else if (a==c){JOptionPane.showMessageDialog(null, "OK");}
		else {JOptionPane.showMessageDialog(null, "NOT OK");}
	}

}

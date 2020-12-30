import javax.swing.JOptionPane;
import java.util.Locale;
public class Dialog {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		String s = JOptionPane.showInputDialog(null, "Enter your height");
		double h = Double.parseDouble(s);
		s = JOptionPane.showInputDialog(null, "Enter your weight in kg");
		double w = Double.parseDouble(s);
		if (h>3) {h=h/100;}
		double bmi = w / (h * h);
		JOptionPane.showMessageDialog(null, "Height = " + h + " \nWeight = " + w + " \n" + "Your BMI = " + bmi);

	}

}

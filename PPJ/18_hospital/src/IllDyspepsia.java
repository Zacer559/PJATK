
public class IllDyspepsia extends Patient {
	public String ill;
	public String treat;

	public IllDyspepsia(String n) {

		super(n);
		ill = "dyspepsia";
		treat = "coal";

	}

	public String illness() {
		return ill;

	}

	public String treatment() {
		return treat;

	}

}

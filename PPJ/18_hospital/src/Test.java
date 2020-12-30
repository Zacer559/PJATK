public class Test {
	public static void main(String[] args) {
		Patient[] patients = { 
				new IllHead("Johny"),
				new IllLeg("Eddy"),
				new IllDyspepsia("Manny") };
		
		
		for (Patient p : patients)
		{System.out.println("Patient: "
		+ p.name() +'\n' +"Illness: " + 
		p.illness() + '\n' + "Treatment: "
		+ p.treatment() + "\n\n");
		}
	}
}
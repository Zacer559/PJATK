
public class Main {

	public static void main(String[] args) {
		
		Person dupa = new Person ("dupa",1995);
		Person Marcin = new Person ("marcin",1999);
		Person Karolina = new Person ("Karolina");
		Person[] pArray= {(new Person ("maciek",1990)), new Person ("rysiek",1980),} ;
		System.out.println(dupa.getName());
		System.out.println(Karolina.getBirthYear());
		System.out.println(Marcin.isFemale());
		System.out.println((Person.getOlder(dupa, Marcin)));
		System.out.println(Person.getOldest(pArray));
		System.out.println(Person.getYoungestFemale(pArray));
		
		
	}

}

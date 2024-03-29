
public class Person {
	private int BirthYear;
	private String name;

	public Person(String n, int year) {
		name = n;
		BirthYear = year;
	}

	public Person(String n) {
		name = n;
		BirthYear = 1990;

	}

	public String getName() {
		return name;
	}

	public int getBirthYear() {
		return BirthYear;
	}

	public boolean isFemale() {
		if (name.charAt(name.length()-1) == 'a')
			return true;
		else
			return false;
	}

	static Person getOlder(Person a, Person b) {
		if (a.BirthYear < b.BirthYear) {
			return a;
		} else
			return b;

	}

	static Person getOldest(Person[] array) {
		Person x = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i].BirthYear < x.BirthYear)
				x = array[i];

		}
		return x;
	}

	static Person getYoungestFemale(Person[] array) {
		Person y = array[0];
		int count = 0;
		for (int j = 0; j < array.length-1; j++) {
			if (array[j].name.charAt(array[j].name.length() - 1) == 'a') {
				count++;
				y = array[j];
			}
		}

		if (count > 0) {

			for (int i = 0; i < array.length-1; i++) {
				if (array[i].BirthYear < y.BirthYear && array[i].name.charAt(array[i].name.length()) == 'a')
					y = array[i];
			}

			return y;

		} else
			return null;

	}

	public String toString() {
		return ("Person:  " + name + "    " + BirthYear);

	}
}

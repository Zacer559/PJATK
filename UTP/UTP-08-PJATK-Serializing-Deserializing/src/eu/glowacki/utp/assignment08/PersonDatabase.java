package eu.glowacki.utp.assignment08;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public final class PersonDatabase {
	private static FirstNameComparator NameComparer = new FirstNameComparator();
	private static BirthdateComparator BirthdayComparer = new BirthdateComparator();
	private List<Person> persons;
	private  List<Person> personsSorted;

	public PersonDatabase(List<Person> persons) {
		this.persons = persons;
		this.personsSorted = sortedBySurnameFirstNameAndBirthdate();
	};

	public int size() {
		return persons.size();
	}

	public void printAll() {
		persons.forEach(person -> System.out
				.println(person.getBirthDate() + "    " + person.getFirstName() + "    " + person.getSurname()));

	}

	// assignment 8 - factory method based on deserialization
	public static PersonDatabase deserialize(DataInputStream input) throws Assignment08Exception {
		try {
			int s = input.readInt();
			List<Person> persons = new ArrayList<>();
			for (int i = 0; input.available() > 0 && i < s; i++) {
				Person person = Person.deserialize(input);
				persons.add(person);

			}
			return new PersonDatabase(persons);
		} catch (Throwable exception) {
			throw new Assignment08Exception("Cannot deserialize database", exception);
		}
	}

	// assignment 8
	public void serialize(DataOutputStream output) throws Assignment08Exception {
		try {
			output.writeInt(personsSorted.size());
			for (Person p : personsSorted) {
				p.serialize(output);
			}		
		} catch (Throwable exception) {
			throw new Assignment08Exception("Cannot serialize database", exception);
		}
	}

	// assignment 4
	public List<Person> sortedByFirstName() {
		return persons.stream().sorted((o1, o2) -> NameComparer.compare(o1, o2)).collect(Collectors.toList());
	}

	// assignment 4
	public List<Person> sortedBySurnameFirstNameAndBirthdate() {
		return persons.stream().sorted((o1, o2) -> o1.compareTo(o2))
				.collect(Collectors.toList());
	}

	// assignment 4
	public List<Person> sortedByBirthdate() {
		return persons.stream().sorted((o1, o2) -> BirthdayComparer.compare(o1, o2)).collect(Collectors.toList());
	}

	// assignment 4
	public List<Person> bornOnDay(Date date) {
		return persons.stream().filter(o1 -> o1.getBirthDate().compareTo(date) == 0).collect(Collectors.toList());
	}
}
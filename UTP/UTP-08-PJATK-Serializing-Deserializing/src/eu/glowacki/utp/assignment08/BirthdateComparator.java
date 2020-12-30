package eu.glowacki.utp.assignment08;

import java.util.Comparator;

import eu.glowacki.utp.assignment08.Person;

public final class BirthdateComparator implements Comparator<Person> {

	@Override
	public int compare(Person person1, Person person2) {
		return person1.getBirthDate().compareTo(person2.getBirthDate());
	}
}
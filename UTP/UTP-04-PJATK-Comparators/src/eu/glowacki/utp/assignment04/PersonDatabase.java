package eu.glowacki.utp.assignment04;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import eu.glowacki.utp.assignment04.comparators.BirthdateComparator;
import eu.glowacki.utp.assignment04.comparators.FirstNameComparator;
import eu.glowacki.utp.assignment04.comparators.SurnameComparator;
public final class PersonDatabase {
	private static FirstNameComparator NameComparer = new FirstNameComparator();
	private static BirthdateComparator BirthdayComparer = new BirthdateComparator();
	private static SurnameComparator SurnameComparer = new SurnameComparator();
	
	
	public PersonDatabase() {};
	
	public List<Person> sortedByFirstNameList(List<Person> peoplelist) {
		return peoplelist.stream().sorted((o1, o2) -> NameComparer.compare(o1, o2)).collect(Collectors.toList());
		
	}
	
	public List<Person> sortedBySurnameFirstNameAndBirthdate(List<Person> peoplelist) {
		return peoplelist.stream()
				.sorted((o1, o2) -> BirthdayComparer.compare(o1, o2))
				.sorted((o1, o2) -> NameComparer.compare(o1, o2))
				.sorted((o1, o2) -> SurnameComparer.compare(o1, o2))
				.collect(Collectors.toList());
	}
	
	public List<Person> sortedByBirthdate(List<Person> peoplelist) {
		return peoplelist.stream().sorted((o1, o2) -> BirthdayComparer.compare(o1, o2)).collect(Collectors.toList());
	}
	
	public  List<Person> bornOnDay(Date date, List<Person> peoplelist) {
		return peoplelist.stream().filter(o1 -> o1.getbirthdate().compareTo(date) == 0).collect(Collectors.toList());
	}
}
package eu.glowacki.utp.assignment04;

import java.util.Date;

public class Person implements Comparable<Person> {
	
	private final String _firstName;
	private final String _surname;
	private final Date _birthdate;
	
	public Person(String firstName, String surname, Date birthdate) {
		_firstName = firstName;
		_surname = surname;
		_birthdate = birthdate;
	}

	@Override
	public int compareTo(Person otherPerson) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getFirstName() {
		return this._firstName;
	}
	public String getSurname() {
		return this._surname;
	}
	public Date getbirthdate() {
		return this._birthdate;
	}
	
	
	
}
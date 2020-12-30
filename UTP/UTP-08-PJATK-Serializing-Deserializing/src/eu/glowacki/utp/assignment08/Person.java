package eu.glowacki.utp.assignment08;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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

	// assignment 8
	public void serialize(DataOutputStream output) throws Assignment08Exception {
		// serialize birth date with getTime() method
		// encapsulate IOException in Assignment08Exception
		try {
			output.writeUTF(_firstName);
			output.writeUTF(_surname);
			output.writeLong(_birthdate.getTime());
			//DateUtility.serialize(_birthdate, output);

		} catch (Throwable exception) {
			throw new Assignment08Exception("cannot serialize person", exception);
		}

	}

	public static Person deserialize(DataInputStream input) throws Assignment08Exception {
		try {
		String firstName = input.readUTF();
		String surname = input.readUTF();
		Date birthDate = new Date(input.readLong());
		//Date birthDate = DateUtility.deserialize(input);
		return new Person(firstName,surname,birthDate);
	}
		catch (Throwable exception) {
			throw new Assignment08Exception("Cannot deserialize person",exception);
		}
	}

	@Override
	public int compareTo(Person otherPerson) {
		int result = this.getSurname().compareTo(otherPerson.getSurname());
		if (result != 0)
			return result;

		result = this.getFirstName().compareTo(otherPerson.getFirstName());
		if (result != 0)
			return result;

		return this.getBirthDate().compareTo(otherPerson.getBirthDate());

	}

	public String getFirstName() {
		return _firstName;
	}

	public String getSurname() {
		return _surname;
	}

	public Date getBirthDate() {
		return _birthdate;
	}
}
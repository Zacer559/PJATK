package eu.glowacki.utp.assignment08.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import eu.glowacki.utp.assignment08.Person;

public class PersonTest {
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private final File personDataBinary = new File("personDataBinary.dat");
	@Test
	public void serializeAndDeserialize() throws Throwable {
		Date birthdate = null;
		try {
			birthdate = dateFormat.parse("1998-04-22");}
		 catch (ParseException e) {
			e.printStackTrace();
		}
		 
		Person person = new Person("Darek","Makowski",birthdate);
		
		
		OutputStream output = new FileOutputStream(personDataBinary);
		DataOutputStream dataOutput = new DataOutputStream(output);
		person.serialize(dataOutput);
		dataOutput.close();
		
		InputStream in = new FileInputStream(personDataBinary);
		DataInputStream dataInput = new DataInputStream(in);
		Person deserialized = Person.deserialize(dataInput);
		dataInput.close();
		Assert.assertNotNull(deserialized);
		Assert.assertEquals(person.getFirstName(), deserialized.getFirstName());
		Assert.assertEquals(person.getSurname(), deserialized.getSurname());
		Assert.assertEquals(person.getBirthDate(), deserialized.getBirthDate());
		System.out.println(person.getFirstName() + " "  + person.getSurname() + " " + person.getBirthDate());
		
		
		
		
		
	}
}
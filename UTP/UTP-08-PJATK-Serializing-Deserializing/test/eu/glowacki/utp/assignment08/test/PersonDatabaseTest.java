package eu.glowacki.utp.assignment08.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.junit.Test;

import eu.glowacki.utp.assignment08.InputParser;
import eu.glowacki.utp.assignment08.Person;
import eu.glowacki.utp.assignment08.PersonDatabase;
import org.junit.Assert;

public class PersonDatabaseTest {
	private final File dataa = new File("Input.txt");
	private final File dataaBinary = new File("dataa.dat");

	
	
	
	@Test
	public void serializeAndDeserialize() throws Throwable {
		List<Person> persons = InputParser.parse(dataa);
		PersonDatabase database = new PersonDatabase(persons);

		OutputStream output = new FileOutputStream(dataaBinary);
		DataOutputStream dataOutput = new DataOutputStream(output);
		database.serialize(dataOutput);
		dataOutput.close();
		database.printAll();
		System.out.println();
		
		InputStream in = new FileInputStream(dataaBinary);
		DataInputStream dataInput = new DataInputStream(in);
		PersonDatabase deserialized = PersonDatabase.deserialize(dataInput);
		dataInput.close();
		deserialized.printAll();
		Assert.assertNotNull(deserialized);
		Assert.assertEquals(database.size(), deserialized.size());
		

	}
}
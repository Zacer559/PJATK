package eu.glowacki.utp.assignment04.test;
import eu.glowacki.utp.assignment04.*;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public final class InputParserTest {
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private List<Person> personList = new ArrayList<>();
	@Before
	public void before() {
		File file = new File("Input.txt");
		 System.out.println(file.getAbsolutePath());
		personList=InputParser.parse(file);
		
	}
	
	@Test
	public void test() throws ParseException {
		
		             
		Assert.assertEquals(personList.get(0).getFirstName(), "John");
		Assert.assertEquals(personList.get(0).getSurname(), "Smith");
		Assert.assertEquals(personList.get(0).getbirthdate(), (dateFormat.parse("1980-01-03")));
		
		Assert.assertEquals(personList.get(1).getFirstName(), "Mark");
		Assert.assertEquals(personList.get(1).getSurname(), "Brown");
		Assert.assertEquals(personList.get(1).getbirthdate(), (dateFormat.parse("1976-02-02")));
		
		
		Assert.assertEquals(personList.get(2).getFirstName(), "Dommy");
		Assert.assertEquals(personList.get(2).getSurname(), "Ztkins");
		Assert.assertEquals(personList.get(2).getbirthdate(), (dateFormat.parse("1966-03-01")));
		
		
		Assert.assertEquals(personList.get(3).getFirstName(), "Tommy");
		Assert.assertEquals(personList.get(3).getSurname(), "Ztkins");
		Assert.assertEquals(personList.get(3).getbirthdate(), (dateFormat.parse("1966-03-02")));
		
		Assert.assertEquals(personList.get(4).getFirstName(), "Alex");
		Assert.assertEquals(personList.get(4).getSurname(), "Snow");
		Assert.assertEquals(personList.get(4).getbirthdate(), (dateFormat.parse("1999-11-09")));
		
		Assert.assertEquals(personList.get(5).getFirstName(), "Alex");
		Assert.assertEquals(personList.get(5).getSurname(), "Snow");
		Assert.assertEquals(personList.get(5).getbirthdate(), (dateFormat.parse("1999-03-12")));
	}
	
	
	
	
	
}
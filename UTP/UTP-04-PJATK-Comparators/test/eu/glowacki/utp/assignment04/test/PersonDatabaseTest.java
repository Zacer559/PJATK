package eu.glowacki.utp.assignment04.test;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.glowacki.utp.assignment04.InputParser;
import eu.glowacki.utp.assignment04.Person;
import eu.glowacki.utp.assignment04.*;

public class PersonDatabaseTest {

	
	
private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  private List<Person> personList = new ArrayList<>();
  private PersonDatabase database = new PersonDatabase();
	@Before
	public void before() {
		 File file = new File("Input.txt");
		personList=InputParser.parse(file);  
	}
	

	@Test
	public void testByFirstName() throws ParseException {
		
		System.out.println("First Name TEST:");
		List<Person> personList2 = database.sortedByFirstNameList(personList);
		for (Person person : personList2) {
			System.out.println(person.getFirstName() + " " + person.getSurname() + " " + person.getbirthdate());
			
		}
		
		Assert.assertEquals(personList2.get(0).getFirstName(), "Alex");
		Assert.assertEquals(personList2.get(1).getFirstName(), "Alex");
		Assert.assertEquals(personList2.get(2).getFirstName(), "Dommy");
		Assert.assertEquals(personList2.get(3).getFirstName(), "John");
		Assert.assertEquals(personList2.get(4).getFirstName(), "Mark");
		Assert.assertEquals(personList2.get(5).getFirstName(), "Tommy");
		
		System.out.println();
		System.out.println();
		
	}
	
	@Test
	public void testByBirthday() throws ParseException {
		
		System.out.println("Birthday date TEST:");
		List<Person> personList2 = database.sortedByBirthdate(personList);
		

		for (Person person : personList2) {
			System.out.println(person.getFirstName() + " " + person.getSurname() + " " + person.getbirthdate());
			
		}
		
		Assert.assertEquals(personList2.get(0).getbirthdate(), dateFormat.parse("1966-03-01"));
		Assert.assertEquals(personList2.get(1).getbirthdate(), dateFormat.parse("1966-03-02"));
		Assert.assertEquals(personList2.get(2).getbirthdate(), dateFormat.parse("1976-02-02"));
		Assert.assertEquals(personList2.get(3).getbirthdate(), dateFormat.parse("1980-01-03"));
		Assert.assertEquals(personList2.get(4).getbirthdate(), dateFormat.parse("1999-03-12"));
		Assert.assertEquals(personList2.get(5).getbirthdate(), dateFormat.parse("1999-11-09"));
		System.out.println();
		System.out.println();
		
	}
	
	
	@Test
	public void testBirthOnDay() throws ParseException {
		
		System.out.println("Birth On Day TEST:");
		List<Person>  personList2 = database.bornOnDay(dateFormat.parse("1980-01-03"),personList);
		

		for (Person person : personList2) {
			System.out.println(person.getFirstName() + " " + person.getSurname() + " " + person.getbirthdate());
			
		}
		
		Assert.assertEquals(personList2.get(0).getbirthdate(), dateFormat.parse("1980-01-03"));
	
		System.out.println();
		System.out.println();
	}
	
	
	
	
	
	@Test
	public void testSurnameNameBirthdate() throws ParseException {
		
		System.out.println("Surname + Name + Birthdate TEST:");
		List<Person>  personList2 = database.sortedBySurnameFirstNameAndBirthdate(personList);
		

		for (Person person : personList2) {
			System.out.println(person.getFirstName() + " " + person.getSurname() + " " + person.getbirthdate());
			
		}
		
		Assert.assertEquals(personList2.get(0).getFirstName(), "Mark");
		Assert.assertEquals(personList2.get(1).getFirstName(), "John");
		Assert.assertEquals(personList2.get(2).getFirstName(), "Alex");
		Assert.assertEquals(personList2.get(3).getFirstName(), "Alex");
		Assert.assertEquals(personList2.get(4).getFirstName(), "Dommy");
		Assert.assertEquals(personList2.get(5).getFirstName(), "Tommy");
		Assert.assertEquals(personList2.get(0).getbirthdate(), dateFormat.parse("1976-02-02"));
		Assert.assertEquals(personList2.get(1).getbirthdate(), dateFormat.parse("1980-01-03"));
		Assert.assertEquals(personList2.get(2).getbirthdate(), dateFormat.parse("1999-03-12"));
		Assert.assertEquals(personList2.get(3).getbirthdate(), dateFormat.parse("1999-11-09"));
		Assert.assertEquals(personList2.get(4).getbirthdate(), dateFormat.parse("1966-03-01"));
		Assert.assertEquals(personList2.get(5).getbirthdate(), dateFormat.parse("1966-03-02"));
		System.out.println();
		System.out.println();
	}
	
	
	
	
		 
		
		 
		
		 
 
			
			
		}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
		
	
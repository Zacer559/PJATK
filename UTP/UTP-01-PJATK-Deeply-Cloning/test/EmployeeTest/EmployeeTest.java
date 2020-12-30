package EmployeeTest;


import project.Employee;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;



public class EmployeeTest {
	
	private static final String nameOne = "George";
	private static final String nameTwo = "Ana";
	
	private static final float sallaryAnnumOne = 20000.0f;
	private static final float sallaryAnnumTwo = 21000.0f;
	
	private Employee employeeOne;
	private Employee employeeTwo;
	
	@Before
	public void before() {
		employeeOne = new Employee(nameOne, sallaryAnnumOne);
		Assert.assertEquals(nameOne, employeeOne.getName());
		Assert.assertEquals(sallaryAnnumOne, employeeOne.getSallaryAnnum(), 0.00f);
		
		employeeTwo = new Employee(nameTwo, sallaryAnnumTwo);
		Assert.assertEquals(nameTwo, employeeTwo.getName());
		Assert.assertEquals(sallaryAnnumTwo, employeeTwo.getSallaryAnnum(), 0.00f);
	
	}

	
	@Test
	public void testAggregate() {
		float aggregateOne = employeeOne.aggregate(null);
		Assert.assertEquals(sallaryAnnumOne, aggregateOne, 0.00f);
		
		float aggregateTwo = employeeTwo.aggregate(null);
		Assert.assertEquals(sallaryAnnumTwo, aggregateTwo, 0.00f);
		
	}

	@Test
	public void testDeepCloneS() {
		Employee cloneOne = this.employeeOne.deepClone();
		Employee cloneTwo = this.employeeTwo.deepClone();
		
		Assert.assertNotEquals(employeeOne, cloneOne);
		Assert.assertNotEquals(employeeTwo, cloneTwo);
		
		Assert.assertEquals(employeeOne.getName(), cloneOne.getName());
		Assert.assertEquals(employeeTwo.getName(), cloneTwo.getName());
		
		
	}
}

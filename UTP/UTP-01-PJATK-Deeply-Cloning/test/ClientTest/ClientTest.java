package ClientTest;


import org.junit.Test;

import org.junit.Assert;
import project.Client;


import org.junit.Before;

public class ClientTest {
	
	
	 private final static String name="John";
	 private final static int age= 55;
	 private final static float spendingAmmount=122.2f;
	
	 private Client client;
	
	@Before
	public void before() {
		client = new Client(name,age,spendingAmmount);
		Assert.assertEquals(name, client.getName());
		Assert.assertEquals(age, client.getAge());
		Assert.assertEquals(client.getSpendingAmmount(), spendingAmmount,0.00f);
		
	
	}

	@Test
	public void testAggregate() {
		float aggregate =client.aggregate(null);
		Assert.assertEquals(aggregate, spendingAmmount, 0.00f);

	}

	@Test
	public void testDeepClone() {
		Client cloneOne = this.client.deepClone();
			
		Assert.assertNotEquals(client, cloneOne);
				
		Assert.assertEquals(client.getName(), cloneOne.getName());
	}

}

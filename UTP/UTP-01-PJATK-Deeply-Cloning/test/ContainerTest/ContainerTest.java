package ContainerTest;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import project.Container;
import project.Client;

public class ContainerTest {
	
	
	private static Container<Client,Float> container= new Container<Client,Float>();
	
	@Before
	public void before() {
		container.add(new Client("John",55,122.22f));
		container.add(new Client("Norm",20,50.0f));
		container.add(new Client("Bart",21,124.0f));
		container.add(new Client("George",42,300.0f));
		

	}
	
	
	@Test
	public void testAggregateAllElements() {
		Assert.assertEquals(50.0f, container.aggregateAllElements(), 0.00f);
	}

	@Test
	public void testCloneElementAtIndex() {
		Client clone = container.cloneElementAtIndex(0);
			
		Assert.assertEquals(clone.getAge(), container.get(0).getAge());
		Assert.assertEquals(clone.getName(), container.get(0).getName());
		Assert.assertEquals(clone.getSpendingAmmount(), container.get(0).getSpendingAmmount(),00.0f);
		
		Assert.assertNotSame(clone,container.get(0));
	}

	
}

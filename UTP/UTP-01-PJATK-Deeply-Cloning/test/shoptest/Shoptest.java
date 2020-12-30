package shoptest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import project.Shop;

public class Shoptest {

	private static final float incomeOne = 10234.21f;
	private static final float incomeTwo = 23555.42f;
	private static final String nameOne = "M1";
	private static final String nameTwo = "M2";
	
	
	private Shop shopOne,shopTwo;
	
	@Before
	public void before() {
		shopOne = new Shop(nameOne,incomeOne);
		Assert.assertEquals(nameOne, shopOne.getName());
		Assert.assertEquals(incomeOne, shopOne.getIncomeAnnum(),0.00f);
		
		shopTwo= new Shop(nameTwo,incomeTwo);
		Assert.assertEquals(nameTwo, shopTwo.getName());
		Assert.assertEquals(incomeTwo, shopTwo.getIncomeAnnum(),0.00f);
		
		Assert.assertNotEquals(shopOne.getName(), shopTwo.getName());
		Assert.assertNotEquals(shopOne.getIncomeAnnum(), shopTwo.getIncomeAnnum());
	}
	

	@Test
	public void aggregate() {
		float aggregateOne = shopOne.aggregate(null);
		Assert.assertEquals(incomeOne, aggregateOne,0.00f);
		
		float aggregateTwo = shopTwo.aggregate(null);
		Assert.assertEquals(incomeTwo, aggregateTwo, 0.00f);
		
		
		
		
	}
	
	
	@Test
	public void deepClone() {
		Shop cloneOne = this.shopOne.deepClone();
		Shop cloneTwo = this.shopTwo.deepClone();
		
		Assert.assertNotEquals(shopOne, cloneOne);
		Assert.assertNotEquals(shopTwo, cloneTwo);
		
		Assert.assertEquals(shopOne.getName(), cloneOne.getName());
		Assert.assertEquals(shopTwo.getName(), cloneTwo.getName());
		
		
		}
}

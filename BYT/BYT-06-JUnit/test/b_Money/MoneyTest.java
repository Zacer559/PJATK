package b_Money;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
    Currency SEK, DKK, NOK, EUR;
    Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;

    @Before
    public void setUp() throws Exception {
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);
        EUR = new Currency("EUR", 1.5);
        SEK100 = new Money(10000, SEK);
        EUR10 = new Money(1000, EUR);
        SEK200 = new Money(20000, SEK);
        EUR20 = new Money(2000, EUR);
        SEK0 = new Money(0, SEK);
        EUR0 = new Money(0, EUR);
        SEKn100 = new Money(-10000, SEK);
    }

    @Test
    public void testGetAmount() {
        Assert.assertEquals("Test: get regular amount", Integer.valueOf(1000), EUR10.getAmount());
        Assert.assertEquals("Test: get zero amount", Integer.valueOf(0), EUR0.getAmount());
    }

    @Test
    public void testGetCurrency() {
        Assert.assertEquals("Test: get currency", EUR, EUR10.getCurrency());
    }

    @Test
    public void testToString() {
        Assert.assertEquals("Test: String to value 0 EUR", "0 EUR", EUR0.toString());
        Assert.assertEquals("Test: String to value 10 EUR", "10 EUR", EUR10.toString());
    }

    @Test
    public void testUniversalValue() {
        Assert.assertEquals("Test: Universal value of 1500 SEK", Integer.valueOf(1500), SEK100.universalValue());
        Assert.assertEquals("Test: Universal value of 3000 EUR", Integer.valueOf(3000), EUR20.universalValue());
    }

    @Test
    public void testEqualsMoney() {
        Assert.assertEquals("Test: value of 1000 EUR and 10000 SEK are equal", true, EUR10.equals(SEK100));
        Assert.assertEquals("Test: value of 2000 EUR and 10000 SEK are not equal", false, EUR20.equals(SEK100));
    }

    @Test
    public void testAdd() {
        Assert.assertEquals("Test: Adding SEK + SEK", "200 SEK", SEK100.add(SEK100).toString());
        Assert.assertEquals("Test: Adding SEK + EUR", "200 SEK", SEK100.add(EUR10).toString());
    }

    @Test
    public void testSub() {
        Assert.assertEquals("Test: Subtraction currencies below zero", "-100 SEK", SEK100.sub(SEK200).toString());
        Assert.assertEquals("Test: Subtraction currencies above zero", "0 SEK", SEK100.sub(SEK100).toString());
    }

    @Test
    public void testIsZero() {
        Assert.assertEquals("Test: Amount of money not equal zero", false, SEK100.isZero());
        Assert.assertEquals("Test: Amount of money equal zero", true, SEK0.isZero());

    }

    @Test
    public void testNegate() {
        Assert.assertEquals("Test: Negation of money ", "-100 SEK", SEK100.negate().toString());

    }

    @Test
    public void testCompareTo() {
        Assert.assertEquals("Test: Compare same", 0, SEK100.compareTo(SEK100));
        Assert.assertEquals("Test: Compare with less", -1, SEK100.compareTo(EUR20));
        Assert.assertEquals("Test: Compare with more", +1, SEK200.compareTo(SEK0));
    }
}

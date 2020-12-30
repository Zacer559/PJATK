package b_Money;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountTest {
    Currency SEK, DKK;
    Bank Nordea;
    Bank DanskeBank;
    Bank SweBank;
    Account testAccount;

    @Before
    public void setUp() throws Exception {
        SEK = new Currency("SEK", 0.15);
        SweBank = new Bank("SweBank", SEK);
        SweBank.openAccount("Alice");
        testAccount = new Account("Hans", SEK);
        testAccount.deposit(new Money(10000000, SEK));

        SweBank.deposit("Alice", new Money(1000000, SEK));
    }

    /**
     * Preventing to create duplicate payments
     */
    @Test
    public void testAddRemoveTimedPayment() {
        testAccount.addTimedPayment("1", 10, 10, new Money(100, SEK), SweBank, "Alice");
        testAccount.addTimedPayment("1", 10, 10, new Money(100, SEK), SweBank, "Alice");
        //error here(not test error, but printed message in red color), timed payment already exist.
        assertTrue("Test: timedPayment already exist", testAccount.timedPaymentExists("1"));
        testAccount.removeTimedPayment("1");
        assertFalse("Test: removed payment does not exist", testAccount.timedPaymentExists("1"));
        //error here(not test error, but printed message in red color), timed payment does not exist.
        testAccount.removeTimedPayment("1");

    }

    @Test
    public void testTimedPayment() throws AccountDoesNotExistException {
        testAccount.addTimedPayment("1", 2, 0, new Money(10000, SEK), SweBank, "Alice");
        for (int i = 0; i < 10; i++) {
            testAccount.tick();
        }
        Assert.assertEquals("Test: Account balance after timed payment", Integer.valueOf(9960000), testAccount.getBalance());
    }

    /**
     * Withdrawing more than available on account
     */
    @Test
    public void testAddWithdraw() {
        testAccount.withdraw(new Money(5000000, SEK));
        Assert.assertEquals("Test: withdraw Hans", Integer.valueOf(5000000), testAccount.getBalance());
        testAccount.withdraw(new Money(990000000, SEK));
        //error here(not test error, but printed message in red color), has dont have so much money.
        Assert.assertEquals("Test: withdraw Hans", Integer.valueOf(5000000), testAccount.getBalance());
    }

    @Test
    public void testGetBalance() {
        Assert.assertEquals("Test: Get account balance", Integer.valueOf(10000000), testAccount.getBalance());
    }
}

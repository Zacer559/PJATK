package b_Money;

import java.util.Hashtable;

public class Account {
    private final String accountName; //created for better error printing
    private Money content;
    private final Hashtable<String, TimedPayment> timedpayments = new Hashtable<String, TimedPayment>();

    Account(String name, Currency currency) {
        accountName = name;
        this.content = new Money(0, currency);
    }

    /**
     * created for better error printing
     *
     * @return
     */
    public String getAccountName() {
        return accountName;
    }


    /**
     * Add a timed payment
     *
     * @param id        Id of timed payment
     * @param interval  Number of ticks between payments
     * @param next      Number of ticks till first payment
     * @param amount    Amount of Money to transfer each payment
     * @param tobank    Bank where receiving account resides
     * @param toaccount Id of receiving account
     */
    public void addTimedPayment(String id, Integer interval, Integer next, Money amount, Bank tobank, String toaccount) {
        //Added check that payment exist
        //Error which was showed by the test
        if (timedPaymentExists(id)) {
            System.err.println("Timed payment " + id + " already exists");
        } else {
            TimedPayment tp = new TimedPayment(interval, next, amount, this, tobank, toaccount);
            timedpayments.put(id, tp);
        }

    }

    /**
     * Remove a timed payment
     *
     * @param id Id of timed payment to remove
     */
    public void removeTimedPayment(String id) {
        //Added check that payment exist
        //Error which was showed by the test
        if (timedPaymentExists(id)) {
            timedpayments.remove(id);
        } else {
            System.err.println("Timed payment " + id + " does not exist");
        }


    }

    /**
     * Check if a timed payment exists
     *
     * @param id Id of timed payment to check for
     */
    public boolean timedPaymentExists(String id) {
        return timedpayments.containsKey(id);
    }

    /**
     * A time unit passes in the system
     */
    public void tick() {
        for (TimedPayment tp : timedpayments.values()) {
//			tp.tick(); tp.tick(); //Error which was showed by the test
            tp.tick();
        }
    }

    /**
     * Deposit money to the account
     *
     * @param money Money to deposit.
     */
    public void deposit(Money money) {
        content = content.add(money);

    }

    /**
     * Withdraw money from the account
     *
     * @param money Money to withdraw.
     */
    public void withdraw(Money money) {
        //checking added //Error which was showed by the test
        if (this.getBalance() < money.getAmount()) {
            System.err.println("account " + this.getAccountName() + " has only " + this.getBalance() + " (you want to withdraw " + money.getAmount() + ")");

        } else {
            content = content.sub(money);
        }

    }

    /**
     * Get balance of account
     *
     * @return Amount of Money currently on account
     */
    public Integer getBalance() {
        //Error which was showed by the test
        //changed the return type from Money to integer
        //		return content;
        return content.getAmount();

    }

    /* Everything below belongs to the private inner class, TimedPayment */
    private class TimedPayment {
        private final int interval;
        private int next;
        private final Account fromaccount;
        private final Money amount;
        private final Bank tobank;
        private final String toaccount;

        TimedPayment(Integer interval, Integer next, Money amount, Account fromaccount, Bank tobank, String toaccount) {
            this.interval = interval;
            this.next = next;
            this.amount = amount;
            this.fromaccount = fromaccount;
            this.tobank = tobank;
            this.toaccount = toaccount;
        }

        /* Return value indicates whether or not a transfer was initiated */
        public Boolean tick() {
            if (next == 0) {
                next = interval;

                fromaccount.withdraw(amount);
                try {
                    tobank.deposit(toaccount, amount);
                } catch (AccountDoesNotExistException e) {
                    /* Revert transfer.
                     * In reality, this should probably cause a notification somewhere. */
                    fromaccount.deposit(amount);
                }
                return true;
            } else {
                next--;
                return false;
            }
        }
    }

}

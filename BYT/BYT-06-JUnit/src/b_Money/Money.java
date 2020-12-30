package b_Money;

@SuppressWarnings("rawtypes")
public class Money implements Comparable {
    private final int amount;
    private final Currency currency;

    /**
     * New Money
     *
     * @param amount   The amount of money
     * @param currency The currency of the money
     */
    Money(Integer amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    /**
     * Return the amount of money.
     *
     * @return Amount of money in Double type.
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Returns the currency of this Money.
     *
     * @return Currency object representing the currency of this Money
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Returns the amount of the money in the string form "(amount) (currencyname)", e.g. "10.5 SEK".
     * Recall that we represent decimal numbers with integers. This means that the "10.5 SEK" mentioned
     * above is actually represented as the integer 1050
     *
     * @return String representing the amount of Money.
     */
    public String toString() {
        return amount / 100 + " " + currency.getName();
    }

    /**
     * Gets the universal value of the Money, according the rate of its Currency.
     *
     * @return The value of the Money in the "universal currency".
     */
    public Integer universalValue() {
        return this.currency.universalValue(this.amount);
    }

    /**
     * Check to see if the value of this money is equal to the value of another Money of some other Currency.
     *
     * @param other The other Money that is being compared to this Money.
     * @return A Boolean indicating if the two monies are equal.
     */
    public Boolean equals(Money other) {
        return this.universalValue().equals(other.universalValue());
    }

    /**
     * Adds a Money to this Money, regardless of the Currency of the other Money.
     *
     * @param other The Money that is being added to this Money.
     * @return A new Money with the same Currency as this Money, representing the added value of the two.
     * (Remember to convert the other Money before adding the amounts)
     */
    public Money add(Money other) {
        int newAmount = this.getAmount() + other.getCurrency().valueInThisCurrency(other.getAmount(), this.getCurrency());
        return new Money(newAmount, this.getCurrency());
    }

    /**
     * Subtracts a Money from this Money, regardless of the Currency of the other Money.
     *
     * @param other The money that is being subtracted from this Money.
     * @return A new Money with the same Currency as this Money, representing the subtracted value.
     * (Again, remember converting the value of the other Money to this Currency)
     */
    public Money sub(Money other) {
        int newAmount = this.getAmount() - other.getCurrency().valueInThisCurrency(other.getAmount(), this.getCurrency());
        return new Money(newAmount, this.getCurrency());
    }

    /**
     * Check to see if the amount of this Money is zero or not
     *
     * @return True if the amount of this Money is equal to 0.0, False otherwise
     */
    public Boolean isZero() {
        return this.getAmount().equals(0);
    }

    /**
     * Negate the amount of money, i.e. if the amount is 10.0 SEK the negation returns -10.0 SEK
     *
     * @return A new instance of the money class initialized with the new negated money amount.
     */
    public Money negate() {
        return new Money(-this.getAmount(), this.getCurrency());
    }

    /**
     * Compare two Monies.
     * compareTo is required because the class implements the Comparable interface.
     * (Remember the universalValue method, and that Integers already implement Comparable).
     * Also, since compareTo must take an Object, you will have to explicitly downcast it to a Money.
     *
     * @return 0 if the values of the monies are equal.
     * A negative integer if this Money is less valuable than the other Money.
     * A positive integer if this Money is more valuiable than the other Money.
     */
    public int compareTo(Object other) {
        Money other2 = (Money) other;
        //taking universal values to compare
        int local = this.universalValue();
        int outer = other2.universalValue();
        return Integer.compare(local, outer);
    }
}
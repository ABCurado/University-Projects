package csheets.framework;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;

/**
 * represents money values.
 *
 * based on http://martinfowler.com/eaaDev/quantity.html
 *
 */
public class Money implements Comparable<Money>, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final transient int HUNDRED = 100;

	private final BigInteger amount;
	private final Currency currency;

	/**
	 * Constructs a new Money object.
	 *
	 * You'll notice there are no setters. Money is a Value Object and is thus
	 * immutable. It helps to have a variety of constructors to make it easy to
	 * make monies. Constructors that convert from basic numeric types are
	 * always useful.
	 *
	 * @param amount amount
	 * @param currency currency
	 */
	public Money(double amount, Currency currency) {
		if (amount < 0.0 || currency == null) {
			throw new IllegalArgumentException("Amount should be a positive value and currency must be set!");
		}
		this.amount = BigInteger.valueOf(Math.round(amount * HUNDRED));
		this.currency = currency;
	}

	public Money(long amount, Currency currency) {
		this.amount = BigInteger.valueOf(amount * HUNDRED);
		this.currency = currency;
	}

	private Money(BigInteger amountInPennies, Currency currency,
				  boolean privacyMarker) {
		// Assert.notNull(amountInPennies);
		// Assert.notNull(currency);
		// FIXME do validations
		this.amount = amountInPennies;
		this.currency = currency;
	}

	/**
	 *
	 * @param amount amount
	 * @return money
	 */
	public static Money pounds(final double amount) {
		return new Money(amount, Currency.getInstance("GBP"));
	}

	/**
	 * If you use one currency a lot, you may want a special constructor for
	 * that currency.
	 *
	 * @param amount amount
	 * @return money
	 */
	public static Money dollars(final double amount) {
		return new Money(amount, Currency.getInstance("USD"));
	}

	/**
	 * If you use one currency a lot, you may want a special constructor for
	 * that currency.
	 *
	 * @param amount amount
	 * @return money
	 */
	public static Money euros(final double amount) {
		return new Money(amount, Currency.getInstance("EUR"));
	}

	/**
	 * Returns the amount portion of this Money object.
	 *
	 * Notice that I use a BigInteger. In Java I could equally well use a
	 * BigDecimal, but in many languages an integer is the only decent option,
	 * so using an integer seems the best for explanation. Don't be afraid to
	 * choose your representation of the amount part of a quantity based on
	 * performance factors. The beauty of objects is that you can choose any
	 * data structure you like on the inside, providing you hide it on the
	 * outside.
	 *
	 * @return amout
	 */
	public double amount() {
		return this.amount.doubleValue() / HUNDRED;
	}

	public BigDecimal amountAsDecimal() {
		return BigDecimal.valueOf(amount());
	}

	/**
	 * Returns the currency of this Money object.
	 *
	 * @return currency
	 */
	public Currency currency() {
		return this.currency;
	}

	/**
	 * Adds two Money objects and returns the result as a new object.
	 *
	 * For addition and subtraction I'm not trying to do any fancy conversion.
	 * Notice that I'm using a special constructor with a marker argument.
	 *
	 * @param arg arg
	 * @return money
	 */
	public Money add(Money arg) {
		assertSameCurrencyAs(arg);
		return new Money(this.amount.add(arg.amount), this.currency, true);
	}

	/**
	 * Subtracts two Money instances and returns a third one with the result.
	 *
	 * @param arg arg
	 * @return money
	 */
	public Money subtract(Money arg) {
		return add(arg.negate());
	}

	private void assertSameCurrencyAs(Money arg) {
		// Assert.equals("money math mismatch", currency, arg.currency);
		// FIX do validations
	}

	public Money negate() {
		return new Money(this.amount.negate(), this.currency, true);
	}

	/**
	 * Multiplies two Money objects and returns the result a new object.
	 *
	 * @param arg arg
	 * @return money
	 */
	public Money multiply(double arg) {
		return new Money(amount() * arg, this.currency);
	}

	/**
	 * Divides this Money object by a certain integer denominator doing the
	 * right allocation of cents.
	 *
	 * Multiplication is very straightforward. But division is not, as we have
	 * to take care of errant pennies. We'll do that by returning an array of
	 * monies, such that the sum of the array is equal to the original amount,
	 * and the original amount is distributed fairly between the elements of the
	 * array. Fairly in this sense means those at the begining get the extra
	 * pennies.
	 *
	 * @param denominator denominator
	 * @return money
	 */
	public Money[] divide(int denominator) {
		final BigInteger bigDenominator = BigInteger.valueOf(denominator);
		final Money[] result = new Money[denominator];
		final BigInteger simpleResult = this.amount.divide(bigDenominator);
		for (int i = 0; i < denominator; i++) {
			result[i] = new Money(simpleResult, this.currency, true);
		}
		final int remainder = this.amount.subtract(simpleResult.
			multiply(bigDenominator)).intValue();
		for (int i = 0; i < remainder; i++) {
			result[i] = result[i].
				add(new Money(BigInteger.valueOf(1), this.currency, true));
		}
		return result;
	}

	/**
	 * Compares two Money objects.
	 *
	 * Next we'll look at comparing monies, in Java the approach is to implement
	 * comparable.
	 *
	 * @param moneyArg moneyArg
	 */
	@Override
	public int compareTo(Money moneyArg) {
		assertSameCurrencyAs(moneyArg);
		return this.amount.compareTo(moneyArg.amount);
	}

	/**
	 * Compares two Money objects.
	 *
	 * It's also useful to provide some better named operations such as: That
	 * makes methods that need the comparison much easier to read.
	 *
	 * @param arg arg
	 * @return boolean
	 */
	public boolean greaterThan(Money arg) {
		return compareTo(arg) > 0;
	}

	/**
	 * Compares two Money objects.
	 *
	 * @param arg arg
	 * @return boolean
	 */
	public boolean lessThan(Money arg) {
		return compareTo(arg) < 0;
	}

	/**
	 * Compares two Money objects.
	 *
	 * @param arg arg
	 * @return boolean
	 */
	public boolean greaterThanOrEqual(Money arg) {
		return compareTo(arg) >= 0;
	}

	/**
	 * Compares two Money objects.
	 *
	 * @param arg arg
	 * @return boolean
	 */
	public boolean lessThanOrEqual(Money arg) {
		return compareTo(arg) <= 0;
	}

	/**
	 * Since money is a value, it should override equals.
	 *
	 * @param arg arg
	 */
	@Override
	public boolean equals(Object arg) {
		if (!(arg instanceof Money)) {
			return false;
		}
		final Money other = (Money) arg;
		return this.currency.equals(other.currency) && this.amount.
			equals(other.amount);
	}

	/**
	 * Since you override equals, don't forget to also override hash (here's a
	 * simple suggestion for that).
	 */
	@Override
	public int hashCode() {
		return this.amount.hashCode();
	}

	public int signum() {
		return this.amount.signum();
	}

	@Override
	public String toString() {
		return amount() + " " + currency();
	}
}

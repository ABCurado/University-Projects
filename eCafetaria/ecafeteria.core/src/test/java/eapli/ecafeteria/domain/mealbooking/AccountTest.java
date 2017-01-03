/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.mealbooking;

import eapli.framework.domain.Money;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Rafael Rocha <1140329@isep.ipp.pt>
 */
public class AccountTest {

    @Test(expected = IllegalStateException.class)
    public void notValidAccountWithEmptyArgument() {
        System.out.println("Not valid account");
        new Account("");
    }

    @Test(expected = IllegalStateException.class)
    public void notValidAccountWithArgumentNull() {
        System.out.println("Not valid account");
        new Account(null);
    }

    @Test
    public void validAccountWithCurrency() {
        System.out.println("Valid account");
        new Account("EUR");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullTransaction() {
        Account test = new Account("test");
        test.addAccountLoad(null);
        assertTrue(true);
    }

    @Test
    public void addValidLoad() {
        Account testAccount = new Account(Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        testAccount.addAccountLoad(BigDecimal.valueOf(Double.parseDouble("10.0")));
        assertEquals(1, testAccount.transactionsNumber());
    }

    @Test(expected = NullPointerException.class)
    public void addNullBalance() {
        Account testAccount = new Account(Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        testAccount.addAccountLoad(null);
        assertTrue(true);
    }

    @Test
    public void add10ToBalance() {
        Account testAccount = new Account(Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        Money testMoney = new Money(10.0, Currency.getInstance(Locale.getDefault()));
        testAccount.addAccountLoad(BigDecimal.valueOf(Double.parseDouble("10.0")));
        assertEquals(testMoney, testAccount.getBalance());
    }

    @Test
    public void add3x10ToBalance() {
        Account testAccount = new Account(Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        Money testMoney = new Money(10.0, Currency.getInstance(Locale.getDefault()));
        testAccount.addAccountLoad(BigDecimal.valueOf(Double.parseDouble("10.0")));
        testAccount.addAccountLoad(BigDecimal.valueOf(Double.parseDouble("10.0")));
        testAccount.addAccountLoad(BigDecimal.valueOf(Double.parseDouble("10.0")));
        assertEquals(testMoney.multiply(3.0), testAccount.getBalance());
    }

    @Test
    public void addValidDebt() {
        Account testAccount = new Account(Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        Money testMoney = new Money(20.0, Currency.getInstance(Locale.getDefault()));
        testAccount.setBalance(testMoney);
        testAccount.addAccountDebt(BigDecimal.valueOf(Double.parseDouble("10.0")));
        assertEquals(1, testAccount.transactionsNumber());
    }

    @Test
    public void add3ValidTransaction() {
        Account testAccount = new Account(Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        testAccount.addAccountLoad(BigDecimal.valueOf(Double.parseDouble("10.0")));
        testAccount.addAccountDebt(BigDecimal.valueOf(Double.parseDouble("10.0")));
        testAccount.addAccountLoad(BigDecimal.valueOf(Double.parseDouble("10.0")));
        assertEquals(3, testAccount.transactionsNumber());
    }

    @Test(expected = NullPointerException.class)
    public void subtractNullBalance() {
        Account testAccount = new Account(Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        testAccount.addAccountLoad(null);
        assertTrue(true);
    }

    @Test
    public void remove10ToBalance() {
        Account testAccount = new Account(Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        Money testMoney = new Money(20.0, Currency.getInstance(Locale.getDefault()));
        testAccount.setBalance(testMoney);
        testAccount.addAccountDebt(BigDecimal.valueOf(Double.parseDouble("10.0")));
        assertEquals(testMoney.subtract(new Money(10, Currency.getInstance(Locale.getDefault()))), testAccount.getBalance());
    }
    //30 - 25.5 = 4.5
    @Test
    public void remove3xToBalance() {
        Account testAccount = new Account(Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        Money testMoney = new Money(30.0, Currency.getInstance(Locale.getDefault()));
        testAccount.setBalance(testMoney);
        testAccount.addAccountDebt(BigDecimal.valueOf(Double.parseDouble("10.0")));
        testAccount.addAccountDebt(BigDecimal.valueOf(Double.parseDouble("15.0")));
        testAccount.addAccountDebt(BigDecimal.valueOf(Double.parseDouble("0.5")));
        assertEquals(testMoney.subtract(new Money(25.5, Currency.getInstance(Locale.getDefault()))), testAccount.getBalance());
    }
    
    @Test
    public void ensureAddDebtRetrunsFalseWhenIsNotPossible() {
        Account testAccount = new Account(Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        Money testMoney = new Money(5.0, Currency.getInstance(Locale.getDefault()));
        testAccount.setBalance(testMoney);

        assertEquals(false,testAccount.addAccountDebt(BigDecimal.valueOf(Double.parseDouble("10.0"))));
    }
    
    @Test
    public void ensureDebtIsOnlyAddedWithEnoughMoney() {
        Account testAccount = new Account(Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        Money testMoney = new Money(5.0, Currency.getInstance(Locale.getDefault()));
        testAccount.setBalance(testMoney);
testAccount.addAccountDebt(BigDecimal.valueOf(Double.parseDouble("10.0")));
        assertEquals(0, testAccount.transactionsNumber());
    }
}

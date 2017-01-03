/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.mealbooking;

import eapli.framework.domain.Money;
import java.util.Currency;
import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.*;

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

    @Test(expected = IllegalStateException.class)
    public void notValidAccountWithArgumentNullandCurrency() {
        System.out.println("Not valid account");
        new Account(null, "EUR");
    }
    @Test
    public void ValidAccountWithCurrency() {
        System.out.println("Valid account");
        new Account("Account", "EUR");
    }

    @Test(expected = NullPointerException.class)
    public void addNullTransaction() {
        Account test = new Account("test");
        test.addTransaction(null);
        assertTrue(true);
    }

    @Test
    public void addValidTransaction() {
        Account testAccount = new Account("test", Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        Money testMoney = new Money(10.0, Currency.getInstance(Locale.getDefault()));
        Transaction test = new AccountLoad(testMoney);
        testAccount.addTransaction(test);
        assertEquals(1, testAccount.transactionsNumber());
    }

    @Test
    public void add3ValidTransaction() {
        Account testAccount = new Account("test", Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        Money testMoney = new Money(10.0, Currency.getInstance(Locale.getDefault()));
        Transaction test = new AccountLoad(testMoney);
        testAccount.addTransaction(test);
        Money testMoney2 = new Money(10.0, Currency.getInstance(Locale.getDefault()));
        Transaction test2 = new AccountLoad(testMoney2);
        testAccount.addTransaction(test2);
        Money testMoney3 = new Money(10.0, Currency.getInstance(Locale.getDefault()));
        Transaction test3 = new AccountLoad(testMoney3);
        testAccount.addTransaction(test3);
        assertEquals(3, testAccount.transactionsNumber());
    }

    @Test(expected = NullPointerException.class)
    public void addNullBalance() {
        Account testAccount = new Account("test", Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        Transaction test = new AccountLoad(null);
        testAccount.addTransaction(test);
        assertTrue(true);
    }

    @Test
    public void add10ToBalance() {
        Account testAccount = new Account("test", Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        Money testMoney = new Money(10.0, Currency.getInstance(Locale.getDefault()));
        Transaction test = new AccountLoad(testMoney);
        testAccount.addTransaction(test);
        assertEquals(testMoney, testAccount.getBalance());
    }

    @Test
    public void add3x10ToBalance() {
        Account testAccount = new Account("test", Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        Money testMoney = new Money(10.0, Currency.getInstance(Locale.getDefault()));
        Transaction test = new AccountLoad(testMoney);
        testAccount.addTransaction(test);
        Transaction test2 = new AccountLoad(testMoney);
        testAccount.addTransaction(test2);
        Transaction test3 = new AccountLoad(testMoney);
        testAccount.addTransaction(test3);
        assertEquals(testMoney.multiply(3.0), testAccount.getBalance());
    }
}

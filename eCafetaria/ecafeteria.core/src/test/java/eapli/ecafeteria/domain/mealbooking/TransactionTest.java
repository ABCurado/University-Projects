/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.mealbooking;

import eapli.framework.domain.Money;
import java.util.Currency;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Rafael Rocha <1140329@isep.ipp.pt>
 */
public class TransactionTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureAccountLoadDoesntAcceptNegativeAmountOfMoney() {
        Money money = new Money(-3, Currency.getInstance("EUR"));

        new Transaction(money, TransactionType.LOAD);
    }

    @Test
    public void ensureGetAmountMethodWorksAsIntended() {
        Money money = new Money(5, Currency.getInstance("EUR"));

        Transaction load = new Transaction(money, TransactionType.LOAD);

       assertEquals(money,load.getAmount());
    }
    
    @Test
    public void ensureNewLoadWorksAsIntended() {
        Money money = new Money(5, Currency.getInstance("EUR"));

        Transaction load = new Transaction(money, TransactionType.LOAD);

       assertEquals(TransactionType.LOAD,load.getType());
    }
    
    @Test
    public void ensureNewDebtWorksAsIntended() {
        Money money = new Money(5, Currency.getInstance("EUR"));

        Transaction load = new Transaction(money, TransactionType.DEBT);

       assertEquals(TransactionType.DEBT,load.getType());
    }
}

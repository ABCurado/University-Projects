/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.mealbooking;

import eapli.framework.domain.Money;
import java.util.Currency;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Rafael Rocha <1140329@isep.ipp.pt>
 */
public class AccountLoadTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureAccountLoadDoesntAcceptNegativeAmountOfMoney() {
        Money money = new Money(-3, Currency.getInstance("EUR"));

        new AccountLoad(money);
    }

    @Test
    public void ensureGetAmountMethodWorksAsIntended() {
        boolean expected = true;

        Money money = new Money(5, Currency.getInstance("EUR"));

        AccountLoad load = new AccountLoad(money);

        if (money.amount() != load.getAmount().amount()) {
            expected = false;
        }

        assertTrue(expected);
    }
}

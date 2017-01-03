/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.mealbooking;

import eapli.framework.domain.Money;
import eapli.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * This class represents the amount of money added to an account.
 *
 * @author Rafael Rocha <1140329@isep.ipp.pt>
 */
@Entity
public class AccountLoad implements Transaction, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long trasactionId;

    private Money money;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar date;

    public AccountLoad(Money money) {
        if (money.amount() < 0) {
            throw new IllegalArgumentException();
        }
        this.money = money;

        this.date = DateTime.now();
    }

    protected AccountLoad() {
        // for ORM
    }

    @Override
    public Money getAmount() {
        return money;

    }

    @Override
    public String toString() {
        return date.toString() + ": added " + money.toString() + " to the account;";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.domain.mealbooking;

import eapli.framework.domain.Money;
import java.io.Serializable;

import eapli.util.Strings;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
// FIXME Account is almost certainly an entity (part of the CafeteriaUser
// aggregate) and not a value object
@Entity
public class Account implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String account;
    private Money balance;

//  @OneToMany(fetch=FetchType.LAZY) Not sure if this is necessary since JPA is returning an error when uncommented
    private List<Transaction> transactionList;

    public Account(String account) {
        if (Strings.isNullOrEmpty(account)) {
            throw new IllegalStateException("Account should neither be null nor empty");
        }
        // FIXME validate invariants, i.e., account regular expression
        this.account = account;
        this.balance = new Money(0, Currency.getInstance(Locale.getDefault()));
        transactionList = new ArrayList<>();
    }

    public Account(String account, String currency) {
        if (Strings.isNullOrEmpty(account)) {
            throw new IllegalStateException("Account should neither be null nor empty");
        }
        // FIXME validate invariants, i.e., account regular expression
        this.account = account;
        this.balance = new Money(0, Currency.getInstance(currency));
        transactionList = new ArrayList<>();
    }

    protected Account() {
        // for ORM
    }

    public boolean addTransaction(Transaction al) {
        if (al == null) {
            throw new NullPointerException();
        }
        if (al instanceof AccountLoad) {
            transactionList.add(al);
            loadBalance(al.getAmount());
            return true;
        }
        return false;
    }

    /**
     * Loads amount to the current account
     *
     * @param ammount to be added to the balance
     * @return operation result
     */
    private boolean loadBalance(Money amount) {
        if (amount == null) {
            throw new NullPointerException();
        }
        balance = balance.add(amount);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }

        final Account that = (Account) o;

        return this.account.equals(that.account);

    }

    @Override
    public int hashCode() {
        return this.account.hashCode();
    }

    @Override
    public String toString() {
        return this.account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public int transactionsNumber() {
        return transactionList.size();
    }
}

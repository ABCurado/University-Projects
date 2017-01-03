package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.mealbooking.Account;
import eapli.ecafeteria.domain.mealbooking.AccountLoad;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.MecanographicNumber;
import eapli.ecafeteria.domain.mealbooking.Transaction;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.Money;
import java.util.Currency;

/**
 *
 * @author Rafael Rocha <1140329@isep.ipp.pt>
 */
public class LoadAccountController implements Controller {

    private Account userAccount;

    public boolean getCafeteriaUserAccount(String id) {
        MecanographicNumber number = new MecanographicNumber(id);
        CafeteriaUser user = PersistenceContext.repositories().cafeteriaUsers().findById(number);

        if (user == null) {
            return false;
        }

        //FIXME avoid having state variables in controller
        this.userAccount = user.account();
        return this.userAccount == null;
    }

    public boolean loadAccount(String amount) {
        Money money = new Money(Double.parseDouble(amount), Currency.getInstance("EUR"));
        Transaction load = new AccountLoad(money);
        return this.userAccount.addTransaction(load);
        //TODO did you forget to save the user account back to the repository?
    }
}

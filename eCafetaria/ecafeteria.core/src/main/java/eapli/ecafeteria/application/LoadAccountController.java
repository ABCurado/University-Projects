package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.MecanographicNumber;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

/**
 *
 * @author Rafael Rocha <1140329@isep.ipp.pt>
 */
public class LoadAccountController implements Controller {

    /**
     * Returns the registered cafeteria user.
     * @param id Mecanographic number
     * @return Cafeteria user
     */
    public CafeteriaUser getCafeteriaUser(String id) {
        MecanographicNumber number = new MecanographicNumber(id);
        CafeteriaUser user = PersistenceContext.repositories().cafeteriaUsers().
                findById(number);

        if (user == null) {
            throw new NoSuchElementException("User not found");
        }

        return user;
    }

    /**
     * Loads the balance of the cafeteria user's account.
     * @param user Cafeteria User
     * @param amount Ammount to be added to the account
     * @return True, if the account's balance is successfully loaded. False, if not.
     * @throws DataConcurrencyException 
     */
    public boolean loadAccount(CafeteriaUser user, BigDecimal amount) throws DataConcurrencyException {
        if (user.registerLoad(amount) == false) {
            return false;
        }

        persist(user);
        return true;
    }
    
    /**
     * Updated the registered cafeteria user on the database.
     * @param user Cafeteria User
     * @throws DataConcurrencyException 
     */
    private void persist(CafeteriaUser user) throws DataConcurrencyException {
        PersistenceContext.repositories().cafeteriaUsers().save(user);
    }
}

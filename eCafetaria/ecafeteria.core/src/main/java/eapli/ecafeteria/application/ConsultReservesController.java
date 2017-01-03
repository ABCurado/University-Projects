package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.util.DateTime;
import java.util.Calendar;
import java.util.NoSuchElementException;

/**
 *
 * @author Carlos Mateus
 */
public class ConsultReservesController {

    /**
     * Method that give the reserves between current date and the end date
     *
     * @param dateInitial variable representing the current date.
     * @param dateFinal variable that represents the end date.
     * @return reserves.
     */
    public Iterable<Reserve> getReservesBetweenDates(Calendar dateInitial,
            Calendar dateFinal) {
        Iterable<CafeteriaUser> user = new UserService().
                cafeteriaUserBySystemUser(AppSettings.getCurrentUser());

        if (!user.iterator().hasNext()) {
            return null;
        }

        if (DateTime.isPreviousDate(dateFinal, dateInitial)) {
            throw new IllegalArgumentException();
        }

        Iterable<Reserve> reserves = PersistenceContext.repositories().
                reserves().FindNextReverves(dateInitial, dateFinal, user.iterator().
                        next());

        if (reserves == null) {
            throw new NoSuchElementException("User not found");
        }

        return reserves;
    }
}

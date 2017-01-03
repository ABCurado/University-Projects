package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.cafeteria.Kitchen.KitchenAlert;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

public class KitchenAlertController implements Controller {

    public boolean addAlert(String name, Double ratio) throws DataIntegrityViolationException {
        KitchenAlert alert = new KitchenAlert(name, ratio);
        return PersistenceContext.repositories().kitchenAlerts().add(alert);
    }

    public void removeAlert(KitchenAlert alert) throws DataIntegrityViolationException, DataConcurrencyException {
        PersistenceContext.repositories().kitchenAlerts().delete(alert);
    }

    public void editAlert(KitchenAlert alert, Double ratio) throws DataConcurrencyException {
        alert.defineRatio(ratio);
        PersistenceContext.repositories().kitchenAlerts().save(alert);
    }

    public Iterable<KitchenAlert> allAlerts() {
        return PersistenceContext.repositories().kitchenAlerts().all();
    }
}

package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.persistence.AssignedRatingRepository;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.ExecutionControlRepository;
import eapli.ecafeteria.persistence.KitchenAlertRepository;
import eapli.ecafeteria.persistence.LastMinuteSaleRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.ecafeteria.persistence.ReserveRepository;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.ecafeteria.persistence.UserRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

	@Override
	public UserRepository users() {
		return new JpaUserRepository();
	}

	@Override
	public JpaDishTypeRepository dishTypes() {
		return new JpaDishTypeRepository();
	}

	@Override
	public JpaOrganicUnitRepository organicUnits() {
		return new JpaOrganicUnitRepository();
	}

	@Override
	public JpaCafeteriaUserRepository cafeteriaUsers() {
		return new JpaCafeteriaUserRepository();
	}

	@Override
	public SignupRequestRepository signupRequests() {
		return new JpaSignupRequestRepository();
	}

	@Override
	public DishRepository dishes() {
		return new JpaDishRepository();
	}

	@Override
	public MealTypeRepository mealTypes() {
		return new JpaMealTypeRepository();
	}

	@Override
	public MealRepository meals() {
		return new JpaMealRepository();
	}

	@Override
	public CashRegisterRepository cashRegisters() {
		return new JpaCashRegisterRepository();
	}

	@Override
	public MenuRepository menus() {
		return new JpaMenuRepository();
	}

	@Override
	public ExecutionControlRepository executionControl() {
		return new JpaExecutionControlRepository();
	}

	@Override
	public ReserveRepository reserves() {
		return new JpaReserveRepository();
	}

	@Override
	public AssignedRatingRepository ratings() {
		return new JpaAssignedRatingRepository();
	}

	@Override
	public ShiftRepository shift() {
		return new JpaShiftRepository();
	}

	@Override
	public KitchenAlertRepository kitchenAlerts() {
		return new JpaKitchenAlertRepository();
	}

	@Override
	public LastMinuteSaleRepository lastMinuteSale() {
		return new JpaLastMinuteSaleRepository();
	}
}

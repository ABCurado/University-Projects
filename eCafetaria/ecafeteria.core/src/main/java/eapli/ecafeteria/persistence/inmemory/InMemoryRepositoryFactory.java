package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.persistence.AssignedRatingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.ExecutionControlRepository;
import eapli.ecafeteria.persistence.KitchenAlertRepository;
import eapli.ecafeteria.persistence.LastMinuteSaleRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.OrganicUnitRepository;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.ecafeteria.persistence.ReserveRepository;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.ecafeteria.persistence.UserRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

	private static UserRepository userRepository = null;
	private static DishTypeRepository dishTypeRepository = null;
	private static OrganicUnitRepository organicUnitRepository = null;
	private static CafeteriaUserRepository cafeteriaUserRepository = null;
	private static SignupRequestRepository signupRequestRepository = null;
	private static MealTypeRepository mealTypeRepository = null;
	private static DishRepository dishRepository = null;
	private static MenuRepository menuRepository = null;
	private static MealRepository mealRepository = null;
	private static CashRegisterRepository cashRegisterRepository = null;
	private static ExecutionControlRepository executionControlRepository = null;
	private static ReserveRepository reserveRepository = null;
	private static AssignedRatingRepository assignedRatingRepository = null;
	private static ShiftRepository shiftRepository = null;
	private static KitchenAlertRepository kitchenAlertRepository = null;
	private static LastMinuteSaleRepository lastMinuteSaleRepository = null;

	@Override
	public OrganicUnitRepository organicUnits() {
		if (organicUnitRepository == null) {
			organicUnitRepository = new InMemoryOrganicUnitRepository();
		}
		return organicUnitRepository;
	}

	@Override
	public CafeteriaUserRepository cafeteriaUsers() {

		if (cafeteriaUserRepository == null) {
			cafeteriaUserRepository = new InMemoryCafeteriaUserRepository();
		}
		return cafeteriaUserRepository;
	}

	@Override
	public SignupRequestRepository signupRequests() {

		if (signupRequestRepository == null) {
			signupRequestRepository = new InMemorySignupRequestRepository();
		}
		return signupRequestRepository;
	}

	@Override
	public DishRepository dishes() {
		if (dishRepository == null) {
			dishRepository = new InMemoryDishRepository();
		}
		return dishRepository;
	}

	@Override
	public UserRepository users() {
		if (userRepository == null) {
			userRepository = new InMemoryUserRepository();
		}

		return userRepository;
	}

	@Override
	public DishTypeRepository dishTypes() {
		if (dishTypeRepository == null) {
			dishTypeRepository = new InMemoryDishTypeRepository();
		}

		return dishTypeRepository;
	}

	@Override
	public MealTypeRepository mealTypes() {
		if (mealTypeRepository == null) {
			mealTypeRepository = new InMemoryMealTypeRepository();
		}

		return mealTypeRepository;
	}

	@Override
	public MenuRepository menus() {
		if (menuRepository == null) {
			menuRepository = new InMemoryMenuRepository();
		}

		return menuRepository;
	}

	@Override
	public MealRepository meals() {
		if (mealRepository == null) {
			mealRepository = new InMemoryMealRepository();
		}

		return mealRepository;
	}

	@Override
	public CashRegisterRepository cashRegisters() {
		if (cashRegisterRepository == null) {
			cashRegisterRepository = new InMemoryCashRegisterRepository();
		}
		return cashRegisterRepository;
	}

	@Override
	public ExecutionControlRepository executionControl() {
		if (executionControlRepository == null) {
			executionControlRepository = new InMemoryExecutionControlRepository();
		}

		return executionControlRepository;
	}

	@Override
	public ReserveRepository reserves() {
		if (reserveRepository == null) {
			reserveRepository = new InMemoryReserveRepository();
		}
		return reserveRepository;
	}

	@Override
	public AssignedRatingRepository ratings() {
		if (assignedRatingRepository == null) {
			assignedRatingRepository = new InMemoryAssignedRatingRepository();
		}
		return assignedRatingRepository;
	}

	@Override
	public ShiftRepository shift() {
		if (shiftRepository == null) {
			shiftRepository = new InMemoryShiftRepository();
		}
		return shiftRepository;
	}

	@Override
	public KitchenAlertRepository kitchenAlerts() {
		if (kitchenAlertRepository == null) {
			kitchenAlertRepository = new InMemoryKitchenAlertRepository();
		}
		return kitchenAlertRepository;
	}

	@Override
	public LastMinuteSaleRepository lastMinuteSale() {
		if (lastMinuteSaleRepository == null) {
			lastMinuteSaleRepository = new InMemoryLastMinuteSaleRepository();
		}
		return lastMinuteSaleRepository;
	}
}

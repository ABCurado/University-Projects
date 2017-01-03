package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.persistence.AccountRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.OrganicUnitRepository;
import eapli.ecafeteria.persistence.RepositoryFactory;
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
	private static AccountRepository accountRepository = null;
	private static DishRepository dishRepository = null;
	private static MenuRepository menuRepository = null;
	private static MealRepository mealRepository = null;
	private static CashRegisterRepository cashRegisterRepository;

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
	public AccountRepository usersAccounts() {

		if (accountRepository == null) {
			accountRepository = new InMemoryAccountRepository();
		}
		return accountRepository;
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
	public MenuRepository menu() {
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
	public MenuRepository menus() {
		return null;
	}
}

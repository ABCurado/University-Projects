/**
 *
 */
package eapli.ecafeteria.persistence;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

	UserRepository users();

	DishTypeRepository dishTypes();

	DishRepository dishes();

	OrganicUnitRepository organicUnits();

	CafeteriaUserRepository cafeteriaUsers();

	SignupRequestRepository signupRequests();

	MealTypeRepository mealTypes();

	MenuRepository menus();

	MenuRepository menu();

	AccountRepository usersAccounts();

	MealRepository meals();

	CashRegisterRepository cashRegisters();

}

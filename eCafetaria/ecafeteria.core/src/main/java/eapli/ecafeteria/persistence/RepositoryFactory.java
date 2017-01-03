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

	MealRepository meals();

	CashRegisterRepository cashRegisters();

	ExecutionControlRepository executionControl();

	ReserveRepository reserves();

	AssignedRatingRepository ratings();

	ShiftRepository shift();

	KitchenAlertRepository kitchenAlerts();

	LastMinuteSaleRepository lastMinuteSale();

}

/**
 *
 */
package eapli.ecafeteria.domain.authz;

import java.util.Collection;

/**
 * @author Paulo Gandra Sousa
 *
 */
public enum ActionRight {
	Administer, SelectMeal, ManageKitchen, ManageMenus, Sale,;

	/**
	 * checks if this action right can be performed by a user with the specified
	 * role types
	 * 
	 * @param roles
	 * @return
	 */
	public boolean canBePerformedBy(Collection<RoleType> roles) {
		if (this == Administer && roles.contains(RoleType.Admin)) {
			return true;
		}
		if (this == SelectMeal && roles.contains(RoleType.User)) {
			return true;
		}
		if (this == ManageKitchen && roles.contains(RoleType.KitchenManager)) {
			return true;
		}
		if (this == ManageMenus && roles.contains(RoleType.MenuManager)) {
			return true;
		}
		if (this == Sale && roles.contains(RoleType.Cashier)) {
			return true;
		}
		return false;
	}
}

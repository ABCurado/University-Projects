package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.visitor.Visitor;
import eapli.util.Console;
import java.util.Iterator;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class MealTypeSelectWidget extends ListWidget<MealType> {

	private int option = -1;

	public MealTypeSelectWidget(Iterable<MealType> source,
								Visitor<MealType> visitor) {
		super(source, visitor);
	}

	@Override
	public void show() {
		super.show();
		System.out.println("0. Default");
		this.option = Console.readOption(1, size(), 0);
	}

	/**
	 *
	 * @return -1 if the user has not yet made a selection 0 if the user
	 * selected "exit" a positive number corresponding to the list index of
	 * source if the user selected an item
	 */
	public int selectedOption() {
		return this.option;
	}

	public MealType selectedElement() {
		switch (this.option) {
			case -1:
			case 0:
				return null;
			default:
				int idx = 0;
				MealType elem = null;
				final Iterator<MealType> it = this.source.iterator();
				while (idx < this.option) {
					elem = it.next();
					idx++;
				}
				return elem;
		}
	}
}

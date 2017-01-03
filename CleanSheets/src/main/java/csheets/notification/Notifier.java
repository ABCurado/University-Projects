package csheets.notification;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Marcelo Barroso 1131399
 */
public abstract class Notifier extends Observable {

	public void notifyChange() {
		this.setChanged();
		this.notifyObservers();
	}

	public void notifyChange(Object object) {
		this.setChanged();
		this.notifyObservers(object);
	}

	@Override
	public synchronized void addObserver(Observer observer) {
		super.addObserver(observer);
	}

}

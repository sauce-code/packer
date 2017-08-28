package com.saucecode.packer;

import java.util.LinkedList;
import java.util.List;

/**
 * An observable item.
 * 
 * @author Torben Kr&uuml;ger
 * 
 * @since Packer 1.0
 *
 */
public abstract class Observable {

	/**
	 * A list of all current observers.
	 */
	private List<IObserver> observers = new LinkedList<IObserver>();

	/**
	 * Attatches an observer to this.
	 * 
	 * @param observer
	 *            observer to be attatched
	 * @return {@code false}, if the observer has been attatched already
	 */
	public boolean attatch(IObserver observer) {
		return (observers.contains(observer)) ? false : observers.add(observer);
	}

	/**
	 * Detatches an observer from this.
	 * 
	 * @param observer
	 *            observer to remove
	 * @return {@code true}, if the this observable contained the observer
	 */
	public boolean detatch(IObserver observer) {
		return observers.remove(observer);
	}

	/**
	 * Alerts all observers.
	 */
	public void alertAll() {
		observers.forEach(e -> e.alert());
	}

}

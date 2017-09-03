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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((observers == null) ? 0 : observers.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Observable other = (Observable) obj;
		if (observers == null) {
			if (other.observers != null)
				return false;
		} else if (!observers.equals(other.observers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Observable [observers=" + observers + "]";
	}

}

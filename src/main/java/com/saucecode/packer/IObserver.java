package com.saucecode.packer;

/**
 * Interface for all observers, which wish to be attatched to
 * {@link Observable}.
 * 
 * @author Torben Kr&uuml;ger
 * 
 * @since Packer 1.0
 *
 */
public interface IObserver {

	/**
	 * Signals the observer that an instance of {@link Observer} has changed.
	 */
	public void alert();

}

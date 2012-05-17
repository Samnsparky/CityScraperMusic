package org.sampottinger.cityscraper.gui;

import org.phineas.core.PhineasBoundable;

/**
 * Button that can be depressed by a user and will stay
 * depressed until unset by the program
 * @author Sam Pottinger
 */
public interface ToggleButton extends PhineasBoundable
{
	/**
	 * Un-sets / releases this button
	 */
	public void reset();
	
	/**
	 * Determines if this button is depressed
	 * @return True if depressed and false otherwise
	 */
	public boolean isActive();
	
	/**
	 * Marks this button as active
	 */
	public void activate();
	
	/**
	 * Add a new object to inform when this toggle button's state is changed
	 * @param newListener The new object to fire events to
	 */
	public void attachListener(ToggleButtonListener newListener);
	
	/**
	 * Removes an object such that it will no longer be inforedm when this 
	 * toggle button's state is changed
	 * @param oldListener The new object to stop firing events to
	 */
	public void deattachListener(ToggleButtonListener oldListener);
}

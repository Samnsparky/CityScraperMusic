package org.sampottinger.cityscraper.gui;

/**
 * Interface for objects that respond to toggle button events.
 * 
 * Interface describing objects capable of responding to changes in
 * toggle button state.
 * 
 * @author Sam Pottinger
 */
public interface ToggleButtonListener
{
	/**
	 * Method called when the toggle button's state is changed.
	 * @param target The toggle button that fired this event.
	 * @param active True if toggle button is selected and false otherwise.
	 */
	public void onStateChange(ToggleButton target, boolean active);
}

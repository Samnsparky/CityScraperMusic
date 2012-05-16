package org.sampottinger.cityscraper.gui.nodeselection;

import org.phineas.core.PhineasCompoundGameObject;

/**
 * Pairing of a toggle button used to select a tab and that
 * tab's contents
 * @author Sam Pottinger
 */
public class TabRecord
{
	private final TabSelectionButton tabSelectButton;
	private final PhineasCompoundGameObject tabContents;
	
	/**
	 * Creates a new pairing between the given button and contents
	 * @param newTabSelectButton The button for this tab
	 * @param newTabContents The contents of this tab
	 */
	public TabRecord(TabSelectionButton newTabSelectButton, PhineasCompoundGameObject newTabContents)
	{
		tabSelectButton = newTabSelectButton;
		tabContents = newTabContents;
	}
	
	/**
	 * Get the button that selects this tab
	 * @return The toggle button that activates this tab
	 */
	public TabSelectionButton getButton()
	{
		return tabSelectButton;
	}
	
	/**
	 * Get the contents of this tab
	 * @return The compound object that this tab contains
	 */
	public PhineasCompoundGameObject getContents()
	{
		return tabContents;
	}
}

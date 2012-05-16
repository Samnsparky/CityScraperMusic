package org.sampottinger.cityscraper.gui.nodeselection;

import java.io.IOException;

import org.sampottinger.cityscraper.gui.ToggleUnderlineButton;

/**
 * Toggle button that activates a tab
 * @author Sam Pottinger
 */
public class TabSelectionButton extends ToggleUnderlineButton
{
	public static enum TabType { FLOW, LOGIC, MATH, SOUND }
	
	private TabType type;

	/**
	 * Creates a new button at the given coordiante with the given image for the given tab
	 * type
	 * @param newX The x (pixel) coordinate of this button
	 * @param newY The y (pixel) coordinate of this button
	 * @param newInactiveLoc The location of the image to use when this button is not activated
	 * @param newActiveLoc The location if the image to use when this button has been clicked on
	 *                  and is active
	 * @param newType The type of tab this button is for
	 * @throws IOException One or more of the images could not be loaded
	 */
	public TabSelectionButton(int newX, int newY, String newInactiveLoc,
			String newActiveLoc, TabType newType) throws IOException 
	{
		super(newX, newY, newInactiveLoc, newActiveLoc);
		type = newType;
	}
	
	/**
	 * Get the type of tab this button is for
	 * @return The type of tab this button targets
	 */
	public TabType getType()
	{
		return type;
	}

}

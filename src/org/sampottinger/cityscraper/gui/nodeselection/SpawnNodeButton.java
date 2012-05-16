package org.sampottinger.cityscraper.gui.nodeselection;

import java.io.IOException;

import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.gui.IconToggleButton;

/**
 * Toggle button to select spawn node type
 * @author Sam Pottinger
 */
public class SpawnNodeButton extends IconToggleButton
{
	private static final String TEXT = "spawn";
	private static final String ICON_LOC = "img/start.png";

	/**
	 * Creates a new spawn node button
	 * @param newX The x position of the button
	 * @param newY The y position of the button
	 * @param newBackgroundDepth The depth at which background elements should be drawn
	 * @param newForegroundDepth The depth at which foreground elements should be drawn
	 * @throws IOException Thrown if the button icon was not found or failed to load due to
	 *                     file system
	 * @throws PhineasException Thrown if the button icon was corrupted
	 */
	public SpawnNodeButton(int newX, int newY, int newBackgroundDepth, int newForegroundDepth) throws IOException,
			PhineasException
	{
		super(newX, newY, TEXT, ICON_LOC, newBackgroundDepth, newForegroundDepth);
	}

}

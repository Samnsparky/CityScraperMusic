package org.sampottinger.cityscraper.gui.nodeselection;

import java.io.IOException;

import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.gui.IconToggleButton;

/**
 * Toggle button to select the "subtract one" node type
 * @author Sam Pottinger
 */
public class SubtractOneNodeButton extends IconToggleButton
{

	private static final String TEXT = "sub one";
	private static final String ICON_LOC = "img/minus_1.png";

	public SubtractOneNodeButton(int newX, int newY, int newBackgroundDepth, int newForegroundDepth) throws IOException,
			PhineasException
	{
		super(newX, newY, TEXT, ICON_LOC, newBackgroundDepth, newForegroundDepth);
	}
	
}

package org.sampottinger.cityscraper.gui.nodeselection;

import java.io.IOException;

import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.gui.IconToggleButton;

/**
 * Toggle button to select the "add one" node type
 * @author Sam Pottinger
 */
public class AddOneNodeButton extends IconToggleButton
{

	private static final String TEXT = "add one";
	private static final String ICON_LOC = "img/plus_1.png";

	public AddOneNodeButton(int newX, int newY, int newBackgroundDepth, int newForegroundDepth) throws IOException,
			PhineasException
	{
		super(newX, newY, TEXT, ICON_LOC, newBackgroundDepth, newForegroundDepth);
	}
	
}

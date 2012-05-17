package org.sampottinger.cityscraper.gui.nodeselection;

import java.io.IOException;

import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.gui.IconToggleButton;

/**
 * Toggle button to select the "greater gate" node type
 * @author Sam Pottinger
 */
public class GreaterGateNodeButton extends IconToggleButton
{

	private static final String TEXT = "fltr greater";
	private static final String ICON_LOC = "img/larger_gate.png";

	public GreaterGateNodeButton(int newX, int newY, int newBackgroundDepth, int newForegroundDepth) throws IOException,
			PhineasException
	{
		super(newX, newY, TEXT, ICON_LOC, newBackgroundDepth, newForegroundDepth);
	}
	
}

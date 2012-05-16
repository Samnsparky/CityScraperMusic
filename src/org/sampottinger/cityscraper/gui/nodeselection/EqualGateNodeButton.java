package org.sampottinger.cityscraper.gui.nodeselection;

import java.io.IOException;

import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.gui.IconToggleButton;

/**
 * Toggle button to select the "equal gate" node type
 * @author Sam Pottinger
 */
public class EqualGateNodeButton extends IconToggleButton
{

	private static final String TEXT = "fltr for equal";
	private static final String ICON_LOC = "img/equal_gate.png";

	public EqualGateNodeButton(int newX, int newY, int newBackgroundDepth, int newForegroundDepth) throws IOException,
			PhineasException
	{
		super(newX, newY, TEXT, ICON_LOC, newBackgroundDepth, newForegroundDepth);
	}
	
}

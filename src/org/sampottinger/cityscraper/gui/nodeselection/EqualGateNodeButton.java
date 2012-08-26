package org.sampottinger.cityscraper.gui.nodeselection;

import java.io.IOException;

import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.gui.IconToggleButton;
import org.sampottinger.cityscraper.nodes.SpecialNodeImgLocResolver;
import org.sampottinger.cityscraper.nodes.SpecialNodePrototype.SpecialNodeType;

/**
 * Toggle button to select the "equal gate" node type
 * @author Sam Pottinger
 */
public class EqualGateNodeButton extends IconToggleButton
{

	private static final String TEXT = "fltr for equal";

	public EqualGateNodeButton(int newX, int newY, int newBackgroundDepth,
			int newForegroundDepth) throws IOException, PhineasException
	{
		super(
				newX,
				newY,
				TEXT,
				SpecialNodeImgLocResolver.getInstance().getLoc(
						SpecialNodeType.EQUAL_GATE), 
				newBackgroundDepth,
				newForegroundDepth
		);
	}
	
}

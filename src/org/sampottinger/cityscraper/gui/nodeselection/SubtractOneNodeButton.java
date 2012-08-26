package org.sampottinger.cityscraper.gui.nodeselection;

import java.io.IOException;

import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.gui.IconToggleButton;
import org.sampottinger.cityscraper.nodes.SpecialNodeImgLocResolver;
import org.sampottinger.cityscraper.nodes.SpecialNodePrototype.SpecialNodeType;

/**
 * Toggle button to select the "subtract one" node type.
 * @author Sam Pottinger
 */
public class SubtractOneNodeButton extends IconToggleButton
{

	private static final String TEXT = "sub one";

	public SubtractOneNodeButton(int newX, int newY, int newBackgroundDepth,
			int newForegroundDepth) throws IOException, PhineasException
	{
		super(
				newX,
				newY,
				TEXT,
				SpecialNodeImgLocResolver.getInstance().getLoc(
						SpecialNodeType.SUB_ONE), 
				newBackgroundDepth,
				newForegroundDepth
		);
	}
	
}

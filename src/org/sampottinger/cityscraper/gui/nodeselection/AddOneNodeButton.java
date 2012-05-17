package org.sampottinger.cityscraper.gui.nodeselection;

import java.io.IOException;

import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.gui.IconToggleButton;
import org.sampottinger.cityscraper.nodes.SpecialNodeImgLocResolver;
import org.sampottinger.cityscraper.nodes.SpecialNodePrototype.SpecialNodeType;

/**
 * Toggle button to select the "add one" node type
 * @author Sam Pottinger
 */
public class AddOneNodeButton extends IconToggleButton
{

	private static final String TEXT = "add one";

	public AddOneNodeButton(int newX, int newY, int newBackgroundDepth, int newForegroundDepth) throws IOException,
			PhineasException
	{
		super(newX, newY, TEXT, SpecialNodeImgLocResolver.getInstance().getLoc(SpecialNodeType.ADD_ONE), 
				newBackgroundDepth, newForegroundDepth);
	}
	
}

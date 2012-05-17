package org.sampottinger.cityscraper.init;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.gui.ToggleButton;
import org.sampottinger.cityscraper.gui.nodeselection.EqualGateNodeButton;
import org.sampottinger.cityscraper.gui.nodeselection.GreaterGateNodeButton;
import org.sampottinger.cityscraper.gui.nodeselection.LessGateNodeButton;
import org.sampottinger.cityscraper.gui.nodeselection.NodeTypeSelectorStateManager;

/**
 * Factory that builds logic toggle buttons for the node type selection component
 * and the logic node prototypes
 * @author Sam Pottinger
 */
public class LogicNodeTypeInitializer implements NodeTypeInitializer
{
	private static LogicNodeTypeInitializer instance = null;
	
	private LogicNodeTypeInitializer() {}
	
	/**
	 * Gets a shared instance of this factory
	 * @return Shared instance of this singleton
	 */
	public static LogicNodeTypeInitializer getInstance() {
		if(instance == null)
			instance = new LogicNodeTypeInitializer();
		return instance;
	}

	@Override
	public Iterable<ToggleButton> createAndRegisterButtons(int x, int y,
			int verticalPadding, int bgDepth, int fgDepth,
			NodeTypeSelectorStateManager selectorManager) throws IOException,
			PhineasException {
		
		int buttonVertSize;
		Collection<ToggleButton> retVal;
		ToggleButton previousButton;
		
		retVal = new LinkedList<ToggleButton>();
		
		// TODO: This is pretty messy
		previousButton = new EqualGateNodeButton(x, y, bgDepth, fgDepth);
		buttonVertSize = previousButton.getHeight();
		retVal.add(previousButton);
		y += buttonVertSize + verticalPadding;
		
		retVal.add(new GreaterGateNodeButton(x, y, bgDepth, fgDepth));
		y += buttonVertSize + verticalPadding;
		
		retVal.add(new LessGateNodeButton(x, y, bgDepth, fgDepth));
		
		return retVal;
		
	}
}

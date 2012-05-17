package org.sampottinger.cityscraper.init;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.gui.ToggleButton;
import org.sampottinger.cityscraper.gui.nodeselection.AddOneNodeButton;
import org.sampottinger.cityscraper.gui.nodeselection.NodeTypeSelectorStateManager;
import org.sampottinger.cityscraper.gui.nodeselection.SubtractOneNodeButton;

/**
 * Factory that builds math toggle buttons for the node type selection component
 * and the math node prototypes
 * @author Sam Pottinger
 */
public class MathNodeTypeInitializer implements NodeTypeInitializer
{
	private static MathNodeTypeInitializer instance = null;
	
	private MathNodeTypeInitializer() {}
	
	/**
	 * Gets a shared instance of this factory
	 * @return Shared instance of this singleton
	 */
	public static MathNodeTypeInitializer getInstance() {
		if(instance == null)
			instance = new MathNodeTypeInitializer();
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
		previousButton = new AddOneNodeButton(x, y, bgDepth, fgDepth);
		buttonVertSize = previousButton.getHeight();
		retVal.add(previousButton);
		y += buttonVertSize + verticalPadding;
		
		retVal.add(new SubtractOneNodeButton(x, y, bgDepth, fgDepth));
		
		return retVal;
		
	}
}

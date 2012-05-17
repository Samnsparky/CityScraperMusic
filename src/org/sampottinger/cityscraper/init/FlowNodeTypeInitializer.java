package org.sampottinger.cityscraper.init;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.gui.ToggleButton;
import org.sampottinger.cityscraper.gui.nodeselection.NodeTypeSelectorStateManager;
import org.sampottinger.cityscraper.gui.nodeselection.SpawnNodeButton;

/**
 * Factory for flow node prototypes and their selection buttons
 * @author Sam Pottinger
 */
public class FlowNodeTypeInitializer implements NodeTypeInitializer
{
	private static FlowNodeTypeInitializer instance = null;
	
	private FlowNodeTypeInitializer() {}
	
	/**
	 * Gets a shared instance of this factory
	 * @return Shared instance of this singleton
	 */
	public static FlowNodeTypeInitializer getInstance() {
		if(instance == null)
			instance = new FlowNodeTypeInitializer();
		return instance;
	}

	@Override
	public Iterable<ToggleButton> createAndRegisterButtons(int x, int y,
			int verticalPadding, int bgDepth, int fgDepth,
			NodeTypeSelectorStateManager selectorManager) throws IOException,
			PhineasException {
		Collection<ToggleButton> retVal = new LinkedList<ToggleButton>();
		retVal.add(new SpawnNodeButton(x, y, bgDepth, fgDepth));
		return retVal;
	}
}

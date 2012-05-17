package org.sampottinger.cityscraper.init;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.gui.ToggleButton;
import org.sampottinger.cityscraper.gui.nodeselection.NodeTypeSelectorStateManager;
import org.sampottinger.cityscraper.gui.nodeselection.SpawnNodeButton;
import org.sampottinger.cityscraper.nodes.SpecialNodePrototype;
import org.sampottinger.cityscraper.nodes.SpecialNodePrototype.SpecialNodeType;

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

	@SuppressWarnings("rawtypes")
	@Override
	public Iterable<ToggleButton> createAndRegisterButtons(int x, int y,
			int verticalPadding, int bgDepth, int fgDepth,
			NodeTypeSelectorStateManager selectorManager) throws IOException,
			PhineasException {
		
		Collection<ToggleButton> retVal = new LinkedList<ToggleButton>();
		
		SpawnNodeButton spawnNodeButton = new SpawnNodeButton(x, y, bgDepth, fgDepth);
		
		retVal.add(spawnNodeButton);
		selectorManager.registerButton(new SpecialNodePrototype(SpecialNodeType.SPAWN_NODE), spawnNodeButton);
		
		return retVal;
	}
}

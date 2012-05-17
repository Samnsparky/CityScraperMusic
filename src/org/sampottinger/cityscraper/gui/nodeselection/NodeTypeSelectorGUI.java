package org.sampottinger.cityscraper.gui.nodeselection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.phineas.core.PhineasCompoundGameObject;
import org.sampottinger.cityscraper.gui.ToggleButton;
import org.sampottinger.cityscraper.gui.ToggleButtonListener;
import org.sampottinger.cityscraper.gui.nodeselection.TabSelectionButton.TabType;
import org.sampottinger.cityscraper.nodes.CityScraperNodePrototype;

/**
 * Listener to gui components related to the selection of node types
 * @author Sam Pottinger
 */
public class NodeTypeSelectorGUI implements PhineasCompoundGameObject
{
	private Collection<TabRecord> tabs;
	private NodeTypeSelectorJanitor janitor;
	private NodeTypeSelectorStateManager stateManager;
	
	/**
	 * Create a new node selector with the given tabs
	 * @param newTabs The tabs that this node selector should use
	 * @param newJanitor The janitor to use to interface with the game
	 * @param newStateManager The manager that handles state of actual node selection toggle
	 *                     buttons
	 */
	public NodeTypeSelectorGUI(NodeTypeSelectorJanitor newJanitor, NodeTypeSelectorStateManager newStateManager)
	{
		tabs = new ArrayList<TabRecord>();
		janitor = newJanitor;
		stateManager = newStateManager;
	}
	
	/**
	 * Adds another tab for this selector to maintain
	 * @param newTab The new tab to maintain
	 */
	public void addTab(TabRecord newTab)
	{
		final TabSelectionButton targetButton = newTab.getButton();
		tabs.add(newTab);
		
		targetButton.attachListener(new ToggleButtonListener()
		{

			@Override
			public void onStateChange(ToggleButton target, boolean active)
			{
				if(active)
					showTab(targetButton.getType());
			}
			
		});
	}
	
	/**
	 * Shows the tab of the given type
	 * @param targetTabType The type of tab to show
	 */
	public void showTab(TabType targetTabType)
	{
		TabSelectionButton currentButton;
		for(TabRecord tab : tabs)
		{
			currentButton = tab.getButton();
			
			if(currentButton.getType() != targetTabType)
			{
				currentButton.reset();
				janitor.removeTabContents(tab.getContents());
			}
			else
			{
				currentButton.activate();
				janitor.addTabContents(tab.getContents());
			}
		}
	}
	
	// TODO: Returning null is sooo C++. Either way, we shouldn't do it.
	/**
	 * Get the prototype corresponding to the currently selected toggle button
	 * @return Prototype for selected node type or null if none selected
	 */
	public CityScraperNodePrototype getSelectedPrototype()
	{
		return stateManager.getSelectedPrototype();
	}

	@Override
	public Iterable<Object> getComponents()
	{
		Collection<Object> retVal = new LinkedList<Object>();
		
		for(TabRecord tab : tabs)
			retVal.add(tab.getButton());
		
		return retVal;
	}
}

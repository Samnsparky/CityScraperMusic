package org.sampottinger.cityscraper.gui.nodeselection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.phineas.core.PhineasCompoundGameObject;
import org.sampottinger.cityscraper.gui.ToggleButton;
import org.sampottinger.cityscraper.gui.ToggleButtonListener;
import org.sampottinger.cityscraper.gui.nodeselection.TabSelectionButton.TabType;

/**
 * Listener to gui components related to the selection of node types
 * @author Sam Pottinger
 */
public class NodeTypeSelector implements PhineasCompoundGameObject
{
	private Collection<TabRecord> tabs;
	private NodeTypeSelectorJanitor janitor;
	
	/**
	 * Create a new node selector with the given tabs
	 * @param newTabs The tabs that this node selector should use
	 * @param newJanitor The janitor to use to interface with the game
	 */
	public NodeTypeSelector(NodeTypeSelectorJanitor newJanitor)
	{
		tabs = new ArrayList<TabRecord>();
		janitor = newJanitor;
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
				janitor.addTabContents(tab.getContents());
			}
		}
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

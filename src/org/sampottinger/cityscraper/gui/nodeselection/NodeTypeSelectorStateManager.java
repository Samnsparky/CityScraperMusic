package org.sampottinger.cityscraper.gui.nodeselection;

import java.util.ArrayList;
import java.util.Collection;

import org.sampottinger.cityscraper.gui.ToggleButton;
import org.sampottinger.cityscraper.gui.ToggleButtonListener;
import org.sampottinger.cityscraper.nodes.CityScraperNodePrototype;

/**
 * Manager for state of toggle buttons that select actual node types through
 * the NodeTypeSelectorGUI.
 * @author Sam Pottinger
 */
public class NodeTypeSelectorStateManager
{	
	// All of the toggle buttons controlling selected node type
	private Collection<ToggleButton> toggleGroup; 
	
	private CityScraperNodePrototype selectedPrototype;
	
	public NodeTypeSelectorStateManager()
	{
		toggleGroup = new ArrayList<ToggleButton>();
		selectedPrototype = null;
	}
	
	/**
	 * Creates a new association between the given node prototype and the 
	 * toggle button used to select that node type.
	 * @param nodePrototype The prototype for the node type that the given
	 * 		button selects for.
	 * @param selectionButton The button being registered.
	 */
	public void registerButton(CityScraperNodePrototype nodePrototype,
			ToggleButton selectionButton)
	{
		// Make final references for anonymous inner class
		final CityScraperNodePrototype innerNodePrototype = nodePrototype;
		
		// Add to toggle group to be deselected when another in the group is
		toggleGroup.add(selectionButton);
		
		// Register listener (closure over private attributes anyone?)
		selectionButton.attachListener(new ToggleButtonListener()
		{

			@Override
			public void onStateChange(ToggleButton target, boolean active)
			{
				// Ignore de-activations
				if(!active)
					return;
				
				setSelectedPrototype(innerNodePrototype);
				
				resetAllToggleButtonsBut(target);
			}
			
		});
	}
	
	/**
	 * Set the prototype to report as currently selected.
	 * @param prototype The prototype to report as active.
	 */
	private void setSelectedPrototype(CityScraperNodePrototype prototype)
	{
		selectedPrototype = prototype;
	}
	
	/**
	 * Resets all of the registered toggle buttons but the given exception.
	 * @param exception The toggle button to leave in its current state.
	 */
	private void resetAllToggleButtonsBut(ToggleButton exception)
	{
		for(ToggleButton button : toggleGroup)
		{
			if(!button.equals(exception))
				button.reset();
		}
	}
	
	// TODO: Returning null is sooo C++. Either way, we shouldn't do it.
	/**
	 * Get the prototype corresponding to the currently selected toggle button.
	 * @return Prototype for selected node type or null if none selected.
	 */
	public CityScraperNodePrototype getSelectedPrototype()
	{
		return selectedPrototype;
	}
}

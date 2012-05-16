package org.sampottinger.cityscraper.gui.nodeselection;

import org.phineas.core.PhineasCompoundGameObject;
import org.phineas.core.PhineasGameFacade;

/**
 * Abstraction layer between a node selector and the game it is running in
 * that cleans up unwanted elements and adds new ones
 * @author Sam Pottinger
 */
public class NodeTypeSelectorJanitor
{
	private PhineasGameFacade targetFacade;
	
	/**
	 * Creates a new decorator around the given facade
	 * @param newTargetFacade The game facade to abstract
	 */
	public NodeTypeSelectorJanitor(PhineasGameFacade newTargetFacade)
	{
		targetFacade = newTargetFacade;
	}
	
	/**
	 * Removes the given tab contents from the game
	 * @param target The tab contents to remove
	 */
	public void removeTabContents(PhineasCompoundGameObject target)
	{
		targetFacade.removeEntity(target);
	}
	
	/**
	 * Adds the given tab contents to the game
	 * @param target The tab contents to register
	 */
	public void addTabContents(PhineasCompoundGameObject target)
	{
		targetFacade.addEntity(target);
	}
}

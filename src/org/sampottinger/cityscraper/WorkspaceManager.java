package org.sampottinger.cityscraper;

import org.phineas.core.PhineasGameFacade;

/**
 * Facade / decorator for workspace related support objects and the underlying game engine
 * @author Sam Pottinger
 */
public class WorkspaceManager
{
	private WorkspaceGrid grid;
	private PhineasGameFacade game;
	
	/**
	 * Create a workspace manager that abstracts the given grid and game
	 * @param targetGrid The occupancy grid to operate on
	 * @param targetGame The game that will manage workspace elements
	 */
	public WorkspaceManager(WorkspaceGrid targetGrid, PhineasGameFacade targetGame)
	{
		grid = targetGrid;
		game = targetGame;
	}
	
	/**
	 * Registers the given element with the game and occupancy grid
	 * @param newElement The new element to add to the game
	 */
	public void createElement(WorkspaceElement newElement)
	{
		grid.markOccupied(newElement);
		game.addEntity(newElement);
	}
}

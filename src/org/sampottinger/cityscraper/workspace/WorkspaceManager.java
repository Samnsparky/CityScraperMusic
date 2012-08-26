package org.sampottinger.cityscraper.workspace;

import java.util.Collection;

import org.phineas.contrib.PhineasLocation;
import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasGameFacade;

/**
 * Facade / decorator for workspace related support objects and the underlying
 * game engine.
 * @author Sam Pottinger
 */
public class WorkspaceManager
{
	private WorkspaceGrid grid;
	private PhineasGameFacade game;
	
	private int minX;
	private int minY;
	private int maxX;
	private int maxY;
	
	/**
	 * Create a workspace manager that abstracts the given grid and game.
	 * @param targetGrid The occupancy grid to operate on.
	 * @param targetGame The game that will manage workspace elements.
	 */
	public WorkspaceManager(WorkspaceGrid targetGrid,
			PhineasGameFacade targetGame, int newStartX, int newStartY,
			int newWidth, int newHeight)
	{
		grid = targetGrid;
		game = targetGame;
		
		minX = newStartX;
		minY = newStartY;
		maxX = minX + newWidth;
		maxY = minY + newHeight;
	}
	
	/**
	 * Registers the given element with the game and occupancy grid.
	 * @param newElement The new element to add to the game.
	 */
	public void createElement(WorkspaceElement newElement)
	{
		grid.markOccupied(newElement);
		game.addEntity(newElement);
	}
	
	/**
	 * Determines if the given position has something in it.
	 * Values outside the range of the workspace are reported occupied.
	 * @param x The horizontal coordinate to check.
	 * @param y The vertical coordinate to check.
	 * @return true if occupied and false otherwise.
	 */
	public boolean isFree(int x, int y)
	{
		if(x < minX || x > maxX || y < minY || y > maxY)
			return false;
		
		return !grid.isOccupied(x, y);
	}
	
	/**
	 * Determine if the element's area is completely free.
	 * @param element The element to test.
	 * @return true if any of the area covered by the given element is occupied
	 * 		and false if its bounding box is completely open.
	 */
	public boolean isOccupied(PhineasBoundable boundable)
	{
		return grid.isOccupied(boundable);
	}
	
	/**
	 * Get the element at the given position.
	 * @param x The x position of the space whose occupant is desired.
	 * @param y The y position of the space whose occupant is desired.
	 * @return The element at the given position or null if none there.
	 */
	public WorkspaceElement getElementAt(int x, int y)
	{
		return grid.getElementAt(x, y);
	}
	
	/**
	 * Get the size of each "space" in this workspace's grid.
	 * @return PhineasBoundable describing the size of a space in this grid.
	 */
	public PhineasBoundable getStepBounds()
	{
		return new PhineasBoundable()
		{

			@Override
			public int getX()
			{
				return 0;
			}

			@Override
			public int getY()
			{
				return 0;
			}

			@Override
			public int getWidth()
			{
				return grid.getXStep();
			}

			@Override
			public int getHeight()
			{
				return grid.getYStep();
			}
			
		};
	}

	/**
	 * Get the bounds of the workspace.
	 * @return PhineasBoundable describing the constraints of the workspace.
	 */
	public PhineasBoundable getBounds()
	{
		return new PhineasBoundable()
		{

			@Override
			public int getX()
			{
				return minX;
			}

			@Override
			public int getY()
			{
				return minY;
			}

			@Override
			public int getWidth()
			{
				return maxX - getX();
			}

			@Override
			public int getHeight()
			{
				return maxY - getY();
			}
			
		};
	}

	/**
	 * Get a collection of locations that the given object occupies.
	 * 
	 * Get the locations of the spaces that the given boundable object would
	 * occupy / does occupy in this workspace.
	 * 
	 * @param target The object to find the spaces for.
	 * @return Collection of locations of spaces occupied by this target.
	 */
	public Collection<PhineasLocation> getSpacesOccupiedBy(
			PhineasBoundable target) {
		return grid.getSpacesOccupiedBy(target);
	}
}

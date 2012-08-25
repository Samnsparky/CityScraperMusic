package org.sampottinger.cityscraper.workspace;

import java.util.Collection;
import java.util.LinkedList;

import org.phineas.core.PhineasBoundable;
import org.sampottinger.cityscraper.SimpleCoordinatePair;

/**
 * Data structure managing free and occupied spaces in the workspace grid
 * @author Sam Pottinger
 */
public class WorkspaceGrid
{
	private TwoDimensionalWorkspaceElementMap occupancyGrid;
	private int xStep;
	private int yStep;
	
	/**
	 * Creates a new empty simulation grid that has discrete sized spaces
	 * that are either occupied or not
	 * @param newXStep The horizontal size of one of the discrete state spaces
	 * @param newYStep The vertical size of one of the discrete state spaces
	 */
	public WorkspaceGrid(int startX, int startY, int width, int height, int newXStep, int newYStep)
	{
		occupancyGrid = new TwoDimensionalWorkspaceElementMap(startX, startY, width, height);
		xStep = newXStep;
		yStep = newYStep;
	}
	
	/**
	 * Mark the area that the given boundable covers as "occupied"
	 * @param newElement The boundable whose area should be marked as occupied
	 */
	public void markOccupied(WorkspaceElement newElement)
	{
		Iterable<SimpleCoordinatePair> occupiedSpaces = getSpacesOccupiedBy(newElement);
		for(SimpleCoordinatePair pos : occupiedSpaces)
			occupancyGrid.put(pos.getX(), pos.getY(), newElement);
	}
	
	/**
	 * Determine if the given position is already occupied by something
	 * @param x The horizontal position to test
	 * @param y The vertical position to test
	 * @return true if already occupied and false otherwise
	 */
	public boolean isOccupied(int x, int y)
	{	
		return occupancyGrid.containsKey(convertXPos(x), convertYPos(y));
	}
	
	/**
	 * Determine if the element's area is completely free
	 * @param element The element to test
	 * @return true if any of the area covered by the given element is occupied and
	 *         false if its bounding box is completely open
	 */
	public boolean isOccupied(PhineasBoundable element)
	{
		Iterable<SimpleCoordinatePair> occupiedSpaces = getSpacesOccupiedBy(element);
		for(SimpleCoordinatePair pos : occupiedSpaces)
		{
			// TODO: OK, that is a shameful kludge. Key is x and value is y... Not ok.
			if(isOccupied(pos.getX(), pos.getY()))
				return true;
		}
		return false;
	}
	
	/**
	 * Mark the area occupied by the given boundable as free
	 * @param oldElement The boundable whose area will be marked as free
	 */
	public void markUnoccupied(PhineasBoundable oldElement)
	{
		Iterable<SimpleCoordinatePair> occupiedSpaces = getSpacesOccupiedBy(oldElement);
		for(SimpleCoordinatePair pos : occupiedSpaces)
			occupancyGrid.remove(pos.getX(), pos.getY());
	}
	
	/**
	 * Determine the x position of the start of the "space" the general coordinate
	 * falls into 
	 * @param positionToConvert The general x position to find the space for
	 * @return The start x position of the space the provided general x position
	 *         falls into
	 */
	private int convertXPos(int positionToConvert)
	{
		return positionToConvert - (positionToConvert % xStep);
	}
	
	/**
	 * Determine the y position of the start of the "space" the general coordinate
	 * falls into 
	 * @param positionToConvert The general y position to find the space for
	 * @return The start y position of the space the provided general x position
	 *         falls into
	 */
	private int convertYPos(int positionToConvert)
	{
		return positionToConvert - (positionToConvert % yStep);
	}
	
	/**
	 * Determine the upper left corner positions of the spaces that the given element
	 * spans across
	 * @param newElement The element to find the spaces for
	 * @return Iterable over pairs of spaces' x and y coordinate values that the given element
	 *                  spans over
	 */
	public Collection<SimpleCoordinatePair> getSpacesOccupiedBy(PhineasBoundable newElement)
	{
		Collection<SimpleCoordinatePair> retList;
		int rawX;
		int rawY;
		int fixedX;
		int fixedY;
		int curX;
		int curY;
		int maxX;
		int maxY;
		int fixedMaxX;
		int fixedMaxY;
		
		// Allocate for return value
		retList = new LinkedList<SimpleCoordinatePair>();
		
		// Get position of nearest fitting space
		rawX = newElement.getX();
		rawY = newElement.getY();
		fixedX = convertXPos(rawX);
		fixedY = convertYPos(rawY);
		
		// Find ends of the boundable
		maxX = rawX + newElement.getWidth();
		fixedMaxX = convertXPos(maxX);
		maxY = rawY + newElement.getHeight();
		fixedMaxY = convertYPos(maxY);
		
		// Register
		for(curX = fixedX; curX < fixedMaxX; curX+=xStep)
		{
			for(curY = fixedY; curY < fixedMaxY; curY+=yStep)
			{
				retList.add(new SimpleCoordinatePair(curX, curY));
			}
		}
		
		return retList;
	}
	
	/**
	 * Get the element at the given position
	 * @param x The x position of the space whose occupant is desired
	 * @param y The y position of the space whose occupant is desired
	 * @return The element at the given position or null if none there
	 */
	public WorkspaceElement getElementAt(int x, int y)
	{
		return occupancyGrid.get(x, y);
	}
	
	public int getXStep()
	{
		return xStep;
	}
	
	public int getYStep()
	{
		return yStep;
	}
}

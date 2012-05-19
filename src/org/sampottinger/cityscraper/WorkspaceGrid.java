package org.sampottinger.cityscraper;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import org.phineas.core.PhineasBoundable;

/**
 * Data structure managing free and occupied spaces in the workspace grid
 * @author Sam Pottinger
 */
public class WorkspaceGrid
{
	private AbstractMap<SimpleImmutableEntry<Integer, Integer>, WorkspaceElement> occupancyGrid;
	private int xStep;
	private int yStep;
	
	/**
	 * Creates a new empty simulation grid that has discrete sized spaces
	 * that are either occupied or not
	 * @param newXStep The horizontal size of one of the discrete state spaces
	 * @param newYStep The vertical size of one of the discrete state spaces
	 */
	public WorkspaceGrid(int newXStep, int newYStep)
	{
		occupancyGrid = new HashMap<SimpleImmutableEntry<Integer, Integer>, WorkspaceElement>();
		xStep = newXStep;
		yStep = newYStep;
	}
	
	/**
	 * Mark the area that the given boundable covers as "occupied"
	 * @param newElement The boundable whose area should be marked as occupied
	 */
	public void markOccupied(WorkspaceElement newElement)
	{
		Iterable<SimpleImmutableEntry<Integer, Integer>> occupiedSpaces = getSpacesOccupiedBy(newElement);
		for(SimpleImmutableEntry<Integer, Integer> pos : occupiedSpaces)
			occupancyGrid.put(pos, newElement);
	}
	
	/**
	 * Determine if the given position is already occupied by something
	 * @param x The horizontal position to test
	 * @param y The vertical position to test
	 * @return true if already occupied and false otherwise
	 */
	public boolean isOccupied(int x, int y)
	{
		SimpleImmutableEntry<Integer, Integer> pos = new SimpleImmutableEntry<Integer, Integer>(x, y);
		
		return occupancyGrid.containsKey(pos);
	}
	
	/**
	 * Determine if the element's area is completely free
	 * @param element The element to test
	 * @return true if any of the area covered by the given element is occupied and
	 *         false if its bounding box is completely open
	 */
	public boolean isOccupied(PhineasBoundable element)
	{
		Iterable<SimpleImmutableEntry<Integer, Integer>> occupiedSpaces = getSpacesOccupiedBy(element);
		for(SimpleImmutableEntry<Integer, Integer> pos : occupiedSpaces)
		{
			// TODO: OK, that is a shameful kludge. Key is x and value is y... Not ok.
			if(isOccupied(pos.getKey(), pos.getValue()))
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
		Iterable<SimpleImmutableEntry<Integer, Integer>> occupiedSpaces = getSpacesOccupiedBy(oldElement);
		for(SimpleImmutableEntry<Integer, Integer> pos : occupiedSpaces)
			occupancyGrid.remove(pos);
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
	private Iterable<SimpleImmutableEntry<Integer, Integer>> getSpacesOccupiedBy(PhineasBoundable newElement)
	{
		Collection<SimpleImmutableEntry<Integer, Integer>> retList;
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
		retList = new LinkedList<SimpleImmutableEntry<Integer, Integer>>();
		
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
				retList.add(new SimpleImmutableEntry<Integer, Integer>(curX, curY));
			}
		}
		
		return retList;
	}
}

package org.sampottinger.cityscraper.workspace;

import java.util.HashMap;
import java.util.Map;

/**
 * A map of 2D workspace elements.
 * @author Sam Pottinger
 */
public class TwoDimensionalWorkspaceElementMap
{
	private int startX;
	private int startY;
	private int height;
	private Map<Integer, WorkspaceElement> elements;

	/**
	 * Create a new empty map.
	 * @param newStartX The minimum x coordinate for the workspace.
	 * @param newStartY The minimum y coordinate for the workspace.
	 * @param newWidth The width in pixels of the workspace.
	 * @param newHeight The height in pixels of the workspace.
	 */
	public TwoDimensionalWorkspaceElementMap(int newStartX, int newStartY,
			int newWidth, int newHeight)
	{
		startX = newStartX;
		startY = newStartY;
		height = newHeight;
		elements = new HashMap<Integer, WorkspaceElement>();
	}

	/**
	 * Add a new element to this workspace.
	 * @param x The horizontal pixel coordinate of the position to add this
	 * 		element at.
	 * @param y The vertical pixel coordinate of the position to add this
	 * 		element at.
	 * @param newElement The element to add to this map.
	 */
	public void put(int x, int y, WorkspaceElement newElement)
	{
		elements.put(getHashForPos(x, y), newElement);
	}

	/**
	 * Determines if the given space is occupied.
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean containsKey(int x, int y)
	{
		return elements.containsKey(getHashForPos(x, y));
	}

	/**
	 * Remove the elements at the given position.
	 * @param x The horizontal pixel coordinate to remove elements from.
	 * @param y The vertical pixel coordinate to remove elements from.
	 */
	public void remove(int x, int y)
	{
		elements.remove(getHashForPos(x, y));
	}

	/**
	 * Get the first element at the given position.
	 * @param x The x coordinate of the desired element.
	 * @param y The y coordinate of the desired element.
	 * @return
	 */
	public WorkspaceElement get(int x, int y)
	{
		return elements.get(getHashForPos(x, y));
	}
	
	/**
	 * Get a unique hash value for the given position.
	 * @param x The x coordinate of the position the hash is being generated
	 * 		for.
	 * @param y The y coordiante of the position that the hash is being
	 * 		generated for.
	 * @return Unique numerical hash value for the given position.
	 */
	private int getHashForPos(int x, int y)
	{
		return (x - startX) + (y - startY) * height;
	}

}

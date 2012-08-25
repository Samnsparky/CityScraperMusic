package org.sampottinger.cityscraper.workspace;

import java.util.HashMap;
import java.util.Map;

public class TwoDimensionalWorkspaceElementMap
{
	private int startX;
	private int startY;
	private int height;
	private Map<Integer, WorkspaceElement> elements;

	public TwoDimensionalWorkspaceElementMap(int newStartX, int newStartY, int newWidth,
			int newHeight)
	{
		startX = newStartX;
		startY = newStartY;
		height = newHeight;
		elements = new HashMap<Integer, WorkspaceElement>();
	}

	public void put(int x, int y, WorkspaceElement newElement)
	{
		elements.put(getHashForPos(x, y), newElement);
	}

	public boolean containsKey(int x, int y)
	{
		return elements.containsKey(getHashForPos(x, y));
	}

	public void remove(int x, int y)
	{
		elements.remove(getHashForPos(x, y));
	}

	public WorkspaceElement get(int x, int y)
	{
		return elements.get(getHashForPos(x, y));
	}

	public void markOccupied(int x, int y) 
	{
		put(x, y, null);
	}
	
	private int getHashForPos(int x, int y)
	{
		return (x - startX) + (y - startY) * height;
	}

}

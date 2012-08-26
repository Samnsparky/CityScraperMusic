package org.sampottinger.cityscraper.connection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Iterable that provides an iterator over backlinks of a DijkstraSpace.
 * 
 * Iterable that traverses all of the "backlinks" or pointers created while
 * finding a path that delineate the final path found. Giving it a DijkstraSpace
 * after path planning will iterate backwards over the connections that form the
 * shortest path from the starting space to the provided space.
 * 
 * @author Sam Pottinger
 *
 * @param <T> The type of object encapsulated in the DijkstraSpaces in the given
 * 		DijkstraConnections.
 */
public class BacklinkIterable<T> implements Iterable<DijkstraConnection<T>> {
	
	private Iterator<DijkstraConnection<T>> resultIterator;
	private int length;

	/**
	 * Create iterable for the shortest path from the start node to this space.
	 * 
	 * Creates an iterable that produces the connections that delineate the
	 * shortest path from the starting node to the given space. Note that the
	 * connections are produced backwards.
	 * 
	 * @param currentSpace Space to generate an iterable over connections for.
	 * @throws SpaceNotFoundException Thrown if space is not found when
	 * 		following a path.
	 */
	public BacklinkIterable(DijkstraSpace<T> currentSpace)
			throws SpaceNotFoundException
	{
		DijkstraConnection<T> currentBacklink;
		List<DijkstraConnection<T>> path =
				new ArrayList<DijkstraConnection<T>>();
		
		// Follow backlinks
		currentBacklink = currentSpace.getBacklink();
		while(currentBacklink != null)
		{
			path.add(currentBacklink);
			currentSpace = currentBacklink.getOther(currentSpace);
			currentBacklink = currentSpace.getBacklink();
		}
		
		resultIterator = new ReverseListIterator<DijkstraConnection<T>>(path);
		length = path.size();
	}

	@Override
	public Iterator<DijkstraConnection<T>> iterator()
	{
		return resultIterator;
	}
	
	public int getLength()
	{
		return length;
	}

}

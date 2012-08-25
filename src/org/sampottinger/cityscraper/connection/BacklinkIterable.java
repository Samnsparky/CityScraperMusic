package org.sampottinger.cityscraper.connection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BacklinkIterable<T> implements Iterable<DijkstraConnection<T>> {
	
	private Iterator<DijkstraConnection<T>> resultIterator;
	private int length;

	public BacklinkIterable(DijkstraSpace<T> currentSpace) throws SpaceNotFoundException
	{
		DijkstraConnection<T> currentBacklink;
		List<DijkstraConnection<T>> path = new ArrayList<DijkstraConnection<T>>();
		
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

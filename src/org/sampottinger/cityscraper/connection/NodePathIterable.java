package org.sampottinger.cityscraper.connection;

import java.util.Iterator;

import org.phineas.core.PhineasLocateable;

/**
 * Iterable that provides locations for a path between nodes.
 * @author Sam Pottinger
 */
public class NodePathIterable implements Iterable<PhineasLocateable> {
	
	Iterable<DijkstraConnection<PhineasLocateable>> nativePathIterable;
	DijkstraSpace<PhineasLocateable> startSpace;
	
	/**
	 * Create a new iterable that traverses DijkstraConnections.
	 * @param nativePath The DijkstraConnection path to iterate over.
	 * @param newStartSpace The space where the path starts.
	 */
	public NodePathIterable(
			Iterable<DijkstraConnection<PhineasLocateable>> nativePath,
			DijkstraSpace<PhineasLocateable> newStartSpace)
	{
		nativePathIterable = nativePath;
		startSpace = newStartSpace;
	}

	@Override
	public Iterator<PhineasLocateable> iterator()
	{
		final Iterator<DijkstraConnection<PhineasLocateable>> itr = 
				nativePathIterable.iterator();
		return new Iterator<PhineasLocateable>()
		{
			private DijkstraSpace<PhineasLocateable> previousSpace = startSpace;

			@Override
			public boolean hasNext() {
				return itr.hasNext();
			}

			@Override
			public PhineasLocateable next()
			{
				DijkstraSpace<PhineasLocateable> newSpace;

				try {
					newSpace = itr.next().getOther(previousSpace);
				} catch (SpaceNotFoundException e) {
					throw new RuntimeException("Unexepected program state");
				}
				
				previousSpace = newSpace;
				return newSpace.getTarget();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		};
	}

}

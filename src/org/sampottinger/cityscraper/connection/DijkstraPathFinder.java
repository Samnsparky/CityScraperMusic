package org.sampottinger.cityscraper.connection;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Finds shortest path between between DijkstraSpaces.
 * @author Sam Pottinger
 */
public class DijkstraPathFinder<T>
{
	/**
	 * Find a path between the given starting space and closest "goal" spaces.
	 * @param start The space to start on.
	 * @return Iterable of connections to take to get to the end space or null
	 * 		if no path found. Includes connection that starts with starting
	 * 		node and a connection that ends with the ending node.
	 * @throws SpaceNotFoundException Error encountered when traversing given
	 * 		space and its connections / larger graph.
	 */
	public DijkstraPathFinderResult<T> findPath(DijkstraSpace<T> start)
			throws SpaceNotFoundException
	{
		DijkstraSpace<T> currentSpace;
		Iterable<DijkstraSpace<T>> updatedNeighbors;
		PriorityQueue<DijkstraSpace<T>> q =
				new PriorityQueue<DijkstraSpace<T>>();
		
		// Add starting position with starting distance of zero
		start.setTotalDistance(0);
		q.add(start);

		// Keep traversing until solution found or out of nodes
		while(q.peek() != null && !q.peek().wasVisited())
		{
			currentSpace = q.poll();
			
			// Check to see if path found
			if(currentSpace.isEnding())
			{
				BacklinkIterable<T> path =
						new BacklinkIterable<T>(currentSpace);

				return new DijkstraPathFinderResult<T>(path,
						path.getLength());
			}
			
			// Update neighbors
			updatedNeighbors = traverse(currentSpace);
			for(DijkstraSpace<T> neighbor : updatedNeighbors)
			{
				q.remove(neighbor);
				q.add(neighbor);
			}
			
			// Never visit this node again
			currentSpace.setVisited();
		}
		
		// If never returned, no path found
		return new DijkstraPathFinderResult<T>();
	}
	
	/**
	 * Find the new distances for the spaces this space connects to directly.
	 * @param start The space whose immediate neighbors need updating.
	 * @return Iterable over neighboring spaces just updated.
	 * @throws SpaceNotFoundException Error encountered when traversing space
	 * 		graph.
	 */
	private Iterable<DijkstraSpace<T>> traverse(DijkstraSpace<T> start)
			throws SpaceNotFoundException
	{
		List<DijkstraSpace<T>> updated = new ArrayList<DijkstraSpace<T>>();

		for(DijkstraConnection<T> connection : start.getConnections())
		{
			int newDist;
			DijkstraSpace<T> other;

			// Get the node across from start in this connection
			other = connection.getOther(start);
			
			// Set the new distance for the opposing node if shorter path found.
			try {
				newDist = start.getTotalDistance() + connection.getDistance();
				if(other.isDistanceInfinite()
						|| newDist < other.getTotalDistance())
				{
					other.setTotalDistance(newDist);
					other.setBacklink(connection);
					updated.add(other);
				}
			} catch (InfiniteDistanceException e) {
				throw new RuntimeException(
						"Unexpected program state. (%s)",
						e
				);
			}
		}

		return updated;
	}
}

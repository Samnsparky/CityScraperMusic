package org.sampottinger.cityscraper.connection;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Implementation of Dijkstra's algorithm that finds paths between DijkstraSpaces.
 * @author Sam Pottinger
 */
public class DijkstraPathFinder<T>
{
	/**
	 * Find a path between the given starting space and connected "ending" or "goal" spaces.
	 * @param start The space to start on.
	 * @return Iterable of connections to take to get to the end space or null if no path found.
	 */
	public DijkstraPathFinderResult<T> findPath(DijkstraSpace<T> start)
	{
		DijkstraSpace<T> currentSpace;
		Iterable<DijkstraSpace<T>> updatedNeighbors;
		PriorityQueue<DijkstraSpace<T>> q = new PriorityQueue<DijkstraSpace<T>>();
		
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
				// Reconstruct path to starting position
				try {
					DijkstraSpace<T> lastSpace = currentSpace.getBacklink().getOther(currentSpace);
					BacklinkIterable<T> path = new BacklinkIterable<T>(lastSpace);
					return new DijkstraPathFinderResult<T>(path, path.getLength());
				} catch (SpaceNotFoundException e) {
					throw new RuntimeException("Unexpected program state.");
				}
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
	 */
	private Iterable<DijkstraSpace<T>> traverse(DijkstraSpace<T> start)
	{
		List<DijkstraSpace<T>> updatedNeighbors = new ArrayList<DijkstraSpace<T>>();

		for(DijkstraConnection<T> connection : start.getConnections())
		{
			int newDistance;
			DijkstraSpace<T> other;

			// Get the node across from start in this connection
			try {
				other = connection.getOther(start);
			} catch (SpaceNotFoundException e) {
				throw new RuntimeException("Unexpected program state.");
			}
			
			// Set the new total distance for the opposing node if shorter path found.
			try {
				newDistance = start.getTotalDistance() + connection.getDistance();
				if(other.isDistanceInfinite() || newDistance < other.getTotalDistance())
				{
					other.setTotalDistance(newDistance);
					other.setBacklink(connection);
					updatedNeighbors.add(other);
				}
			} catch (InfiniteDistanceException e) {
				throw new RuntimeException("Unexpected program state.");
			}
		}

		return updatedNeighbors;
	}
}

package org.sampottinger.cityscraper.connection;

import java.util.ArrayList;
import java.util.List;

/**
 * Record of a space through which a path can be found. 
 * @author Sam Pottinger
 */
public class DijkstraSpace<E> implements Comparable<DijkstraSpace<E>>
{
	private E target;
	private boolean isEnding;
	private List<DijkstraConnection<E>> connections;
	private int totalDistance;
	private boolean isDistanceInfinite;
	private boolean visited;
	private DijkstraConnection<E> currentBacklink;

	/**
	 * Creates a new non-ending unvisited space with an infinite distance.
	 * 
	 * Creates a new space for the given object. Defaults to this not being a
	 * possible ending / goal position. Sets to be infinite distance away from
	 * starting position and unvisited.
	 * 
	 * @param newTarget The object this space represents.
	 */
	public DijkstraSpace(E newTarget)
	{
		target = newTarget;
		isEnding = false;
		connections = new ArrayList<DijkstraConnection<E>>();
		totalDistance = -1;
		isDistanceInfinite = true;
		visited = false;
		currentBacklink = null;
	}
	
	/**
	 * Creates a new unvisited space with an infinite distance.
	 * 
	 * Creates a new space for the given object. Sets to be infinite distance
	 * away from starting position and unvisited.
	 * 
	 * @param newTarget The object this space represents.
	 * @param newIsEnding Indicates if this is a possible goal position.
	 */
	public DijkstraSpace(E newTarget, boolean newIsEnding)
	{
		target = newTarget;
		isEnding = newIsEnding;
		connections = new ArrayList<DijkstraConnection<E>>();
		visited = false;
		currentBacklink = null;
		totalDistance = -1;
		isDistanceInfinite = true;
	}
	
	/**
	 * Add a new connection that starts from this space
	 * @param newConnection The connection leaving this space.
	 */
	public void addConnection(DijkstraConnection<E> newConnection)
	{
		connections.add(newConnection);
	}
	
	/**
	 * Get all the connections starting from this space.
	 * @return Iterable over connections leaving from this space.
	 */
	public Iterable<DijkstraConnection<E>> getConnections()
	{
		return connections;
	}
	
	/**
	 * Get the object that this space represents.
	 * @return Get the object that this space represents.
	 */
	public E getTarget()
	{
		return target;
	}
	
	/**
	 * Determine if this is a possible goal space / ending point.
	 * @return true if this is a "goal" space and false otherwise.
	 */
	public boolean isEnding()
	{
		return isEnding;
	}
	
	/**
	 * Set how far away this space is from the starting position.
	 * @param newTotalDistance The new total distance / value this space
	 * 		reports for itself.
	 */
	public void setTotalDistance(int newTotalDistance)
	{
		totalDistance = newTotalDistance;
		isDistanceInfinite = false;
	}
	
	/**
	 * Set this space as being infinitely far away from the starting position.
	 */
	public void setTotalDistanceInfinite()
	{
		totalDistance = -1;
		isDistanceInfinite = true;
	}
	
	/**
	 * Get the total distance of this space from the starting position.
	 * 
	 * @return The total distance of the best path currently known from this
	 * 		space to the starting position.
	 * @throws InfiniteDistanceException Thrown if distance requested but
	 * 		distance is infinite.
	 */
	public int getTotalDistance() throws InfiniteDistanceException
	{
		if(isDistanceInfinite)
			throw new InfiniteDistanceException();
		return totalDistance;
	}
	
	/**
	 * Set this space's total distance to infinity.
	 * 
	 * Indicate if the length of the shortest known path from the starting
	 * position to this space is infinite.
	 * 
	 * @return true if infinite and false otherwise.
	 */
	public boolean isDistanceInfinite()
	{
		return isDistanceInfinite;
	}
	
	/**
	 * Indicates that this node has been visited and shouldn't be visited again.
	 */
	public void setVisited()
	{
		visited = true;
	}
	
	/**
	 * Determines if this node has been traversed by the path finding algorithm.
	 * @return true if it has and false otherwise.
	 */
	public boolean wasVisited()
	{
		return visited;
	}
	
	/**
	 * Get the connection that connects this node to the shortest found path.
	 * @return The connection to the "visited" graph.
	 */
	public DijkstraConnection<E> getBacklink()
	{
		return currentBacklink;
	}
	
	/**
	 * Set the connection that connects this node to the best found path.
	 * @param newBacklink The connection to use to get to the "visited" graph.
	 */
	public void setBacklink(DijkstraConnection<E> newBacklink)
	{
		currentBacklink = newBacklink;
	}

	@Override
	public int compareTo(DijkstraSpace<E> o)
	{
		if(isDistanceInfinite())
			return 1;
		else if(o.isDistanceInfinite())
			return -1;
		
		try {
			return getTotalDistance() - o.getTotalDistance();
		} catch (InfiniteDistanceException e) {
			throw new RuntimeException("Unexpected program state.");
		}
	}

	public void reset()
	{
		totalDistance = -1;
		isDistanceInfinite = true;
		visited = false;
		currentBacklink = null;
	}
}

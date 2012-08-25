package org.sampottinger.cityscraper.connection;

/**
 * Record of a connection between two DijsktraSpaces
 * @author Sam Pottinger
 */
public class DijkstraConnection<T>
{
	private DijkstraSpace<T> start;
	private DijkstraSpace<T> end;
	private int distance;
	private boolean bidirectional;

	/**
	 * Creates a new directional connection.
	 * @param newStart The space the connection starts on.
	 * @param newEnd The space the connection ends on.
	 * @param newDistance The "length" or value of this connection.
	 */
	public DijkstraConnection(DijkstraSpace<T> newStart, DijkstraSpace<T> newEnd, int newDistance)
	{
		start = newStart;
		end = newEnd;
		distance = newDistance;
		bidirectional = false;
	}
	
	/**
	 * Creates a new connection.
	 * @param newStart The space the connection starts on.
	 * @param newEnd The space the connection ends on.
	 * @param newDistance The "length" or value of this connection.
	 * @param newBidirectional If true, this is a bi-directional connection.
	 */
	public DijkstraConnection(DijkstraSpace<T> newStart, DijkstraSpace<T> newEnd, int newDistance,
			boolean newBidirectional)
	{
		start = newStart;
		end = newEnd;
		distance = newDistance;
		bidirectional = newBidirectional;
	}
	
	/**
	 * Get the space that this connection starts on.
	 * This may not mean anything if this is bidirectional.
	 * @return The starting space of this connection.
	 */
	public DijkstraSpace<T> getStart()
	{
		return start;
	}
	
	/**
	 * Get the space that this connection ends on.
	 * This may not mean anything if this is bidirectional.
	 * @return The ending space of this connection.
	 */
	public DijkstraSpace<T> getEnd()
	{
		return end;
	}
	
	/**
	 * Indicates if this a bidirectional connection.
	 * @return true if this connection is bidirectional and false otherwise.
	 */
	public boolean isBidirectional()
	{
		return bidirectional;
	}
	
	/**
	 * Get the distance or value of this connection.
	 * @return Value / length of this connection.
	 */
	public int getDistance()
	{
		return distance;
	}
	
	/**
	 * Set the value or distance of this connection.
	 * @param newDistance Value / length of this connection.
	 */
	public void setDistance(int newDistance)
	{
		distance = newDistance;
	}
	
	/**
	 * Gets the other space in this connection.
	 * @param target The DijkstraSpace whose opposite to on this connection should be found.
	 * @return The DijkstraSpace that is not the provided DijkstraSpace.
	 * @throws SpaceNotFoundException Raised if given space is not in this connection.
	 */
	public DijkstraSpace<T> getOther(DijkstraSpace<T> space) throws SpaceNotFoundException
	{
		if(space == start)
			return end;
		else if(space == end)
			return start;
		else
			throw new SpaceNotFoundException();
	}
	
}

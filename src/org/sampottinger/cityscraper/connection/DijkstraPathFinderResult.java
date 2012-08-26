package org.sampottinger.cityscraper.connection;

/**
 * Result of trying to find a path through the DijkstraPathFinder.
 * @author Sam Pottinger
 *
 * @param <T> The class of object that the path finder's DijkstraSpaces
 * 		encapsulate.
 */
public class DijkstraPathFinderResult<T>
{
	private Iterable<DijkstraConnection<T>> path;
	private int length;
	private boolean successful;
	
	/**
	 * Create a path finder result indicating no path found.
	 */
	public DijkstraPathFinderResult()
	{
		path = null;
		length = -1;
		successful = false;
	}
	
	/**
	 * Create a new path finder result indicating a path was found.
	 * @param newPath The iterable over connections that form this path.
	 * @param newLength The length of this path.
	 */
	public DijkstraPathFinderResult(Iterable<DijkstraConnection<T>> newPath,
			int newLength)
	{
		path = newPath;
		length = newLength;
		successful = true;
	}
	
	/**
	 * Determines if path finding was successful.
	 * @return true if path found and false otherwise.
	 */
	public boolean wasSuccessful()
	{
		return successful;
	}
	
	/**
	 * Get the length of the path found.
	 * @return Length of path found or -1 if no path found.
	 */
	public int getLength()
	{
		return length;
	}
	
	/**
	 * Iterable over connections that form the path found.
	 * @return Iterable over connections that form the path found or null
	 * 		otherwise.
	 */
	public Iterable<DijkstraConnection<T>> getPath()
	{
		return path;
	}
}

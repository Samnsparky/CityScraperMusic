package org.sampottinger.cityscraper.connection;

import org.phineas.core.PhineasLocateable;

/**
 * Result of trying to find a path through the NodePathFinder.
 * @author Sam Pottinger
 */
public class NodePathFinderResult
{
	private Iterable<PhineasLocateable> path;
	private int length;
	private boolean successful;
	
	/**
	 * Create a path finder result indicating no path found.
	 */
	public NodePathFinderResult()
	{
		path = null;
		length = -1;
		successful = false;
	}
	
	/**
	 * Create a new path finder result indicating a path was found.
	 * @param newPath The iterable over locations that form this path.
	 * @param newLength The length of this path.
	 */
	public NodePathFinderResult(Iterable<PhineasLocateable> newPath,
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
	public Iterable<PhineasLocateable> getPath()
	{
		return path;
	}
}

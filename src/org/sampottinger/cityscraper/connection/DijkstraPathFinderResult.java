package org.sampottinger.cityscraper.connection;

public class DijkstraPathFinderResult<T>
{
	private Iterable<DijkstraConnection<T>> path;
	private int length;
	private boolean successful;
	
	public DijkstraPathFinderResult()
	{
		path = null;
		length = -1;
		successful = false;
	}
	
	public DijkstraPathFinderResult(Iterable<DijkstraConnection<T>> newPath, int newLength)
	{
		path = newPath;
		length = newLength;
		successful = true;
	}
	
	public boolean wasSuccessful()
	{
		return successful;
	}
	
	public int getLength()
	{
		return length;
	}
	
	public Iterable<DijkstraConnection<T>> getPath()
	{
		return path;
	}
}

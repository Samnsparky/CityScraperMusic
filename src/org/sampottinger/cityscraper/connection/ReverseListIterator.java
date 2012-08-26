package org.sampottinger.cityscraper.connection;

import java.util.Iterator;
import java.util.List;

/**
 * Iterator that traverses a list backwards.
 * @author Sam Pottiner
 * @param <T> The object type in the list.
 */
public class ReverseListIterator<T> implements Iterator<T>
{
	private List<T> target;
	private int size;
	private int i;

	/**
	 * Create a new reverse iterator.
	 * @param newTarget The list to iterate over.
	 */
	public ReverseListIterator(List<T> newTarget)
	{
		target = newTarget;
		size = target.size();
		i = size - 1;
	}
	
	/**
	 * Check that the size of the list has not changed.
	 */
	private void checkSize()
	{
		if(size != target.size())
			throw new RuntimeException("Size of underlying list changed.");
	}

	@Override
	public boolean hasNext()
	{
		checkSize();
		return i >= 0;
	}

	@Override
	public T next()
	{
		T retVal;

		checkSize();
		retVal = target.get(i);
		i--;
		
		return retVal;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}

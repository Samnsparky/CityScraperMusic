package org.sampottinger.cityscraper.nodes;

import java.util.ArrayList;
import java.util.Collection;

import org.sampottinger.cityscraper.workspace.WorkspaceElement;

/**
 * A record that has an arbitrary number of inputs and outputs.
 * @author Sam Pottinger
 */
public class SimpleNodeRecordHelper
{
	
	private Collection<WorkspaceElement> nextElements;
	private Collection<WorkspaceElement> previousElements;
	
	/**
	 * Creates a new helper with no connections.
	 */
	public SimpleNodeRecordHelper()
	{
		nextElements = new ArrayList<WorkspaceElement>();
		previousElements = new ArrayList<WorkspaceElement>();
	}
	
	/**
	 * Adds a new element that this helper's element connects to.
	 * @param nextElement The element that comes after this one.
	 */
	public void addNext(WorkspaceElement nextElement)
	{
		nextElements.add(nextElement);
	}

	/**
	 * Remove a connection from this element to a element that comes after it.
	 * @param element The element to remove the connection from.
	 */
	public void removeNext(WorkspaceElement element)
	{
		nextElements.remove(element);
	}
	
	/**
	 * Get the elements that this helper's element connects to.
	 * @return Iterable over nodes that this node connects to.
	 */
	public Iterable<WorkspaceElement> getNextElements()
	{
		return nextElements;
	}
	
	/**
	 * Add a connection from a node to this element.
	 * @param lastElement The element that connects to this element.
	 */
	public void addPrevious(WorkspaceElement lastElement)
	{
		previousElements.add(lastElement);
	}

	/**
	 * Remove a connection from a element to this element.
	 * @param element The element to remove the connection for.
	 */
	public void removePrevious(WorkspaceElement element)
	{
		previousElements.remove(element);
	}
	
	/**
	 * Get all of the elements that connect to this helper's element.
	 * @return Iterable over elements that connect to this element.
	 */
	public Iterable<WorkspaceElement> getPreviousElements()
	{
		return previousElements;
	}

}

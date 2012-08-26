package org.sampottinger.cityscraper.workspace;

import org.phineas.core.PhineasBoundable;

/**
 * Interface for elements that can be in the workspace.
 * @author Sam Pottinger
 */
public interface WorkspaceElement extends PhineasBoundable
{
	/**
	 * Make a connection to an element that comes after this element.
	 * @param nextElement The element this element connects to.
	 */
	public void addNext(WorkspaceElement nextElement);

	/**
	 * Remove connections from this element to the given element.
	 * @param element The element to remove connections to.
	 */
	public void removeNext(WorkspaceElement element);
	
	/**
	 * Get all of the elements that this element connects to.
	 * @return Iterable over elements this element connects to.
	 */
	public Iterable<WorkspaceElement> getNextElements();
	
	/**
	 * Add a connection from an element to this element.
	 * @param lastElement The element that connects to this element.
	 */
	public void addPrevious(WorkspaceElement lastElement);

	/**
	 * Remove a connection from an element to this element.
	 * @param element The element to remove connections from.
	 */
	public void removePrevious(WorkspaceElement element);
	
	/**
	 * Get all of the elements that connect to this element.
	 * @return Iterable over all of the elements that connect to this element.
	 */
	public Iterable<WorkspaceElement> getPreviousElements();
}

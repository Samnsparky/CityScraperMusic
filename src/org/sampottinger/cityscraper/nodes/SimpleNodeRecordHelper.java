package org.sampottinger.cityscraper.nodes;

import java.util.ArrayList;
import java.util.Collection;

import org.sampottinger.cityscraper.workspace.WorkspaceElement;

/**
 * A record that has an arbitrary number of inputs and outputs 
 * @author Sam Pottinger
 */
public class SimpleNodeRecordHelper
{
	
	private Collection<WorkspaceElement> nextElements;
	private Collection<WorkspaceElement> previousElements;
	
	public SimpleNodeRecordHelper()
	{
		nextElements = new ArrayList<WorkspaceElement>();
		previousElements = new ArrayList<WorkspaceElement>();
	}
	
	public void addNext(WorkspaceElement lastElement)
	{
		nextElements.add(lastElement);
	}

	public void removeNext(WorkspaceElement element)
	{
		nextElements.remove(element);
	}
	
	public Iterable<WorkspaceElement> getNextElements()
	{
		return nextElements;
	}
	
	public void addPrevious(WorkspaceElement lastElement)
	{
		previousElements.add(lastElement);
	}

	public void removePrevious(WorkspaceElement element)
	{
		previousElements.remove(element);
	}
	
	public Iterable<WorkspaceElement> getPreviousElements()
	{
		return previousElements;
	}

}

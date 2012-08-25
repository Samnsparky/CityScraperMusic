package org.sampottinger.cityscraper.workspace;

import org.phineas.core.PhineasBoundable;

public interface WorkspaceElement extends PhineasBoundable
{
	public void addNext(WorkspaceElement lastElement);

	public void removeNext(WorkspaceElement element);
	
	public Iterable<WorkspaceElement> getNextElements();
	
	public void addPrevious(WorkspaceElement lastElement);

	public void removePrevious(WorkspaceElement element);
	
	public Iterable<WorkspaceElement> getPreviousElements();
}

package org.sampottinger.cityscraper.nodes;

import java.io.IOException;

import org.phineas.contrib.PhineasSprite;
import org.sampottinger.cityscraper.workspace.WorkspaceElement;

/**
 * Base class for simple graphical nodes 
 * @author Sam Pottinger
 */
public abstract class SimpleGraphicalNode extends PhineasSprite
		implements CityScraperNode
{
	
	private SimpleNodeRecordHelper recordHelper;

	/**
	 * Creates a new node.
	 * @param newX The x position of the new node.
	 * @param newY The y position of the new node.
	 * @param newImageLoc The location of the image to use for the node.
	 * @throws IOException Thrown if image can not be loaded.
	 */
	public SimpleGraphicalNode(int newX, int newY, String newImageLoc)
			throws IOException 
	{
		super(newX, newY, newImageLoc);
		recordHelper = new SimpleNodeRecordHelper();
	}
	

	@Override
	public void addNext(WorkspaceElement lastElement)
	{
		recordHelper.addNext(lastElement);
	}

	@Override
	public void removeNext(WorkspaceElement element)
	{
		recordHelper.removeNext(element);
	}

	@Override
	public Iterable<WorkspaceElement> getNextElements()
	{
		return recordHelper.getNextElements();
	}
	
	@Override
	public void addPrevious(WorkspaceElement element)
	{
		recordHelper.addPrevious(element);
	}

	@Override
	public void removePrevious(WorkspaceElement element)
	{
		recordHelper.removePrevious(element);
	}

	@Override
	public Iterable<WorkspaceElement> getPreviousElements()
	{
		return recordHelper.getPreviousElements();
	}
}

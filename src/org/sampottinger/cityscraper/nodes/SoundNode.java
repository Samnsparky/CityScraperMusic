package org.sampottinger.cityscraper.nodes;

import java.awt.Graphics2D;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasDrawable;
import org.sampottinger.cityscraper.workspace.WorkspaceElement;

/**
 * Decorator around a boundable drawable that adds sound capabilities to it
 * @author Sam Pottinger
 */
public class SoundNode<T extends PhineasBoundable & PhineasDrawable> implements CityScraperNode
{

	private SimpleNodeRecordHelper recordHelper = new SimpleNodeRecordHelper();
	
	
	private T innards;
	
	/**
	 * Creates a sound node out of the given boundable drawable
	 * @param newInner The boundable drawable to wrap
	 */
	public SoundNode(T newInner)
	{
		innards = newInner;
	}

	@Override
	public void draw(Graphics2D target)
	{
		innards.draw(target);
	}

	@Override
	public int getDepth()
	{
		return innards.getDepth();
	}

	@Override
	public int getX()
	{
		return innards.getX();
	}

	@Override
	public int getY()
	{
		return innards.getY();
	}

	@Override
	public int getWidth()
	{
		return innards.getWidth();
	}

	@Override
	public int getHeight()
	{
		return innards.getHeight();
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

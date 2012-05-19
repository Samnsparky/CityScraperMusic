package org.sampottinger.cityscraper.nodes;

import java.awt.Graphics2D;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasDrawable;

/**
 * Decorator around a boundable drawable that adds sound capabilities to it
 * @author Sam Pottinger
 */
public class SoundNode<T extends PhineasBoundable & PhineasDrawable> implements CityScraperNode
{
	
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

}

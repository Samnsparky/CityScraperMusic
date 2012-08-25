package org.sampottinger.cityscraper;

import org.phineas.core.PhineasLocateable;

/**
 * Simple x and y coordinate pair
 * @author Sam Pottinger
 */
public class SimpleCoordinatePair implements PhineasLocateable
{
	private int x;
	private int y;
	
	public SimpleCoordinatePair(int newX, int newY)
	{
		x = newX;
		y = newY;
	}

	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}
	
	@Override
	public boolean equals(Object other)
	{
		PhineasLocateable locOther = (PhineasLocateable)other;
		return getX() == locOther.getX() && getY() == locOther.getY();
	}

}

package org.sampottinger.cityscraper.gui;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasHoverListener;
import org.phineas.core.PhineasScrollWheelListener;

/**
 * Region that responds to scroll events to operate on slider sources
 * @author Sam Pottinger
 */
public class ScrollResponsiveRegion implements PhineasScrollWheelListener, PhineasBoundable, PhineasHoverListener
{
	
	private SliderSource target;
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private boolean active;
	
	/**
	 * Creates a new region that will operate on the given target
	 * @param newTarget The target to update values in as responding to
	 * scroll events on this region 
	 * @param newX The x position of the upper left corner of this region
	 * @param newY The y position of the upper left corner of this region
	 * @param newWidth The number of pixels wide this region is
	 * @param newHeight The number of pixels high this region is
	 */
	public ScrollResponsiveRegion(SliderSource newTarget, int newX, int newY, int newWidth, int newHeight)
	{
		target = newTarget;
		x = newX;
		y = newY;
		width = newWidth;
		height = newHeight;
		active = false;
	}

	@Override
	public void onWheelMove(int notches)
	{
		float newValue;
		
		if(active)
		{
			newValue = target.getValue() + notches;
			target.setValue(newValue);
		}
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
	public int getWidth()
	{
		return width;
	}

	@Override
	public int getHeight()
	{
		return height;
	}

	@Override
	public void onEnter()
	{
		active = true;
	}

	@Override
	public void onLeave()
	{
		active = false;
	}
}

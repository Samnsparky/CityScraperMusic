package org.sampottinger.cityscraper.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import org.phineas.contrib.PhineasRectangle;
import org.phineas.core.PhineasDrawable;
import org.phineas.core.PhineasClickListener;
import org.phineas.core.PhineasGlobalClickListener;
import org.phineas.core.PhineasGlobalMouseMovementListener;
import org.phineas.core.PhineasHoverListener;
import org.phineas.core.PhineasStepListener;
import org.sampottinger.cityscraper.BoundableInfoSelector.Axis;

/**
 * Minimalistic GUI slider
 * @author Sam Pottinger
 */
public class SlenderSlider implements PhineasClickListener,
		PhineasGlobalClickListener, PhineasGlobalMouseMovementListener,
		PhineasHoverListener, PhineasStepListener, PhineasDrawable
{	
	private static final Color INACTIVE_COLOR = new Color(134, 130, 130);
	private static final Color ACTIVE_COLOR = new Color(179, 171, 171);
	private static final Color HOVER_COLOR = new Color(217, 210, 210);
	private static final int SMALL_DIMENSION_SIZE = 5;
	private static final int STATIC_AXIS_OFFSET = -2;
	private static final int CENTER_LINE_SIZE = 1;
	
	private SliderSource target;
	private int x;
	private int y;
	private int boxWidth;
	private int boxHeight;
	private int travel;
	private int maxBoxPos;
	private int sliderBoxSize;
	private int depth;
	private Axis direction;
	private boolean active;
	private float pixelConversionValue;
	private PhineasRectangle box;
	private PhineasRectangle centerLine;
	
	/**
	 * Create a new slider.
	 * 
	 * @param target The source to have the slider operate on.
	 * @param x The x position of the left of the slider's range.
	 * @param y The y position of the top of the slider's range.
	 * @param newSliderBoxSize The size of the box on the slider.
	 * @param travel How far the slider can move (in pixels).
	 * @param direction The axis on which this slider travels.
	 * @param showCenterLine If true, a thin line outlining the range of the
	 * 		slider will be shown.
	 * @param newDepth The level at which this slider should be drawn
	 * 		(affects drawing order).
	 */
	public SlenderSlider(SliderSource newTarget, int newX, int newY,
			int newSliderBoxSize, int newTravel, Axis newDirection,
			boolean showCenterLine, int newDepth)
	{
		
		// Save simple attributes
		target = newTarget;
		x = newX;
		y = newY;
		travel = newTravel - newSliderBoxSize;
		direction = newDirection;
		active = false;
		sliderBoxSize = newSliderBoxSize;
		depth = newDepth;
		
		// Determine slider box sizes
		switch(direction)
		{
		case X_AXIS:
			boxWidth = sliderBoxSize;
			boxHeight = SMALL_DIMENSION_SIZE;
			break;
		case Y_AXIS:
			boxWidth = SMALL_DIMENSION_SIZE;
			boxHeight = sliderBoxSize;
			break;
		}
		
		// Attach graphical components
		switch(direction)
		{
		case X_AXIS:
			box = new PhineasRectangle(
					x,
					y + STATIC_AXIS_OFFSET,
					boxWidth,
					boxHeight,
					INACTIVE_COLOR
			);
	
			if(showCenterLine)
				centerLine = new PhineasRectangle(
						x,
						y,
						travel + boxWidth,
						CENTER_LINE_SIZE,
						INACTIVE_COLOR
				);
			break;

		case Y_AXIS:
			box = new PhineasRectangle(
					x + STATIC_AXIS_OFFSET,
					y,
					boxWidth,
					boxHeight,
					INACTIVE_COLOR
			);

			if(showCenterLine)
				centerLine = new PhineasRectangle(
						x,
						y,
						CENTER_LINE_SIZE,
						travel + boxHeight,
						INACTIVE_COLOR
				);
			break;
		}
		
		refreshPos();
	}
	
	/**
	 * Update the position of the box on the slider.
	 */
	public void refreshPos()
	{
		int numPixelsBoxDisplaced;
		int sliderBoxX;
		int sliderBoxY;
		
		pixelConversionValue = travel /
				(target.getMaxValue() - target.getMinValue());
		numPixelsBoxDisplaced = (int)((target.getValue() - 
				target.getMinValue()) * pixelConversionValue);
		
		// Determine dimensions of slider box and set position
		switch(direction)
		{
		case X_AXIS:
			sliderBoxX = x + numPixelsBoxDisplaced;
			sliderBoxY = y;
			maxBoxPos = travel + x;
			box.setX(sliderBoxX);
			break;
		case Y_AXIS:
			sliderBoxX = x; 
			sliderBoxY = y + numPixelsBoxDisplaced;
			maxBoxPos = travel + y;
			box.setY(sliderBoxY);
			break;
		}
	}

	@Override
	public void onMouseMove(int newX, int newY)
	{
		int sliderBoxX = box.getX();
		int sliderBoxY = box.getY();
		int sliderBoxCenter;
		int boxDisplacement=0;
		int boxInitialValue=0;
		int newPosVal=0;
		float newVal;
		
		// Ignore if the slider has not been selected
		if(!active)
			return;
		
		// Calculate position of box
		// TODO: Clean this up
		switch(direction)
		{
		case X_AXIS:
			sliderBoxCenter = sliderBoxX + sliderBoxSize / 2;
			boxDisplacement = newX - sliderBoxCenter;
			boxInitialValue = x;
			break;
		case Y_AXIS:
			sliderBoxCenter = sliderBoxY + sliderBoxSize / 2;
			boxDisplacement = newY - sliderBoxCenter;
			boxInitialValue = y;
			break;
		}
		
		switch(direction)
		{
		case X_AXIS:
			newPosVal = sliderBoxX + boxDisplacement;
			if(newPosVal < x)
				newPosVal = x;
			if(newPosVal > maxBoxPos)
				newPosVal = maxBoxPos;
			box.setX(newPosVal);
			break;
		case Y_AXIS:
			newPosVal = sliderBoxY + boxDisplacement;
			if(newPosVal < y)
				newPosVal = y;
			if(newPosVal > maxBoxPos)
				newPosVal = maxBoxPos;
			box.setY(newPosVal);
			break;
		}
		
		// Make appropriate for the target
		newVal = ((newPosVal - boxInitialValue) / pixelConversionValue);
		
		target.setValue(newVal);
	}

	@Override
	public void onGlobalLeftDown(int absX, int absY)
	{}

	@Override
	public void onGlobalLeftRelease(int absX, int absY)
	{
		if(active)
		{
			active = false;
			box.setColor(INACTIVE_COLOR);
		}
	}

	@Override
	public void onLeftDown(int relativeX, int relativeY)
	{
		active = true;
		box.setColor(ACTIVE_COLOR);
	}

	@Override
	public void onLeftRelease(int relativeX, int relativeY)
	{}

	@Override
	public int getX()
	{
		return box.getX();
	}

	@Override
	public int getY()
	{
		return box.getY();
	}

	@Override
	public int getWidth()
	{
		return boxWidth;
	}

	@Override
	public int getHeight()
	{
		return boxHeight;
	}

	@Override
	public void onEnter()
	{
		if(!active)
			box.setColor(HOVER_COLOR);
	}

	@Override
	public void onLeave()
	{
		if(!active)
			box.setColor(INACTIVE_COLOR);
	}

	@Override
	public void onStep(long milliseconds)
	{
		refreshPos();
	}

	@Override
	public void draw(Graphics2D target)
	{
		if(centerLine != null)
			centerLine.draw(target);
		
		box.draw(target);
	}

	@Override
	public int getDepth()
	{
		return depth;
	}
}

package org.sampottinger.cityscraper.init;

import org.sampottinger.cityscraper.BoundableInfoSelector.Axis;
import org.sampottinger.cityscraper.gui.SlenderSlider;
import org.sampottinger.cityscraper.gui.SliderSource;

/**
 * Builder for SlenderSliders with proportionally sized slider boxes.
 * @author Sam Pottinger
 */
public class FittedSlenderSliderBuilder
{
	private static final Axis DEFAULT_DIRECTION = Axis.Y_AXIS;
	private static final boolean DEFAULT_CENTER_LINE_VISIBILITY = true;
	private static final int DEFAULT_DEPTH = SlenderSlider.DEFAULT_DEPTH;
	private static final int MINIMUM_BOX_SIZE = 3;
	
	private int x;
	private int y;
	private int travel;
	private SliderSource targetSource;
	private Axis direction;
	private boolean showCenterLine;
	private int depth;
	private int amountActive;
	
	/**
	 * Create a builder for a slender slider with default center line showing,
	 * vertical direction, and default depth as defined by the Drawable
	 * interface.
	 * @param newTargetSource The source the slider will target.
	 * @param newX The x position for the slider.
	 * @param newY The y position for the slider.
	 * @param newTravel The new travel distance (in pixels) that the slider
	 * 		spans.
	 * @param newAmountActive The source's range that is visible or otherwise
	 * 		"active at any given moment. This could be the number of pixels a
	 * 		scrollable area shows to the user at any given moment.
	 */
	public FittedSlenderSliderBuilder(SliderSource newTargetSource, int newX,
			int newY, int newTravel, int newAmountActive)
	{
		// Specified properties
		targetSource = newTargetSource;
		x = newX;
		y = newY;
		travel = newTravel;
		amountActive = newAmountActive;
		
		// Defaults
		direction = DEFAULT_DIRECTION;
		showCenterLine = DEFAULT_CENTER_LINE_VISIBILITY;
		depth = DEFAULT_DEPTH;
	}
	
	/**
	 * Sets the direction that this builder should use for the slender sliders
	 * it will create.
	 * @param newDirection The new direction this builder should use during the
	 *                     construction of sliders.
	 */
	public void setDirection(Axis newDirection)
	{
		direction = newDirection;
	}
	
	/**
	 * Indicate if the center line for the slider should be shown.
	 * @param visible Indicate if the center line should be visible.
	 */
	public void setCenterLineVisibilit(boolean visible)
	{
		showCenterLine = visible;
	}
	
	/**
	 * Indicate the drawing depth of the sliders that this builder will create.
	 * @param newDepth The drawing depth to use for this builder's creations.
	 */
	public void setDepth(int newDepth)
	{
		depth = newDepth;
	}
	
	/**
	 * Create a new SlenderSlider for the previous specified parameters.
	 * @return New slider that fits the specifications previously given to the
	 * 		builder.
	 */
	public SlenderSlider createSlider()
	{
		float proportionalBoxSize;
		int actualBoxSize;
		SlenderSlider returnValue;
		
		// Calculate box size
		proportionalBoxSize = amountActive / 
				(targetSource.getMaxValue() - targetSource.getMinValue())
				* travel;
		actualBoxSize = proportionalBoxSize < MINIMUM_BOX_SIZE ? 
				MINIMUM_BOX_SIZE : (int)proportionalBoxSize;
		
		// Create slider
		returnValue = new SlenderSlider(
				targetSource,
				x,
				y,
				actualBoxSize,
				travel,
				direction,
				showCenterLine,
				depth
		);
		
		return returnValue;
	}
}

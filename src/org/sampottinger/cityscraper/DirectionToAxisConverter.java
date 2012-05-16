package org.sampottinger.cityscraper;

import org.sampottinger.cityscraper.BoundableInfoSelector.Axis;
import org.sampottinger.cityscraper.gui.SlenderSlider.SliderDirection;

// TODO: This is a pretty messy object, these 2 enums should be merged

/**
 * Converter that will change direction to axis and visa - versa
 * @author Sam Pottinger
 */
public class DirectionToAxisConverter
{
	private static DirectionToAxisConverter instance = null;
	
	/**
	 * Gets a shared instance of this factory
	 * @return Shared instance of this singleton
	 */
	public static DirectionToAxisConverter getInstance()
	{
		if(instance == null)
			instance = new DirectionToAxisConverter();
		return instance;
	}
	
	/**
	 * Finds the corresponding axis for the given direction
	 * (HORIZONTAL -> X_AXIS, VERTICAL -> Y_AXIS)
	 * @param direction The direction to convert
	 * @return The corresponding axis
	 */
	public Axis directionToAxis(SliderDirection direction)
	{
		if(direction == SliderDirection.HORIZONTAL)
			return Axis.X_AXIS;
		else
			return Axis.Y_AXIS;
	}
	
	/**
	 * Finds the corresponding axis for the given direction
	 * (HORIZONTAL -> X_AXIS, VERTICAL -> Y_AXIS)
	 * @param direction The direction to convert
	 * @return The corresponding direction
	 */
	public SliderDirection axisToDirection(Axis axis)
	{
		if(axis == Axis.X_AXIS)
			return SliderDirection.HORIZONTAL;
		else
			return SliderDirection.VERTICAL;
	}
}

package org.sampottinger.cityscraper.gui;

/**
 * Helper decorator that makes values valid and discrete for a slider source
 * as they are set (according to its target's increment, min, and max value)
 * @author Sam Pottinger
 */
public class SliderSourceValueScrubber implements SliderSource
{

	private SliderSource target;
	
	/**
	 * Creates a new decorator around the given target
	 * @param newTarget The target slider source to decorate
	 */
	public SliderSourceValueScrubber(SliderSource newTarget)
	{
		target = newTarget;
	}
	
	@Override
	public float getMinValue()
	{
		return target.getMinValue();
	}

	@Override
	public float getMaxValue()
	{
		return target.getMaxValue();
	}

	@Override
	public void setValue(float newValue)
	{
		float diffBetweenDiscreteStep;
		
		if(newValue > getMaxValue())
			newValue = getMaxValue();
		else if(newValue < getMinValue())
			newValue = getMinValue();
		else
		{
			diffBetweenDiscreteStep = newValue % getIncrement();
			newValue -= diffBetweenDiscreteStep;
		}
		
		target.setValue(newValue);
	}

	@Override
	public float getValue()
	{
		return target.getValue();
	}

	@Override
	public float getIncrement()
	{
		return target.getIncrement();
	}
	
}

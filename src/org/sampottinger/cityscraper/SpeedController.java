package org.sampottinger.cityscraper;

public class SpeedController implements TufteSliderSource
{
	private int speed;
	private int min;
	private int max;
	
	public SpeedController(int newMinSpeed, int newMaxSpeed)
	{
		speed = (newMinSpeed + newMaxSpeed) / 2;
		min = newMinSpeed;
		max = newMaxSpeed;
	}

	@Override
	public float getMinValue()
	{
		return min;
	}

	@Override
	public float getMaxValue()
	{
		return max;
	}

	@Override
	public void setValue(float newValue)
	{
		speed = (int)newValue;
	}

	@Override
	public float getValue()
	{
		return speed;
	}

	@Override
	public float getIncrement()
	{
		return 20;
	}

}

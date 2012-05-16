package org.sampottinger.cityscraper.gui;

/**
 * Some data source which can be manipulated by the TufteSlider widget
 * @author Sam Pottinger
 */
public interface SliderSource
{
	/**
	 * Get the minimum value that this slider source will allow
	 * @return The smallest value that this source's value can be
	 */
	public float getMinValue();
	
	/**
	 * Get the maximum value that this slider source will allow
	 * @return The largest value that this source's value can be
	 */
	public float getMaxValue();
	
	/**
	 * Sets this source's value to newValue, making sure it is in range
	 * and otherwise a valid value to use or sets it to a reasonably
	 * close valid value
	 * @param newValue The newValue to have this source use
	 */
	public void setValue(float newValue);
	
	/**
	 * Get the value that this source is currently set to
	 * @return This source's current value
	 */
	public float getValue();
	
	/**
	 * Get at what discrete steps valid values can be set to
	 * @return The numerical step between valid values
	 */
	public float getIncrement();
}

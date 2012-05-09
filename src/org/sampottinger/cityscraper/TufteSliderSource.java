package org.sampottinger.cityscraper;

/**
 * Some data source which can be manipulated by the TufteSlider widget
 * @author Sam Pottinger
 */
public interface TufteSliderSource
{
	public float getMinValue();
	
	public float getMaxValue();
	
	public void setValue(float newValue);
	
	public float getValue();
	
	public float getIncrement();
}

package org.sampottinger.cityscraper.gui;

/**
 * Interface for potentially stateful strategies that define how a ScollingCollection
 * will define its min, max, and increment values
 * @author Sam Pottinger
 */
public interface ScrollingCollectionBoundsStrategy
{
	public enum ScrollingCollectionBoundsStrategyID { TIGHT };
	
	/**
	 * Get the largest valid value that the scrolling collection should report as a SliderSource
	 * @return Maximum value that should be fed to entities treating a scrolling collection as
	 * 		   a slider source
	 */
	public float getMax();
	
	/**
	 * Get the smallest valid value that the scrolling collection should report as a SliderSource
	 * @return Minimum value that should be fed to entities treating a scrolling collection as
	 * 		   a slider source
	 */
	public float getMin();
	
}

package org.sampottinger.cityscraper.gui;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasPlaceable;

/**
 * Interface for element movement behavior in scrolling collection.
 * 
 * Interface defining strategy for how elements in a scrolling collection should
 * respond to changes in values assigned to the collection when treated like a
 * SliderSource.
 * 
 * @author Sam Pottinger
 */
public interface ScrollingCollectionMovementStrategy
{
	public enum ScrollingCollectionMovementStrategyID { DIRECT };
	
	/**
	 * Method to call when an element is being updated
	 * @param deltaValue The amount the value of the slider source changed by
	 */
	public <T extends PhineasPlaceable & PhineasBoundable> void updateElement(
			float deltaValue, T element);
}

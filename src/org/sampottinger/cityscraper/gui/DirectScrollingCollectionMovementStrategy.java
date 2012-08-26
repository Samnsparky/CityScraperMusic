package org.sampottinger.cityscraper.gui;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasPlaceable;
import org.sampottinger.cityscraper.BoundableInfoSelector.Axis;

/**
 * Scroll strategy that has a 1 to 1 value to pixel movement ratio.
 * @author Sam Pottinger
 */
public class DirectScrollingCollectionMovementStrategy
		implements ScrollingCollectionMovementStrategy
{
	private Axis targetSliderDirection;
	
	/**
	 * Creates a new LinearScrollingCollectionMovementStrategy
	 * @param slidingDirection The axis in which the elements should slide
	 */
	public DirectScrollingCollectionMovementStrategy(Axis slidingDirection)
	{
		targetSliderDirection = slidingDirection;
	}

	@Override
	public <T extends PhineasPlaceable & PhineasBoundable> void updateElement(
			float deltaValue, T element) {
		switch(targetSliderDirection)
		{
		case X_AXIS:
			element.setX((int) (element.getX() - deltaValue));
			break;
		case Y_AXIS:
			element.setY((int) (element.getY() - deltaValue));
			break;
		}
	}
}

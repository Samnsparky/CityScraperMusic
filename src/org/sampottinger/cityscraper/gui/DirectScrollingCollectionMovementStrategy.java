package org.sampottinger.cityscraper.gui;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasPlaceable;
import org.sampottinger.cityscraper.gui.SlenderSlider.SliderDirection;

/**
 * ScrollingCollectionMovementStrategy that has a 1 to 1 value to pixel movement ratio
 * @author Sam Pottinger
 */
public class DirectScrollingCollectionMovementStrategy implements ScrollingCollectionMovementStrategy
{
	private SliderDirection targetSliderDirection;
	
	/**
	 * Creates a new LinearScrollingCollectionMovementStrategy
	 * @param slidingDirection The direction in which the elements should slide
	 */
	public DirectScrollingCollectionMovementStrategy(SliderDirection slidingDirection)
	{
		targetSliderDirection = slidingDirection;
	}

	@Override
	public <T extends PhineasPlaceable & PhineasBoundable> void updateElement(
			float deltaValue, T element) {
		switch(targetSliderDirection)
		{
		case HORIZONTAL:
			element.setX((int) (element.getX() - deltaValue));
			break;
		case VERTICAL:
			element.setY((int) (element.getY() - deltaValue));
			break;
		}
	}
}

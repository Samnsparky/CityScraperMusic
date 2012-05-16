package org.sampottinger.cityscraper.gui;

import org.phineas.core.PhineasBoundable;
import org.sampottinger.cityscraper.BoundableInfoSelector;
import org.sampottinger.cityscraper.BoundableInfoSelector.Axis;
import org.sampottinger.cityscraper.DirectionToAxisConverter;
import org.sampottinger.cityscraper.gui.SlenderSlider.SliderDirection;

/**
 * Strategy that will keep the scrolling region as tight as possible
 * @author Sam Pottinger
 */
public class TightScrollingCollectionBoundsStrategy implements ScrollingCollectionBoundsStrategy
{	
	private float min;
	private float max;
	
	/**
	 * Create a new bounds strategy
	 * @param boundsDirection The direction in which the bounds will extend (ex 
	 *                        if the slider acting as a scrollbar is horizontal,
	 *                        this should be HORIZONTAL to make a bounding box
	 *                        appropriate to the elements scrolling horizontally)
	 * @param targetElements The elements that are contained in the scrolling region
	 * @param startingValue The value that the scroll region should start at
	 * @param newIncrement How much the actual scrolling region should move in a single
	 * @param amountActive The number of pixels that are visibile at any given moment in time
	 */
	public TightScrollingCollectionBoundsStrategy(SliderDirection boundsDirection, 
			Iterable<PhineasBoundable> targetElements, int amountActive)
	{
		Axis targetAxis;
		PhineasBoundable firstBoundable;
		BoundableInfoSelector infoSelectorDecorator;
		
		// Determine starting axis
		targetAxis = DirectionToAxisConverter.getInstance().directionToAxis(boundsDirection);
		
		// Get first boundable
		firstBoundable = targetElements.iterator().next();
		
		// Prime min and max values
		infoSelectorDecorator = new BoundableInfoSelector(targetAxis, firstBoundable);
		min = infoSelectorDecorator.getLowerBound();
		max = infoSelectorDecorator.getUpperBound();
		
		// Find actual min and max
		for(PhineasBoundable element : targetElements)
		{
			infoSelectorDecorator = new BoundableInfoSelector(targetAxis, element);
			min = Math.min(infoSelectorDecorator.getLowerBound(), min);
			max = Math.max(infoSelectorDecorator.getUpperBound(), min);
		}
		
		// Tighten
		max -= amountActive;
	}

	@Override
	public float getMax()
	{
		return max;
	}

	@Override
	public float getMin()
	{
		return min;
	}

}

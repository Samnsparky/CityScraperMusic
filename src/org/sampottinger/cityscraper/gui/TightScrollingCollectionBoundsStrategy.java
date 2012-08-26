package org.sampottinger.cityscraper.gui;

import org.phineas.core.PhineasBoundable;
import org.sampottinger.cityscraper.BoundableInfoSelector;
import org.sampottinger.cityscraper.BoundableInfoSelector.Axis;

/**
 * Strategy that will keep the scrolling region as tight as possible.
 * @author Sam Pottinger
 */
public class TightScrollingCollectionBoundsStrategy implements ScrollingCollectionBoundsStrategy
{	
	private float min;
	private float max;
	
	/**
	 * Create a new bounds strategy.
	 * @param targetAxis The axis in which the bounds will extend (ex 
	 * 		if the slider acting as a scrollbar is horizontal, this should be
	 * 		HORIZONTAL to make a bounding box appropriate to the elements
	 * 		scrolling horizontally).
	 * @param targetElements The elements that are contained in the scrolling
	 * 		region.
	 * @param startingValue The value that the scroll region should start at.
	 * @param newIncrement How much the actual scrolling region should move in
	 * 		a single step.
	 * @param amountActive The number of pixels that are visible at any given
	 * 		moment in time.
	 */
	public TightScrollingCollectionBoundsStrategy(Axis targetAxis, 
			Iterable<PhineasBoundable> targetElements, int amountActive)
	{
		PhineasBoundable firstBoundable;
		BoundableInfoSelector infoSelectorDecorator;
		
		// Get first boundable
		firstBoundable = targetElements.iterator().next();
		
		// Prime min and max values
		infoSelectorDecorator = new BoundableInfoSelector(targetAxis,
				firstBoundable);
		min = infoSelectorDecorator.getLowerBound();
		max = infoSelectorDecorator.getUpperBound();
		
		// Find actual min and max
		for(PhineasBoundable element : targetElements)
		{
			infoSelectorDecorator = new BoundableInfoSelector(targetAxis,
					element);
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

package org.sampottinger.cityscraper.gui;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasPlaceable;

/**
 * Collection of placeable elements that will move in response to scroll events
 * @author Sam Pottinger
 * @param <T> The type this collection will be targeting
 */
public class ScrollingCollection<T extends PhineasPlaceable & PhineasBoundable> implements SliderSource
{
	
	private final ScrollingCollectionBoundsStrategy boundsStrategy;
	private final ScrollingCollectionMovementStrategy movementStrategy;
	private final Iterable<T> elements;
	
	private float value;
	private float incrementValue;
	
	/**
	 * Creates a new scrolling collection with the given behavior strategies
	 * @param targetBoundsStrategy The strategy to use for determining bounds
	 * @param targetMovementStrategy The strategy to use for moving entities
	 * @param targetEntities The entities to target
	 * @param startingValue The value to report before it is set externally
	 */
	public ScrollingCollection(
			ScrollingCollectionBoundsStrategy targetBoundsStrategy,
			ScrollingCollectionMovementStrategy targetMovementStrategy,
			Iterable<T> targetEntities, int startingValue, int newIncrementValue)
	{
		boundsStrategy = targetBoundsStrategy;
		movementStrategy = targetMovementStrategy;
		elements = targetEntities;
		value = startingValue;
		incrementValue = newIncrementValue;
	}

	@Override
	public float getMinValue()
	{
		return boundsStrategy.getMin();
	}

	@Override
	public float getMaxValue()
	{
		return boundsStrategy.getMax();
	}

	@Override
	public void setValue(float newValue)
	{
		float delta = newValue - value;
		
		for(T target : elements)
			movementStrategy.updateElement(delta, target);
		
		value = newValue;
	}

	@Override
	public float getValue()
	{
		return value;
	}

	@Override
	public float getIncrement()
	{
		return incrementValue;
	}
}

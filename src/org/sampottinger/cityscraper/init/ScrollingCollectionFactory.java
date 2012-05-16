package org.sampottinger.cityscraper.init;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasPlaceable;
import org.sampottinger.cityscraper.gui.DirectScrollingCollectionMovementStrategy;
import org.sampottinger.cityscraper.gui.ScrollingCollection;
import org.sampottinger.cityscraper.gui.ScrollingCollectionBoundsStrategy;
import org.sampottinger.cityscraper.gui.ScrollingCollectionBoundsStrategy.ScrollingCollectionBoundsStrategyID;
import org.sampottinger.cityscraper.gui.ScrollingCollectionMovementStrategy;
import org.sampottinger.cityscraper.gui.ScrollingCollectionMovementStrategy.ScrollingCollectionMovementStrategyID;
import org.sampottinger.cityscraper.gui.SlenderSlider.SliderDirection;
import org.sampottinger.cityscraper.gui.TightScrollingCollectionBoundsStrategy;

/**
 * Factory that produces collections of scrolling elements
 * @author Sam Pottinger
 */
public class ScrollingCollectionFactory
{
	private static final int DEFAULT_STARTING_VALUE = 0;
	private static final int DEFAULT_INCREMENT_VALUE = 1;
	
	private static ScrollingCollectionFactory instance = null;
	
	/**
	 * Gets a shared instance of this factory
	 * @return Shared instance of this singleton
	 */
	public static ScrollingCollectionFactory getInstance()
	{
		if(instance == null)
			instance = new ScrollingCollectionFactory();
		return instance;
	}
	
	private ScrollingCollectionFactory() {}
	
	/**
	 * Creates a new ScrollingCollection
	 * @param boundsStrategyID The id of the strategy to use for the bounds calculation
	 * @param movementStrategyID The id of the strategy to use for moving entities
	 * @param targetEntities The entities to operate on
	 * @param direction The direction in which the entities will travel
	 * @param amountActive The number of pixels of this collection that will be visible
	 *                     at any given moment in time
	 * @return New scrolling collection that fits the given specifications
	 */
	public <T extends PhineasPlaceable & PhineasBoundable> ScrollingCollection<T> createCollection(
			ScrollingCollectionBoundsStrategyID boundsStrategyID,
			ScrollingCollectionMovementStrategyID movementStrategyID, 
			Iterable<T> targetEntities, SliderDirection direction, int amountActive)
	{
		return createCollection(boundsStrategyID, movementStrategyID, targetEntities, direction, amountActive,
				DEFAULT_STARTING_VALUE, DEFAULT_INCREMENT_VALUE);
	}
	
	/**
	 * Creates a new ScrollingCollection
	 * @param boundsStrategyID The id of the strategy to use for the bounds calculation
	 * @param movementStrategyID The id of the strategy to use for moving entities
	 * @param targetEntities The entities to operate on
	 * @param direction The direction in which the entities will travel
	 * @param amountActive The number of pixels of this collection that will be visible
	 *                     at any given moment in time
	 * @param startingValue The starting value to use slider source
	 * @param incrementValue The minimum step the resulting scrolling collection can take
	 * @return New scrolling collection that fits the given specifications
	 */
	@SuppressWarnings("unchecked")
	public <T extends PhineasPlaceable & PhineasBoundable> ScrollingCollection<T> createCollection(
			ScrollingCollectionBoundsStrategyID boundsStrategyID,
			ScrollingCollectionMovementStrategyID movementStrategyID, 
			Iterable<T> targetEntities, SliderDirection direction,
			int amountActive, int startingValue, int incrementValue)
	{
		ScrollingCollection<T> returnVal;
		ScrollingCollectionBoundsStrategy boundsStrategy;
		ScrollingCollectionMovementStrategy movementStrategy;
		
		// Resolve bounds strategy
		switch(boundsStrategyID)
		{
		case TIGHT:
			boundsStrategy = new TightScrollingCollectionBoundsStrategy(direction, 
					(Iterable<PhineasBoundable>) targetEntities, amountActive);
			break;
		default:
			throw new RuntimeException("Invalid bounds strategy id provided.");
		}
		
		// Resolve movement strategy
		switch(movementStrategyID)
		{
		case DIRECT:
			movementStrategy = new DirectScrollingCollectionMovementStrategy(direction);
			break;
		default:
			throw new RuntimeException("Invalid movement strategy id provided.");
		}
		
		returnVal = new ScrollingCollection<T>(boundsStrategy, movementStrategy, targetEntities, startingValue, incrementValue);
		
		return returnVal;
	}
}

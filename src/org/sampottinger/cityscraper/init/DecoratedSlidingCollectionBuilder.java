package org.sampottinger.cityscraper.init;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasDrawable;
import org.phineas.core.PhineasPlaceable;
import org.sampottinger.cityscraper.gui.DecoratedSlidingCollection;
import org.sampottinger.cityscraper.gui.ScrollResponsiveRegion;
import org.sampottinger.cityscraper.gui.ScrollingCollection;
import org.sampottinger.cityscraper.gui.ScrollingCollectionBoundsStrategy.ScrollingCollectionBoundsStrategyID;
import org.sampottinger.cityscraper.gui.ScrollingCollectionMovementStrategy.ScrollingCollectionMovementStrategyID;
import org.sampottinger.cityscraper.gui.SlenderSlider;
import org.sampottinger.cityscraper.gui.SlenderSlider.SliderDirection;
import org.sampottinger.cityscraper.gui.SliderSourceValueScrubber;

/**
 * Builder pattern for creating NodeContent instances
 * @author Sam Pottinger
 */
public class DecoratedSlidingCollectionBuilder<T extends PhineasPlaceable & PhineasBoundable>
{
	private static final ScrollingCollectionBoundsStrategyID DEFAULT_BOUNDS_STRATEGY_ID = 
			ScrollingCollectionBoundsStrategyID.TIGHT; 
	private static final ScrollingCollectionMovementStrategyID DEFAULT_MOVEMENT_STRATEGY_ID =
			ScrollingCollectionMovementStrategyID.DIRECT;
	private static final int DEFAULT_SCROLL_PADDING = 4;
	
	private ScrollingCollectionBoundsStrategyID boundsStrategyID;
	private ScrollingCollectionMovementStrategyID movementStrategyID;
	private SliderDirection slidingDirection;
	private Iterable<T> targetEntities;
	private int x;
	private int y;
	private int travel;
	private int amountActive;
	private int scrollbarOffset;
	private int depth;
	private int scrollRegionPadding;
	private int scrollRegionWidth;
	private int scrollRegionHeight;
	
	/**
	 * Creates a new node content buider
	 * @param newX The x position of the upper left hand corner of the content region
	 * @param newY The y position of the upper left hand corner of the content region
	 * @param newScrollbarOffset The offset of the scrollbar for the content
	 * @param newDirection The direction in which the slider will travels
	 * @param newTravel The number of pixels the scrollbar can travel
	 * @param newAmountActive The number of pixels that are visible at any given point in time
	 *                        in the scrollable region
	 * @param newTargetEntities The entities that will be scrolled through
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DecoratedSlidingCollectionBuilder(int newX, int newY, int newScrollbarOffset, SliderDirection newDirection,
			int newTravel, int newAmountActive, Iterable newTargetEntities)
	{
		// Properties provided
		x = newX;
		y = newY;
		travel = newTravel;
		scrollbarOffset = newScrollbarOffset;
		amountActive = newAmountActive;
		targetEntities = newTargetEntities;
		slidingDirection = newDirection;
		
		// Universal defaults
		boundsStrategyID = DEFAULT_BOUNDS_STRATEGY_ID;
		movementStrategyID = DEFAULT_MOVEMENT_STRATEGY_ID;
		scrollRegionPadding = DEFAULT_SCROLL_PADDING;
		depth = PhineasDrawable.DEFAULT_DEPTH;
		
		// Orientation specific properties
		if(slidingDirection == SliderDirection.HORIZONTAL)
		{
			scrollRegionHeight = scrollbarOffset + scrollRegionPadding;
			scrollRegionWidth = amountActive;
		}
		else
		{
			scrollRegionWidth = scrollbarOffset + scrollRegionPadding;
			scrollRegionHeight = amountActive;
		}
	};
	
	public void setBoundsStrategyID(ScrollingCollectionBoundsStrategyID newBoundsStrategy)
	{
		
	}
	
	/**
	 * Actually makes the contents of the tab
	 * @return Newly created tab contents
	 */
	@SuppressWarnings("unchecked")
	public DecoratedSlidingCollection createContents()
	{
		@SuppressWarnings("rawtypes")
		ScrollingCollection newScrollingCollection;
		SliderSourceValueScrubber scrubbedSource;
		
		FittedSlenderSliderBuilder scrollBarBuilder;
		ScrollResponsiveRegion newScrollingRegion;
		SlenderSlider newScrollbar;
		
		int sliderX;
		int sliderY;
		
		// Calculate direction specific properties
		if(slidingDirection == SliderDirection.HORIZONTAL)
		{
			sliderX = x;
			sliderY = y + scrollbarOffset;
		}
		else
		{
			sliderY = y;
			sliderX = x + scrollbarOffset;
		}
		
		// Create collection
		newScrollingCollection = ScrollingCollectionFactory.getInstance().createCollection(
				boundsStrategyID, movementStrategyID, targetEntities, slidingDirection, amountActive);
		scrubbedSource = new SliderSourceValueScrubber(newScrollingCollection);
		
		// Determine if a scrollbar is needed
		if(scrubbedSource.getMaxValue() > 0)
		{
			// Create scrollbar
			scrollBarBuilder = new FittedSlenderSliderBuilder(scrubbedSource, sliderX, sliderY, 
					travel, amountActive);
			scrollBarBuilder.setDepth(depth);
			newScrollbar = scrollBarBuilder.createSlider();
		}
		else
		{
			newScrollbar = null;
		}
		
		// Create scrolling region
		newScrollingRegion = new ScrollResponsiveRegion(scrubbedSource, x, y, scrollRegionWidth, 
				scrollRegionHeight);
		
		if(newScrollbar == null)
		{
			return new DecoratedSlidingCollection(newScrollingCollection, newScrollingRegion, 
					(Iterable<Object>) targetEntities);
		}
		else
		{
			return new DecoratedSlidingCollection(newScrollingCollection, newScrollingRegion, newScrollbar, 
					(Iterable<Object>) targetEntities);
		}
	}
}

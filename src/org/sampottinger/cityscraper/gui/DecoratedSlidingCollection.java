package org.sampottinger.cityscraper.gui;

import java.util.LinkedList;
import java.util.List;

import org.phineas.core.PhineasCompoundGameObject;

/**
 * Contents of a "tab" for node type selection
 * @author Sam Pottinger
 */
public class DecoratedSlidingCollection implements PhineasCompoundGameObject
{
	@SuppressWarnings("rawtypes")
	private ScrollingCollection scrollingCollection;
	
	private ScrollResponsiveRegion scrollingRegion;
	private SlenderSlider scrollbar;
	private Iterable<Object> entities;
	
	/**
	 * Creates a new node contents set with the given values.
	 * @param newScrollingCollection Object collection that moves under the
	 * 		command of a SlenderSlider.
	 * @param newScrollingRegion The scroll wheel aware region that can also
	 * 		scroll objects.
	 * @param newScrollbar The actual scrollbar for scrolling the scroll
	 * 		collection.
	 * @param newEntities The actual elements that are being scrolled through.
	 */
	@SuppressWarnings("rawtypes")
	public DecoratedSlidingCollection(
			ScrollingCollection newScrollingCollection,
			ScrollResponsiveRegion newScrollingRegion,
			Iterable<Object> newEntities)
	{
		scrollingCollection = newScrollingCollection;
		scrollingRegion = newScrollingRegion;
		entities = newEntities;
		scrollbar = null;
	}
	
	/**
	 * Creates a new node contents set with the given values.
	 * @param newScrollingCollection Object collection that moves under the
	 * 		command of a SlenderSlider.
	 * @param newScrollingRegion The scroll wheel aware region that can also
	 * 		scroll objects.
	 * @param newScrollbar The actual scrollbar for scrolling the scroll
	 * 		collection.
	 * @param newEntities The actual elements that are being scrolled through.
	 */
	@SuppressWarnings("rawtypes")
	public DecoratedSlidingCollection(
			ScrollingCollection newScrollingCollection,
			ScrollResponsiveRegion newScrollingRegion,
			SlenderSlider newScrollbar, Iterable<Object> newEntities)
	{
		scrollingCollection = newScrollingCollection;
		scrollingRegion = newScrollingRegion;
		scrollbar = newScrollbar;
		entities = newEntities;
	}

	@Override
	public Iterable<Object> getComponents()
	{
		List<Object> retVal = new LinkedList<Object>();
		
		retVal.add(scrollingCollection);
		retVal.add(scrollingRegion);
		
		if(scrollbar != null)
			retVal.add(scrollbar);
		
		for(Object o : entities)
			retVal.add(o);
		
		return retVal;
	}
	
	
}

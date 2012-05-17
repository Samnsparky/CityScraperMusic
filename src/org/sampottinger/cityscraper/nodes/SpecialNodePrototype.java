package org.sampottinger.cityscraper.nodes;

import java.io.IOException;

import org.phineas.contrib.PhineasSprite;
import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasDrawable;
import org.phineas.core.PhineasPlaceable;

/**
 * Prototype for non-sound nodes
 * @author Sam Pottinger
 */
public class SpecialNodePrototype<T extends PhineasPlaceable & PhineasBoundable & PhineasDrawable>
	implements CityScraperNodePrototype
{
	public enum SpecialNodeType { ADD_ONE, EQUAL_GATE, GREATER_GATE, LESS_GATE, SPAWN_NODE, SUB_ONE }
	
	private static final int DEFAULT_X = 0;
	private static final int DEFAULT_Y = 0;
	
	private T icon;
	
	@SuppressWarnings("unchecked")
	public SpecialNodePrototype(SpecialNodeType type) throws IOException
	{
		// Assign icon
		icon = (T) new PhineasSprite(DEFAULT_X, DEFAULT_Y, SpecialNodeImgLocResolver.getInstance().getLoc(type));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T getPreview() 
	{
		return (T) icon;
	}
}

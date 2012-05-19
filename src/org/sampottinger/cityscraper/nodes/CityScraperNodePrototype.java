package org.sampottinger.cityscraper.nodes;

import java.io.IOException;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasDrawable;
import org.phineas.core.PhineasPlaceable;

/**
 * Prototype strategy factory mashup that can polymorphistically
 * create CityScraperNodes or describe a node type
 * @author Sam Pottinger
 */
public interface CityScraperNodePrototype
{
	/**
	 * Get a small icon preview of the target node that can be used when placing an instance of
	 * @return Small preview image of the node
	 */
	public <T extends PhineasPlaceable & PhineasBoundable & PhineasDrawable> T getPreview();

	/**
	 * Create an instance of the node that this prototype represents
	 * @param x The x position to create the instance at
	 * @param y The y position to create the instance at
	 * @return Newly created node at the given position
	 * @throws IOException Thrown if the grapic for the node could not be loaded
	 */
	public CityScraperNode createNode(int x, int y) throws IOException;
}

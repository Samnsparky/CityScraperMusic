package org.sampottinger.cityscraper.nodes;

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
}

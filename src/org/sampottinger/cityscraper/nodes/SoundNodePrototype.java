package org.sampottinger.cityscraper.nodes;

import java.io.IOException;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasDrawable;
import org.phineas.core.PhineasPlaceable;
import org.phineas.contrib.PhineasSprite;

/**
 * Prototype used to create sound node instances
 * @author Sam Pottinger
 */
public class SoundNodePrototype <T extends PhineasPlaceable & PhineasBoundable & PhineasDrawable> 
implements CityScraperNodePrototype
{
	private PhineasSprite icon;
	
	public SoundNodePrototype(String newIconLoc, String newSoundLoc) throws IOException
	{
		icon = new PhineasSprite(0, 0, newIconLoc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getPreview()
	{
		return (T) icon;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public CityScraperNode createNode(int x, int y)
	{
		return new SoundNode(icon.clone());
	}
}

package org.sampottinger.cityscraper.nodes;

import java.io.IOException;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasDrawable;
import org.phineas.core.PhineasPlaceable;
import org.phineas.core.PhineasSound;
import org.phineas.contrib.PhineasSprite;

/**
 * Prototype used to create sound node instances
 * @author Sam Pottinger
 */
public class SoundNodePrototype<T extends PhineasPlaceable & PhineasBoundable & PhineasDrawable>
	implements CityScraperNodePrototype
{
	private PhineasSound sound;
	T icon;
	
	/**
	 * Creates a new prototype for a sound node with the given
	 * icon and sound
	 * @param newIcon The icon to use for the new node
	 * @param newSound The sound to assign to the node
	 */
	public SoundNodePrototype(T newIcon, PhineasSound newSound)
	{
		icon = newIcon;
		sound = newSound;
	}
	
	@SuppressWarnings("unchecked")
	public SoundNodePrototype(String newIconLoc, String newSoundLoc) throws IOException
	{
		icon = (T) new PhineasSprite(0, 0, newIconLoc);
	}

	@Override
	public T getPreview()
	{
		return icon;
	}
}

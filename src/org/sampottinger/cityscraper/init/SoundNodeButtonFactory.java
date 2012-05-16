package org.sampottinger.cityscraper.init;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.gui.IconToggleButton;
import org.sampottinger.cityscraper.gui.ToggleButton;

/**
 * Facade to work with sound node configuration file
 * @author Sam Pottinger
 */
public class SoundNodeButtonFactory
{
	private static SoundNodeButtonFactory instance = null;
	
	private Iterable<SoundInfoRecord> infoRecords;

	/**
	 * Gets a shared instance of this configuration facade
	 * @return Shared instance of this singleton
	 */
	public static SoundNodeButtonFactory getInstance()
	{
		if(instance == null)
			instance = new SoundNodeButtonFactory();
		return instance;
	}
	
	private SoundNodeButtonFactory()
	{
		infoRecords = null;
	}
	
	/**
	 * Creates new vertically stacked toggle buttons for all of the available sounds
	 * @param x The x position for the toggle buttons to align against
	 * @param startY The top y position to start placing toggle buttons
	 * @param verticalPadding How many pixels to put between the toggle buttons
	 * @param bgDepth The level at which the button backgrounds should be drawn (affects drawing order)
	 * @param fgDepth The level at which the button foregrounds should be drawn (affects drawing order)
	 * @return Iterable over new toggle button instances
	 * @throws PhineasException Thrown when an icon is corrupted and fails to load
	 * @throws IOException Thrown when an icon can not be found
	 */
	public Iterable<ToggleButton> getToggleButtons(int x, int startY, int verticalPadding, 
			int bgDepth, int fgDepth) throws IOException, PhineasException
	{
		int y;
		ToggleButton newButton;
		List<ToggleButton> newList;

		newList = new ArrayList<ToggleButton>();
		
		y = startY;
		for(SoundInfoRecord target : getRecords())
		{
			newButton = new IconToggleButton(x, y, target.getDisplayName(), target.getImageLoc(), bgDepth, fgDepth);
			newList.add(newButton);
			y += newButton.getHeight() + verticalPadding;
		}
		
		return newList;
	}
	
	/**
	 * Loads and caches info about available sounds or returns cache if available
	 * @return Iterable over loaded sound info records
	 * @throws IOException 
	 */
	private Iterable<SoundInfoRecord> getRecords() throws IOException
	{
		if(infoRecords == null)
			infoRecords = SoundConfigurationLoader.getInstance().getSoundInfo();
		
		return infoRecords;
	}
}

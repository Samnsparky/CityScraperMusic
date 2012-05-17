package org.sampottinger.cityscraper.init;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.gui.IconToggleButton;
import org.sampottinger.cityscraper.gui.ToggleButton;
import org.sampottinger.cityscraper.gui.nodeselection.NodeTypeSelectorStateManager;

/**
 * Facade to work with sound node configuration file to load sound
 * node prototypes and selection buttons
 * @author Sam Pottinger
 */
public class SoundNodeTypeInitializer implements NodeTypeInitializer
{
	private static SoundNodeTypeInitializer instance = null;
	
	private Iterable<SoundInfoRecord> infoRecords;

	/**
	 * Gets a shared instance of this configuration facade
	 * @return Shared instance of this singleton
	 */
	public static SoundNodeTypeInitializer getInstance()
	{
		if(instance == null)
			instance = new SoundNodeTypeInitializer();
		return instance;
	}
	
	private SoundNodeTypeInitializer()
	{
		infoRecords = null;
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

	@Override
	public Iterable<ToggleButton> createAndRegisterButtons(int x, int startY,
			int verticalPadding, int bgDepth, int fgDepth,
			NodeTypeSelectorStateManager selectorManager) throws IOException,
			PhineasException {
		
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
}

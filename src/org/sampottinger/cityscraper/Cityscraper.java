package org.sampottinger.cityscraper;
import java.io.IOException;

import org.phineas.core.PhineasDrawable;
import org.phineas.core.PhineasException;
import org.phineas.core.PhineasGameFacade;
import org.sampottinger.cityscraper.gui.DecoratedSlidingCollection;
import org.sampottinger.cityscraper.gui.IconToggleButton;
import org.sampottinger.cityscraper.gui.SlenderSlider.SliderDirection;
import org.sampottinger.cityscraper.gui.ToggleButton;
import org.sampottinger.cityscraper.gui.ToggleUnderlineButton;
import org.sampottinger.cityscraper.gui.nodeselection.NodeTypeSelectorGUI;
import org.sampottinger.cityscraper.gui.nodeselection.TabSelectionButton.TabType;
import org.sampottinger.cityscraper.init.DecoratedSlidingCollectionBuilder;
import org.sampottinger.cityscraper.init.NodeTabSelectButtonBuilder;
import org.sampottinger.cityscraper.init.NodeTypeSelectorBuilder;
import org.sampottinger.cityscraper.init.SoundNodeTypeInitializer;

/**
 * Main executable class for the Cityscraper game
 * @author Sam Pottinger
 */
public class Cityscraper {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 700;
	private static final int NODE_SELECTOR_X = 645;
	private static final int NODE_SELECTOR_Y = 0;
	
	private static final int BUTTON_FG_DEPTH = PhineasDrawable.DEFAULT_DEPTH;
	private static final int BUTTON_BG_DEPTH = PhineasDrawable.DEFAULT_DEPTH + 1;
	
	public static void main(String [] args)
	{
		NodeTypeSelectorBuilder nodeSelectorBuilder;
		NodeTypeSelectorGUI nodeTypeSelector;
		
		// Create high level game facade
		PhineasGameFacade game = PhineasGameFacade.getInstance();
		
		// Resize game window
		game.setDimensions(WIDTH, HEIGHT); 
		
		// Start game
		try 
		{	
			// Create node type selector
			nodeSelectorBuilder = new NodeTypeSelectorBuilder(NODE_SELECTOR_X, NODE_SELECTOR_Y, HEIGHT, 
					BUTTON_BG_DEPTH, BUTTON_FG_DEPTH, game);
			nodeTypeSelector = nodeSelectorBuilder.createSelector();
			nodeTypeSelector.showTab(TabType.FLOW);
			game.addEntity(nodeTypeSelector);
			
			game.startGame();
		}
		catch (PhineasException e)
		{
			System.out.println("Failed to start game because of Phineas errror: " + e);
		}
		catch (IOException e) 
		{
			System.out.println("Failed to start game because of IO error: " + e);
		}
	}

}

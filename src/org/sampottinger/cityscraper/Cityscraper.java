package org.sampottinger.cityscraper;
import java.io.IOException;

import org.phineas.core.PhineasDrawable;
import org.phineas.core.PhineasException;
import org.phineas.core.PhineasGameFacade;
import org.sampottinger.cityscraper.gui.WorkspaceRegion;
import org.sampottinger.cityscraper.gui.nodeselection.NodeTypeSelectorGUI;
import org.sampottinger.cityscraper.gui.nodeselection.TabSelectionButton.TabType;
import org.sampottinger.cityscraper.init.NodeTypeBuilder;
import org.sampottinger.cityscraper.workspace.WorkspaceGrid;
import org.sampottinger.cityscraper.workspace.WorkspaceManager;

/**
 * Main executable class for the Cityscraper game
 * @author Sam Pottinger
 */
public class Cityscraper {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 700;
	private static final int NODE_SELECTOR_X = 645;
	private static final int NODE_SELECTOR_Y = 0;
	private static final int WORKSPACE_WIDTH = 610;
	private static final int WORKSPACE_HEIGHT = HEIGHT;
	private static final int X_DISCRETE_STEP = 16;
	private static final int Y_DISCRETE_STEP = 16;
	
	private static final int BUTTON_FG_DEPTH = PhineasDrawable.DEFAULT_DEPTH;
	private static final int BUTTON_BG_DEPTH = PhineasDrawable.DEFAULT_DEPTH + 1;
	
	@SuppressWarnings("rawtypes")
	public static void main(String [] args)
	{
		NodeTypeBuilder nodeSelectorBuilder;
		NodeTypeSelectorGUI nodeTypeSelector;
		WorkspaceGrid workspaceGrid;
		WorkspaceManager workspaceManager;
		WorkspaceRegion workspaceRegion;
		
		// Create high level game facade
		PhineasGameFacade game = PhineasGameFacade.getInstance();
		
		// Resize game window
		game.setDimensions(WIDTH, HEIGHT); 
		
		// Start game
		try 
		{	
			// Create node type selector
			nodeSelectorBuilder = new NodeTypeBuilder(NODE_SELECTOR_X, NODE_SELECTOR_Y, HEIGHT, 
					BUTTON_BG_DEPTH, BUTTON_FG_DEPTH, game);
			nodeTypeSelector = nodeSelectorBuilder.createSelector();
			nodeTypeSelector.showTab(TabType.FLOW);
			game.addEntity(nodeTypeSelector);
			
			// Create occupancy grid
			workspaceGrid = new WorkspaceGrid(0, 0, WORKSPACE_WIDTH, WORKSPACE_HEIGHT, X_DISCRETE_STEP, Y_DISCRETE_STEP);
			
			// Create workspace manager
			workspaceManager = new WorkspaceManager(workspaceGrid, game, 0, 0, WORKSPACE_WIDTH, WORKSPACE_HEIGHT);
			
			// Add workspace region
			workspaceRegion = new WorkspaceRegion(0, 0, WORKSPACE_WIDTH, WORKSPACE_HEIGHT, nodeTypeSelector, 
					workspaceManager, X_DISCRETE_STEP, Y_DISCRETE_STEP);
			game.addEntity(workspaceRegion);
			
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

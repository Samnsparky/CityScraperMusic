package org.sampottinger.cityscraper.init;

import java.io.IOException;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import org.phineas.core.PhineasException;
import org.phineas.core.PhineasGameFacade;
import org.sampottinger.cityscraper.gui.DecoratedSlidingCollection;
import org.sampottinger.cityscraper.gui.IconToggleButton;
import org.sampottinger.cityscraper.gui.ToggleButton;
import org.sampottinger.cityscraper.gui.SlenderSlider.SliderDirection;
import org.sampottinger.cityscraper.gui.nodeselection.NodeTypeSelectorGUI;
import org.sampottinger.cityscraper.gui.nodeselection.NodeTypeSelectorJanitor;
import org.sampottinger.cityscraper.gui.nodeselection.TabRecord;
import org.sampottinger.cityscraper.gui.nodeselection.TabSelectionButton;
import org.sampottinger.cityscraper.gui.nodeselection.TabSelectionButton.TabType;

/**
 * Builder that creates and registers node type prototypes
 * and the selection buttons / tabs used to choose them
 * @author Sam Pottinger
 */
public class NodeTypeBuilder
{
	private static int DEFAULT_TABS_VERT_OFFSET = 20;
	private static int DEFAULT_TOGGLE_BUTTONS_HORIZ_OFFSET = 35;
	private static int DEFAULT_TOGGLE_BUTTON_PADDING = 7;
	private static int DEFAULT_SCROLL_BAR_HORIZ_OFFSET = 105;
	
	private int x;
	private int y;
	private int height;
	private int bgDepth;
	private int fgDepth;
	private int toggleButtonsHorizOffset;
	private int tabsVertOffset;
	private int scrollBarHorizOffset;
	private int toggleButtonPadding;
	private PhineasGameFacade targetGame;
	
	/**
	 * Creates a new node type selector builder
	 * @param newX The x position of the upper left corner of this builder's selectors
	 * @param newY The y position of the upper left corner of this builder's selectors
	 * @param newHeight The height of this builder's selectors
	 * @param newBGDepth The depth at which button backgrounds should be drawn
	 * @param newFGDepth The depth at which button foregrounds should be drawn
	 * @param newTargetGame The game that this selector will be part of
	 */
	public NodeTypeBuilder(int newX, int newY, int newHeight, int newBGDepth, int newFGDepth,
			PhineasGameFacade newTargetGame)
	{
		// Simple properties
		x = newX;
		y = newY;
		height = newHeight;
		bgDepth = newBGDepth;
		fgDepth = newFGDepth;
		targetGame = newTargetGame;
		
		// Defaults
		toggleButtonsHorizOffset = DEFAULT_TOGGLE_BUTTONS_HORIZ_OFFSET;
		tabsVertOffset = DEFAULT_TABS_VERT_OFFSET;
		scrollBarHorizOffset = DEFAULT_SCROLL_BAR_HORIZ_OFFSET;
		toggleButtonPadding = DEFAULT_TOGGLE_BUTTON_PADDING;
	}
	
	/**
	 * Creates and returns a new selector that meets the specifications previously provided to this
	 * builder 
	 * @throws IOException Thrown if an image could not be found
	 * @throws PhineasException Thrown if image corrupted
	 */
	public NodeTypeSelectorGUI createSelector() throws IOException, PhineasException
	{	
		int toggleButtonX;
		Collection<TabRecord> tabRecords;
		Iterable<ToggleButton> currentButtons;
		DecoratedSlidingCollectionBuilder<IconToggleButton> currentBuilder;
		SimpleImmutableEntry<TabType,NodeTypeInitializer> currentPair;
		DecoratedSlidingCollection currentContents;
		TabSelectionButton currentTabButton;
		TabType currentType;
		NodeTypeInitializer currentInitializer;
		NodeTabSelectButtonBuilder tabLabelBuilder;
		NodeTypeSelectorGUI retVal;
		NodeTypeSelectorJanitor janitor;
		
		// Initialize helper structures
		Queue<SimpleImmutableEntry<TabType,NodeTypeInitializer>> buildQueue = 
				new LinkedList<SimpleImmutableEntry<TabType,NodeTypeInitializer>>();
		tabRecords = new LinkedList<TabRecord>();
		
		// Initialize tab selection button builder
		tabLabelBuilder = new NodeTabSelectButtonBuilder(x, y + tabsVertOffset);
		
		// Calculate positions
		toggleButtonX = x + toggleButtonsHorizOffset;
		
		// Prime build queue
		buildQueue.add(new SimpleImmutableEntry<TabType,NodeTypeInitializer>(TabType.FLOW, 
				FlowNodeTypeInitializer.getInstance()));
		buildQueue.add(new SimpleImmutableEntry<TabType,NodeTypeInitializer>(TabType.LOGIC, 
				LogicNodeTypeInitializer.getInstance()));
		buildQueue.add(new SimpleImmutableEntry<TabType,NodeTypeInitializer>(TabType.MATH, 
				MathNodeTypeInitializer.getInstance()));
		buildQueue.add(new SimpleImmutableEntry<TabType,NodeTypeInitializer>(TabType.SOUND, 
				SoundNodeTypeInitializer.getInstance()));
		
		// Build tabs
		while(!buildQueue.isEmpty())
		{
			currentPair = buildQueue.remove();
			currentType = currentPair.getKey();
			currentInitializer = currentPair.getValue();
			
			currentButtons = currentInitializer.createAndRegisterButtons(toggleButtonX, y, toggleButtonPadding, 
					bgDepth, fgDepth, null);
			currentBuilder = new DecoratedSlidingCollectionBuilder<IconToggleButton>(toggleButtonX, 
					y, scrollBarHorizOffset, SliderDirection.VERTICAL, height, height, currentButtons);
			currentContents = currentBuilder.createContents();
			currentTabButton = tabLabelBuilder.createTabButton(currentType);
			tabRecords.add(new TabRecord(currentTabButton, currentContents));
		}
		
		// Create node selector
		janitor = new NodeTypeSelectorJanitor(targetGame);
		retVal = new NodeTypeSelectorGUI(janitor);
		for(TabRecord record : tabRecords)
			retVal.addTab(record);
		
		return retVal;
	}
}

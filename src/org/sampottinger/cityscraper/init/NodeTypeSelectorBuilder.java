package org.sampottinger.cityscraper.init;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import org.phineas.core.PhineasException;
import org.phineas.core.PhineasGameFacade;
import org.sampottinger.cityscraper.gui.DecoratedSlidingCollection;
import org.sampottinger.cityscraper.gui.IconToggleButton;
import org.sampottinger.cityscraper.gui.ToggleButton;
import org.sampottinger.cityscraper.gui.SlenderSlider.SliderDirection;
import org.sampottinger.cityscraper.gui.nodeselection.NodeTypeSelector;
import org.sampottinger.cityscraper.gui.nodeselection.NodeTypeSelectorJanitor;
import org.sampottinger.cityscraper.gui.nodeselection.SpawnNodeButton;
import org.sampottinger.cityscraper.gui.nodeselection.TabRecord;
import org.sampottinger.cityscraper.gui.nodeselection.TabSelectionButton;

/**
 * Collection of GUI widgets that allow a user to choose what type of
 * node they would like to add to their project
 * @author Sam Pottinger
 */
public class NodeTypeSelectorBuilder
{
	private static int DEFAULT_TABS_VERT_OFFSET = 20;
	private static int DEFAULT_TOGGLE_BUTTONS_HORIZ_OFFSET = 35;
	private static int DEFAULT_TOGGLE_BUTTON_PADDING = 7;
	private static int DEFAULT_SCROLL_BAR_HORIZ_OFFSET = 145;
	
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
	public NodeTypeSelectorBuilder(int newX, int newY, int newHeight, int newBGDepth, int newFGDepth,
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
	public NodeTypeSelector createSelector() throws IOException, PhineasException
	{
		// TODO: This is a really messy method and should be cleaned up
		
		int toggleButtonX;
		
		NodeTypeSelector retVal;
		NodeTypeSelectorJanitor janitor;
		
		Collection<TabRecord> tabRecords;
		
		NodeTabSelectButtonBuilder tabLabelBuilder;
		
		DecoratedSlidingCollectionBuilder<IconToggleButton> flowTabBuilder;
		TabSelectionButton flowTabLabelButton;
		DecoratedSlidingCollection flowTabContents;
		Collection<ToggleButton> flowButtons;
		
		LogicToggleButtonFactory logicButtonFactory;
		DecoratedSlidingCollectionBuilder<IconToggleButton> logicTabBuilder;
		TabSelectionButton logicTabLabelButton;
		DecoratedSlidingCollection logicTabContents;
		Iterable<ToggleButton> logicButtons;
		
		MathToggleButtonFactory mathButtonFactory;
		DecoratedSlidingCollectionBuilder<IconToggleButton> mathTabBuilder;
		DecoratedSlidingCollection mathTabContents;
		TabSelectionButton mathTabLabelButton;
		Iterable<ToggleButton> mathButtons;
		
		SoundNodeButtonFactory soundButtonFactory;
		DecoratedSlidingCollectionBuilder<IconToggleButton> soundTabBuilder;
		TabSelectionButton soundTabLabelButton;
		DecoratedSlidingCollection soundTabContents;
		Iterable<ToggleButton> soundButtons;
		
		// Get appropriate factories
		logicButtonFactory = LogicToggleButtonFactory.getInstance();
		mathButtonFactory = MathToggleButtonFactory.getInstance();
		soundButtonFactory = SoundNodeButtonFactory.getInstance();
		
		// Create collection for records
		tabRecords = new LinkedList<TabRecord>();
		
		// Create tab builder
		tabLabelBuilder = new NodeTabSelectButtonBuilder(x, y + tabsVertOffset);
		
		// Calculate positions
		toggleButtonX = x + toggleButtonsHorizOffset;
		
		// Create flow buttons tab
		flowButtons = new LinkedList<ToggleButton>();
		flowButtons.add(new SpawnNodeButton(toggleButtonX, y, bgDepth, fgDepth));
		flowTabBuilder = new DecoratedSlidingCollectionBuilder<IconToggleButton>(toggleButtonX, 
				y, x + scrollBarHorizOffset, SliderDirection.VERTICAL, height, height, flowButtons);
		flowTabContents = flowTabBuilder.createContents();
		flowTabLabelButton = tabLabelBuilder.createTabButton(TabSelectionButton.TabType.FLOW);
		tabRecords.add(new TabRecord(flowTabLabelButton, flowTabContents));
		
		// Create logic buttons tab
		logicButtons = logicButtonFactory.createButtons(toggleButtonX, y, toggleButtonPadding, 
				bgDepth, fgDepth);
		logicTabBuilder = new DecoratedSlidingCollectionBuilder<IconToggleButton>(toggleButtonX, 
				y, scrollBarHorizOffset, SliderDirection.VERTICAL, height, height, logicButtons);
		logicTabContents = logicTabBuilder.createContents();
		logicTabLabelButton = tabLabelBuilder.createTabButton(TabSelectionButton.TabType.LOGIC);
		tabRecords.add(new TabRecord(logicTabLabelButton, logicTabContents));
		
		// Create math buttons tab
		mathButtons = mathButtonFactory.createButtons(toggleButtonX, y, toggleButtonPadding, 
				bgDepth, fgDepth);
		mathTabBuilder = new DecoratedSlidingCollectionBuilder<IconToggleButton>(toggleButtonX, 
				y, scrollBarHorizOffset, SliderDirection.VERTICAL, height, height, mathButtons);
		mathTabContents = mathTabBuilder.createContents();
		mathTabLabelButton = tabLabelBuilder.createTabButton(TabSelectionButton.TabType.MATH);
		tabRecords.add(new TabRecord(mathTabLabelButton, mathTabContents));
		
		// Create sound buttons tab
		soundButtons = soundButtonFactory.getToggleButtons(toggleButtonX, y, toggleButtonPadding, 
				bgDepth, fgDepth);
		soundTabBuilder = new DecoratedSlidingCollectionBuilder<IconToggleButton>(toggleButtonX, 
				y, scrollBarHorizOffset, SliderDirection.VERTICAL, height, height, soundButtons);
		soundTabContents = soundTabBuilder.createContents();
		soundTabLabelButton = tabLabelBuilder.createTabButton(TabSelectionButton.TabType.SOUND);
		tabRecords.add(new TabRecord(soundTabLabelButton, soundTabContents));
		
		// Create node selector
		janitor = new NodeTypeSelectorJanitor(targetGame);
		retVal = new NodeTypeSelector(janitor);
		for(TabRecord record : tabRecords)
			retVal.addTab(record);
		
		return retVal;
	}
}

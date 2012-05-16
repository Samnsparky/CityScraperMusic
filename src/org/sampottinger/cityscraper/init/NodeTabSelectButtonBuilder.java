package org.sampottinger.cityscraper.init;

import java.io.IOException;

import org.sampottinger.cityscraper.gui.nodeselection.TabSelectionButton;
import org.sampottinger.cityscraper.gui.nodeselection.TabSelectionButton.TabType;

/**
 * Factory singleton to create tabs to go between groups
 * of toggle buttons for node type selection (stacked
 * vertically)
 * @author Sam Pottinger
 */
public class NodeTabSelectButtonBuilder
{
	private static final int TAB_Y_SIZE = 100;
	private static final String FLOW_BUTTON_IMG = "img/button_flow.png";
	private static final String FLOW_BUTTON_HIGHLIGHT_IMG = "img/button_flow_highlight.png";
	private static final String LOGIC_BUTTON_IMG = "img/button_logic.png";
	private static final String LOGIC_BUTTON_HIGHLIGHT_IMG = "img/button_logic_highlight.png";
	private static final String MATH_BUTTON_IMG = "img/button_math.png";
	private static final String MATH_BUTTON_HIGHLIGHT_IMG = "img/button_math_highlight.png";
	private static final String SOUND_BUTTON_IMG = "img/button_sound.png";
	private static final String SOUND_BUTTON_HIGHLIGHT_IMG = "img/button_sound_highlight.png";
	
	private int x;
	private int y;
	
	/**
	 * Create a new tab builder starting at the given positions
	 * @param newX The x position where left edges should be aligned
	 * @param startY The starting y position where the labels will be placed
	 */
	public NodeTabSelectButtonBuilder(int newX, int startY)
	{
		x = newX;
		y = startY;
	}
	
	/**
	 * Create a new tab label button for the given type of tab
	 * @param type The type of tab label to create
	 * @return New toggle button for given tab
	 * @throws IOException
	 */
	public TabSelectionButton createTabButton(TabType type) throws IOException
	{
		TabSelectionButton retVal = null;
		
		switch(type)
		{
		case FLOW:
			retVal = new TabSelectionButton(x, y, FLOW_BUTTON_IMG, FLOW_BUTTON_HIGHLIGHT_IMG, TabType.FLOW);
			break;
		case LOGIC:
			retVal = new TabSelectionButton(x, y, LOGIC_BUTTON_IMG, LOGIC_BUTTON_HIGHLIGHT_IMG, TabType.LOGIC);
			break;
		case MATH:
			retVal = new TabSelectionButton(x, y, MATH_BUTTON_IMG, MATH_BUTTON_HIGHLIGHT_IMG, TabType.MATH);
			break;
		case SOUND:
			retVal = new TabSelectionButton(x, y, SOUND_BUTTON_IMG, SOUND_BUTTON_HIGHLIGHT_IMG, TabType.SOUND);
			break;
		}
		
		y += TAB_Y_SIZE;
		
		return retVal;
	}
}

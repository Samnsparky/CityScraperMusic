package org.sampottinger.cityscraper.init;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.gui.ToggleButton;
import org.sampottinger.cityscraper.gui.nodeselection.EqualGateNodeButton;
import org.sampottinger.cityscraper.gui.nodeselection.GreaterGateNodeButton;
import org.sampottinger.cityscraper.gui.nodeselection.LessGateNodeButton;

/**
 * Factory that builds logic toggle buttons for the node type selection component
 * @author Sam Pottinger
 */
public class LogicToggleButtonFactory
{
	private static LogicToggleButtonFactory instance = null;
	
	private LogicToggleButtonFactory() {}
	
	/**
	 * Gets a shared instance of this factory
	 * @return Shared instance of this singleton
	 */
	public static LogicToggleButtonFactory getInstance() {
		if(instance == null)
			instance = new LogicToggleButtonFactory();
		return instance;
	}
	
	/**
	 * Create fixed set of logic toggle buttons
	 * @param x The x position where the left of the buttons should be aligned
	 * @param y The y position of the upper left corner of the first button
	 * @param verticalPadding The extra spacing to put between buttons
	 * @param bgDepth The depth at which the backgrounds of the buttons should
	 *                be drawn
	 * @param fgDepth The depth at which the foregrounds of the buttons should
	 *                be drawn
	 * @return Iterable over newly created buttons
	 * @throws IOException Thrown if the file system fails to load an image file
	 * @throws PhineasException Thrown if an image is corrupt
	 */
	public Iterable<ToggleButton> createButtons(int x, int y, int verticalPadding, 
			int bgDepth, int fgDepth) throws IOException, PhineasException
	{
		int buttonVertSize;
		Collection<ToggleButton> retVal;
		ToggleButton previousButton;
		
		retVal = new LinkedList<ToggleButton>();
		
		// TODO: This is pretty messy
		previousButton = new EqualGateNodeButton(x, y, bgDepth, fgDepth);
		buttonVertSize = previousButton.getHeight();
		retVal.add(previousButton);
		y += buttonVertSize + verticalPadding;
		
		retVal.add(new GreaterGateNodeButton(x, y, bgDepth, fgDepth));
		y += buttonVertSize + verticalPadding;
		
		retVal.add(new LessGateNodeButton(x, y, bgDepth, fgDepth));
		
		return retVal;
	}
}

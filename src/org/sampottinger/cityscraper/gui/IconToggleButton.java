package org.sampottinger.cityscraper.gui;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.phineas.contrib.PhineasRectangle;
import org.phineas.contrib.PhineasSprite;
import org.phineas.contrib.PhineasText;
import org.phineas.core.PhineasCompoundGameObject;
import org.phineas.core.PhineasClickListener;
import org.phineas.core.PhineasException;
import org.phineas.core.PhineasHoverListener;
import org.phineas.core.PhineasPlaceable;

/**
 * Toggle button that wraps a small icon with text and an activated box
 * @author Sam Pottinger
**/
public class IconToggleButton implements PhineasHoverListener, PhineasClickListener, ToggleButton,
PhineasCompoundGameObject, PhineasPlaceable
{
	private static final Color INACTIVE_COLOR = Color.BLACK;
	private static final Color ACTIVE_COLOR = new Color(134, 130, 130);
	private static final Color HOVER_COLOR = new Color(217, 210, 210);
	private static final Color TEXT_COLOR = Color.WHITE;
	private static final int WIDTH = 96;
	private static final int HEIGHT = 96;
	private static final int TEXT_VERT_OFFSET = 72;
	private static final int ICON_HORIZ_OFFSET = 32;
	private static final int ICON_VERT_OFFSET = 16;
	private static final int CHARACTER_WIDTH = 7;
	private static final Font CAPTION_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);
	
	private boolean active;
	private int x;
	private int y;
	private PhineasSprite targetIcon;
	private PhineasRectangle targetRectangle;
	private PhineasText targetText;
	private int horizontalTextOffset;
	private Collection<ToggleButtonListener> listeners;
	
	/**
	 * Create a new toggle button
	 * @param newX The x position of the toggle button
	 * @param newY The y position of the toggle button
	 * @param newDepth The level at which this button should be drawn (affects drawing order)
	 * @param newText The text the toggle button should display
	 * @param iconLoc The location of the icon that should appear on this button
	 * @param newBackgroundDepth The depth that the background of the button should be drawn at
	 * @param newForegroundDepth The depth that the foreground of the button should be drawn at
	 * @throws IOException Thrown if the icon could not be found
	 * @throws PhineasException Thrown if the icon was found but was corrupted
	 */
	public IconToggleButton(int newX, int newY, String newText, String iconLoc, 
			int newBackgroundDepth, int newForegroundDepth) throws IOException, PhineasException
	{
		
		// Save simple attributes
		active = false;
		x = newX;
		y = newY;
		
		// Create simple components
		targetIcon = new PhineasSprite(x + ICON_HORIZ_OFFSET, y + ICON_VERT_OFFSET, iconLoc, newForegroundDepth);
		targetRectangle = new PhineasRectangle(x, y, WIDTH, HEIGHT, INACTIVE_COLOR, newBackgroundDepth);
		
		// Create text
		horizontalTextOffset = calculateTextHorizontalOffset(newText);
		targetText = new PhineasText(x + horizontalTextOffset, y + TEXT_VERT_OFFSET, newText, TEXT_COLOR, newForegroundDepth);
		targetText.setFont(CAPTION_FONT);
		
		// Create collection for listeners
		listeners = new ArrayList<ToggleButtonListener>();
	}
	
	/**
	 * Calculates the horizontal position of this button's caption text
	 */
	private int calculateTextHorizontalOffset(String text)
	{
		return (int)(WIDTH / 2.0 - text.length() / 2.0 * CHARACTER_WIDTH);
	}

	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}

	@Override
	public int getWidth()
	{
		return WIDTH;
	}

	@Override
	public int getHeight()
	{
		return HEIGHT;
	}

	@Override
	public void reset()
	{
		active = false;
		targetRectangle.setColor(INACTIVE_COLOR);
		
		for(ToggleButtonListener listener : listeners)
			listener.onStateChange(this, false);
	}

	@Override
	public boolean isActive()
	{
		return active;
	}

	@Override
	public void onLeftDown(int relativeX, int relativeY)
	{
		activate();
		targetRectangle.setColor(ACTIVE_COLOR);
		
		for(ToggleButtonListener listener : listeners)
			listener.onStateChange(this, true);
	}

	@Override
	public void onLeftRelease(int relativeX, int relativeY)
	{}

	@Override
	public void onEnter()
	{
		if(!active)
			targetRectangle.setColor(HOVER_COLOR);
	}

	@Override
	public void onLeave()
	{
		if(!active)
			targetRectangle.setColor(INACTIVE_COLOR);
	}

	@Override
	public void setX(int newX)
	{
		x = newX;
		targetIcon.setX(newX + ICON_HORIZ_OFFSET);
		targetRectangle.setX(newX);
		targetText.setX(newX + horizontalTextOffset);
	}

	@Override
	public void setY(int newY)
	{
		y = newY;
		targetIcon.setY(newY + ICON_VERT_OFFSET);
		targetRectangle.setY(newY);
		targetText.setY(newY + TEXT_VERT_OFFSET);
	}

	@Override
	public Iterable<Object> getComponents()
	{
		List<Object> returnVal = new LinkedList<Object>();
		
		returnVal.add(targetIcon);
		returnVal.add(targetRectangle);
		returnVal.add(targetText);
		
		return returnVal;
	}

	@Override
	public void attachListener(ToggleButtonListener newListener)
	{
		listeners.add(newListener);
	}

	@Override
	public void deattachListener(ToggleButtonListener oldListener)
	{
		listeners.remove(oldListener);
	}

	@Override
	public void activate() 
	{
		active = true;
		targetRectangle.setColor(HOVER_COLOR);
	}
}

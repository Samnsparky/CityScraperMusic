package org.sampottinger.cityscraper.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.phineas.contrib.PhineasSprite;
import org.phineas.core.PhineasClickListener;
import org.phineas.core.PhineasHoverListener;

/**
 * A button that can be activated and will stay activated until
 * expressly deactivated
 * @author Sam Pottinger
 */
public class ToggleUnderlineButton extends PhineasSprite implements PhineasHoverListener, PhineasClickListener, ToggleButton
{
	static int HORIZ_OFFSET = 2;
	
	private boolean activated;
	private String activeLoc;
	private String inactiveLoc;
	private Collection<ToggleButtonListener> listeners;
	
	/**
	 * Creates a new button at the given coordiante with the given image
	 * @param newX The x (pixel) coordinate of this button
	 * @param newY The y (pixel) coordinate of this button
	 * @param newInactiveLoc The location of the image to use when this button is not activated
	 * @param newActiveLoc The location if the image to use when this button has been clicked on
	 *                  and is active
	 * @throws IOException One or more of the images could not be loaded
	 */
	public ToggleUnderlineButton(int newX, int newY, String newInactiveLoc, String newActiveLoc) throws IOException
	{
		super(newX, newY, newInactiveLoc);
		activeLoc = newActiveLoc;
		inactiveLoc = newInactiveLoc;
		
		listeners = new ArrayList<ToggleButtonListener>();
	}

	@Override
	public void onEnter()
	{
		if(!activated)
		{
			try 
			{
				setImage(activeLoc);
			} catch (IOException e) 
			{}
		}
	}

	@Override
	public void onLeave()
	{
		if(!activated)
		{
			try 
			{
				setImage(inactiveLoc);
			} catch (IOException e) 
			{}
		}
	}

	@Override
	public void onLeftDown(int relativeX, int relativeY)
	{
		if(!activated)
			activate();
		
		for(ToggleButtonListener l : listeners)
			l.onStateChange(this, true);
	}

	@Override
	public void onLeftRelease(int relativeX, int relativeY)
	{}
	
	@Override
	public int getWidth()
	{
		return super.getWidth() + HORIZ_OFFSET;
	}
	
	@Override
	public void draw(Graphics2D target)
	{
		super.draw(target);
		
		// Draw line if active
		if(activated)
		{
			// TODO: Lin alg could make this go faster if we
			//       are worried
			
			// Calculate position of line
			int lineStartX = getX() + getWidth() + HORIZ_OFFSET;
			int lineEndX = lineStartX;
			int lineStartY = getY();
			int lineEndY = lineStartY + getHeight();
				
			// Draw line (TODO: Maybe wrap this away)
			target.setColor(Color.WHITE);
			target.drawLine(lineStartX, lineStartY, lineEndX, lineEndY);
		}
	}

	@Override
	public void reset()
	{
		activated = false;
		
		// TODO: This is a bit messy
		try 
		{
			setImage(inactiveLoc);
		} 
		catch (IOException e) {}
		
		for(ToggleButtonListener l : listeners)
			l.onStateChange(this, false);
	}

	@Override
	public boolean isActive()
	{
		return activated;
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
		activated = true;
		
		// TODO: need to clean this up
		try {setImage(activeLoc);} 
		catch (IOException e) {e.printStackTrace();}
	}
}

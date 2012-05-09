package org.sampottinger.cityscraper;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import org.phineas.contrib.PhineasSprite;
import org.phineas.contrib.PhineasSpriteLoader;
import org.phineas.core.PhineasClickListener;
import org.phineas.core.PhineasHoverListener;

/**
 * A button that can be activated and will stay activated until
 * expressly deactivated
 * @author Sam Pottinger
 */
public class ToggleUnderlineButton extends PhineasSprite implements PhineasHoverListener, PhineasClickListener
{
	static int HORIZ_OFFSET = 2;
	
	Image inactiveImg;
	Image activeImg;
	boolean activated;
	
	/**
	 * Creates a new button at the given coordiante with the given image
	 * @param newX The x (pixel) coordinate of this button
	 * @param newY The y (pixel) coordinate of this button
	 * @param inactiveLoc The location of the image to use when this button is not activated
	 * @param activeLoc The location if the image to use when this button has been clicked on
	 *                  and is active
	 * @throws IOException One or more of the images could not be loaded
	 */
	public ToggleUnderlineButton(int newX, int newY, String inactiveLoc, String activeLoc) throws IOException
	{
		super(newX, newY, inactiveLoc);
		
		Image inactiveImg = PhineasSpriteLoader.getInstance().loadSprite(inactiveLoc);
		Image activeImg = PhineasSpriteLoader.getInstance().loadSprite(activeLoc);
		
		setup(inactiveImg, activeImg);
	}

	/**
	 * Creates a new button at the given coordiante with the given image
	 * @param newX The x (pixel) coordinate of this button
	 * @param newY The y (pixel) coordinate of this button
	 * @param inactive The image to use when this button is not activated
	 * @param active The image to use when this button has been clicked on
	 *               and is active
	 */
	public ToggleUnderlineButton(int newX, int newY, Image inactive, Image active)
	{
		super(newX, newY, inactive);
		setup(inactive, active);
	}
	
	/**
	 * Internal book keeping operations regarding this button's images
	 * and state
	 * @param newInactive The image to use when this button is inactive
	 * @param newActive The image to use when this button is active
	 */
	private void setup(Image newInactive, Image newActive)
	{
		inactiveImg = newInactive;
		activeImg = newActive;
		activated = false;
	}

	@Override
	public void onEnter()
	{
		if(!activated)
			setImage(activeImg);
	}

	@Override
	public void onLeave()
	{
		if(!activated)
			setImage(inactiveImg);
	}

	@Override
	public void onLeftDown(int relativeX, int relativeY)
	{
		if(!activated)
			activated = true;
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
}

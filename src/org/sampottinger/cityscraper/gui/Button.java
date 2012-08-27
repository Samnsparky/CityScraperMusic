package org.sampottinger.cityscraper.gui;

import java.awt.Graphics2D;
import java.io.IOException;

import org.phineas.contrib.PhineasSprite;
import org.phineas.core.PhineasClickListener;
import org.phineas.core.PhineasDrawable;
import org.phineas.core.PhineasHoverListener;

/**
 * Simple stateless button for Phineas games / components.
 * @author Sam Pottinger
 */
public class Button implements PhineasClickListener, PhineasDrawable,
		PhineasHoverListener
{
	private PhineasSprite normalSprite;
	private PhineasSprite hoverSprite;
	private boolean hovering;
	private boolean down;

	/**
	 * Creates a new simple button.
	 * @param newX The x position of the new button to create.
	 * @param newY The y position of the new button to create.
	 * @param newNormalLoc The location of the image to use for this button in
	 * 		its normal state.
	 * @param newHoverLoc The location of the image to use for this button when
	 * 		the user has his / her cursor over it.
	 * @throws IOException Raised if given file locations can not be openend.
	 */
	public Button(int newX, int newY, String newNormalLoc, String newHoverLoc)
			throws IOException
	{
		// TODO: Possible bug if user's cursor under new button
		hovering = false;
		
		normalSprite = new PhineasSprite(newX, newY, newNormalLoc);
		hoverSprite = new PhineasSprite(newX, newY, newHoverLoc);
	}

	@Override
	public int getX()
	{
		return getActiveSprite().getX();
	}

	@Override
	public int getY()
	{
		return getActiveSprite().getY();
	}

	@Override
	public int getWidth() {
		return getActiveSprite().getWidth();
	}

	@Override
	public int getHeight() {
		return getActiveSprite().getHeight();
	}

	@Override
	public void onLeftDown(int relativeX, int relativeY) {
		down = true;
	}

	@Override
	public void onLeftRelease(int relativeX, int relativeY) {
		down = false;
	}

	@Override
	public void draw(Graphics2D target) {
		getActiveSprite().draw(target);
	}

	@Override
	public int getDepth() {
		return getActiveSprite().getDepth();
	}

	@Override
	public void onEnter() {
		hovering = true;
	}

	@Override
	public void onLeave() {
		hovering = false;
	}
	
	private PhineasSprite getActiveSprite()
	{
		if(!hovering || down)
			return normalSprite;
		else
			return hoverSprite;
	}
}

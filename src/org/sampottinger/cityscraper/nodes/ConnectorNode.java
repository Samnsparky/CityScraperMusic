package org.sampottinger.cityscraper.nodes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;

import org.phineas.contrib.PhineasLine;
import org.phineas.core.PhineasDrawable;
import org.sampottinger.cityscraper.workspace.WorkspaceElement;

/**
 * A workspace element that connects workspace elements.
 * @author Sam Pottinger
 */
public class ConnectorNode implements CityScraperNode
{
	private SimpleNodeRecordHelper recordHelper;
	private int x;
	private int y;
	private int width;
	private int height;
	private Collection<PhineasDrawable> drawableComponents;

	public ConnectorNode(int newX, int newY, int newWidth, int newHeight)
	{
		x = newX;
		y = newY;
		width = newWidth;
		height = newHeight;
		recordHelper = new SimpleNodeRecordHelper();
		drawableComponents = new ArrayList<PhineasDrawable>();
	}
	
	private void createDrawableComponents() {
		
		drawableComponents = new ArrayList<PhineasDrawable>();
		
		for(WorkspaceElement previous: getPreviousElements())
			createLineToCenter(previous);
		for(WorkspaceElement next: getNextElements())
			createLineToCenter(next);
	}

	private void createLineToCenter(WorkspaceElement other)
	{
		int centerX = getX() + getWidth() / 2;
		int centerY = getY() + getHeight() / 2;
		int minX = getX();
		int minY = getY();
		int maxX = minX + getWidth();
		int maxY = minY + getHeight();
		int otherMinX = other.getX();
		int otherMinY = other.getY();
		int otherMaxX = otherMinX + other.getWidth();
		int otherMaxY = otherMinY + other.getHeight();
		
		// Draw connection lines
		if(otherMaxX <= minX) // If left
			drawableComponents.add(new PhineasLine(getX(), centerY, centerX, centerY, Color.BLUE));
		else if(otherMinX >= maxX) // If right
			drawableComponents.add(new PhineasLine(getX() + getWidth(), centerY, centerX, centerY, Color.BLUE));
		else if(otherMinY <= minY) // If above
			drawableComponents.add(new PhineasLine(centerX, getY(), centerX, centerY, Color.BLUE));
		else if(otherMaxY >= maxY) // If below
			drawableComponents.add(new PhineasLine(centerX, getY() + getHeight(), centerX, centerY, Color.BLUE));
	}

	@Override
	public void addNext(WorkspaceElement lastElement)
	{
		recordHelper.addNext(lastElement);
		createDrawableComponents();
	}

	@Override
	public void removeNext(WorkspaceElement element)
	{
		recordHelper.removeNext(element);
		createDrawableComponents();
	}

	@Override
	public Iterable<WorkspaceElement> getNextElements()
	{
		return recordHelper.getNextElements();
	}
	
	@Override
	public void addPrevious(WorkspaceElement element)
	{
		recordHelper.addPrevious(element);
		createDrawableComponents();
	}

	@Override
	public void removePrevious(WorkspaceElement element)
	{
		recordHelper.removePrevious(element);
	}

	@Override
	public Iterable<WorkspaceElement> getPreviousElements()
	{
		return recordHelper.getPreviousElements();
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void draw(Graphics2D target) {
		for(PhineasDrawable drawable : drawableComponents)
			drawable.draw(target);
	}

	@Override
	public int getDepth() {
		return PhineasDrawable.DEFAULT_DEPTH;
	}
}

package org.sampottinger.cityscraper.gui;

import java.awt.Graphics2D;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasDrawable;
import org.phineas.core.PhineasGlobalMouseMovementListener;
import org.phineas.core.PhineasHoverListener;
import org.phineas.core.PhineasPlaceable;
import org.sampottinger.cityscraper.gui.nodeselection.NodeTypeSelectorGUI;
import org.sampottinger.cityscraper.nodes.CityScraperNodePrototype;

/**
 * Hoverable region where new nodes can be placed
 * @author Sam Pottinger
 */
public class WorkspaceRegion<T extends PhineasPlaceable & PhineasBoundable & PhineasDrawable> 
	implements PhineasHoverListener, PhineasGlobalMouseMovementListener, PhineasDrawable
{
	
	public static final int DISCRETE_STEP = 16;
	
	private int x;
	private int y;
	private int previewX;
	private int previewY;
	private int width;
	private int depth;
	private int height;
	private NodeTypeSelectorGUI nodeTypeSelector;
	private boolean active;
	private T preview;
	
	/**
	 * Creates a new region in which new nodes can be placed by clicking
	 * @param newX The x position of this region's top left corner
	 * @param newY The y position of this region's top left corner
	 * @param newWidth The horizontal size of this region in pixels
	 * @param newHeight The vertical size of this region in pixels
	 * @param newNodeTypeSelector The node type selector that dictates what node type will be placed here
	 */
	public WorkspaceRegion(int newX, int newY, int newWidth, int newHeight, NodeTypeSelectorGUI newNodeTypeSelector)
	{
		x = newX;
		y = newY;
		width = newWidth;
		height = newHeight;
		nodeTypeSelector = newNodeTypeSelector;
		active = false;
		depth = PhineasDrawable.DEFAULT_DEPTH;
		preview = null;
		previewX = x;
		previewY = y;
	}
	
	/**
	 * Creates a new region in which new nodes can be placed by clicking
	 * @param newX The x position of this region's top left corner
	 * @param newY The y position of this region's top left corner
	 * @param newWidth The horizontal size of this region in pixels
	 * @param newHeight The vertical size of this region in pixels
	 * @param newNodeTypeSelector The node type selector that dictates what node type will be placed here
	 * @param newDepth The depth at which previews will be shown
	 */
	public WorkspaceRegion(int newX, int newY, int newWidth, int newHeight, 
			NodeTypeSelectorGUI newNodeTypeSelector, int newDepth)
	{
		x = newX;
		y = newY;
		width = newWidth;
		height = newHeight;
		nodeTypeSelector = newNodeTypeSelector;
		active = false;
		depth = newDepth;
		previewX = x;
		previewY = y;
	}
	
	/**
	 * Make the provided continuous position fit on a discrete step on this region
	 * @param position The continuous position to make discrete
	 * @param discreteStep The level of granularity (number of pixels in a "step"
	 *                     in the target number system)
	 * @param minRegion The min value that the given continuous position can be while
	 *                  still being in this region (if the given position val is on the
	 *                  x axis, this should be the x position of the left side of this
	 *                  region)
	 * @return Continuous position provided projected into the discrete number system
	 */
	private int putOnDiscreteStep(int position, int minRegion)
	{
		return position - ((position - minRegion) % DISCRETE_STEP);
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
		return width;
	}

	@Override
	public int getHeight()
	{
		return height;
	}

	@Override
	public void onEnter() 
	{
		active = true;
	}

	@Override
	public void onLeave() 
	{
		active = false;
	}

	@Override
	public void onMouseMove(int newX, int newY)
	{
		// Ignore if not active
		if(!active)
			return;
		
		// Otherwise update preview coordinates
		previewX = putOnDiscreteStep(newX, x);
		previewY = putOnDiscreteStep(newY, y);
	}
	
	@Override
	public void draw(Graphics2D target) 
	{
		CityScraperNodePrototype currentPrototype = nodeTypeSelector.getSelectedPrototype();
		
		// End if there is nothing selected
		if(currentPrototype == null)
			return;
		
		// Get preview
		preview = currentPrototype.getPreview();
				
		// If a preview is available, draw it
		if(active && preview != null)
		{
			preview.setX(previewX);
			preview.setY(previewY);
			preview.draw(target);
		}
	}

	@Override
	public int getDepth() 
	{
		return depth;
	}
}

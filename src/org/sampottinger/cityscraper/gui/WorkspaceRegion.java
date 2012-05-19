package org.sampottinger.cityscraper.gui;

import java.awt.Graphics2D;
import java.io.IOException;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasClickListener;
import org.phineas.core.PhineasDrawable;
import org.phineas.core.PhineasGlobalMouseMovementListener;
import org.phineas.core.PhineasHoverListener;
import org.phineas.core.PhineasPlaceable;
import org.sampottinger.cityscraper.WorkspaceManager;
import org.sampottinger.cityscraper.gui.nodeselection.NodeTypeSelectorGUI;
import org.sampottinger.cityscraper.nodes.CityScraperNode;
import org.sampottinger.cityscraper.nodes.CityScraperNodePrototype;

/**
 * Hoverable region where new nodes can be placed
 * @author Sam Pottinger
 */
public class WorkspaceRegion<T extends PhineasPlaceable & PhineasBoundable & PhineasDrawable> 
	implements PhineasHoverListener, PhineasGlobalMouseMovementListener, PhineasDrawable,
	PhineasClickListener
{
	
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
	private WorkspaceManager workspaceManager;
	int xStep;
	int yStep;
	
	/**
	 * Creates a new region in which new nodes can be placed by clicking
	 * @param newX The x position of this region's top left corner
	 * @param newY The y position of this region's top left corner
	 * @param newWidth The horizontal size of this region in pixels
	 * @param newHeight The vertical size of this region in pixels
	 * @param newNodeTypeSelector The node type selector that dictates what node type will be placed here
	 * @param newWorkspaceManager Data structure containing the simulation grid for the workspace
	 * @param newXStep The horizontal size of the discrete spaces in the workspace (in pixels)
	 * @param newYStep The vertical size of the discrete spaces in the workspace (in pixels)
	 */
	public WorkspaceRegion(int newX, int newY, int newWidth, int newHeight, NodeTypeSelectorGUI newNodeTypeSelector,
			WorkspaceManager newWorkspaceManager, int newXStep, int newYStep)
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
		workspaceManager = newWorkspaceManager;
		xStep = newXStep;
		yStep = newYStep;
	}
	
	/**
	 * Creates a new region in which new nodes can be placed by clicking
	 * @param newX The x position of this region's top left corner
	 * @param newY The y position of this region's top left corner
	 * @param newWidth The horizontal size of this region in pixels
	 * @param newHeight The vertical size of this region in pixels
	 * @param newNodeTypeSelector The node type selector that dictates what node type will be placed here
	 * @param newWorkspaceManager Data structure containing the simulation grid for the workspace
	 * @param newXStep The horizontal size of the discrete spaces in the workspace (in pixels)
	 * @param newYStep The vertical size of the discrete spaces in the workspace (in pixels)
	 * @param newDepth The depth at which previews will be shown
	 */
	public WorkspaceRegion(int newX, int newY, int newWidth, int newHeight, 
			NodeTypeSelectorGUI newNodeTypeSelector, WorkspaceManager newWorkspaceManager, 
			int newXStep, int newYStep, int newDepth)
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
		xStep = newXStep;
		yStep = newYStep;
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
	private int putOnDiscreteStep(int position, int discreteStep, int minRegion)
	{
		return position - ((position - minRegion) % discreteStep);
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
		previewX = putOnDiscreteStep(newX, xStep, x);
		previewY = putOnDiscreteStep(newY, yStep, y);
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

	@Override
	public void onLeftDown(int relativeX, int relativeY)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLeftRelease(int relativeX, int relativeY)
	{
		CityScraperNodePrototype currentPrototype = nodeTypeSelector.getSelectedPrototype();
		CityScraperNode newNode;
		try {
			newNode = currentPrototype.createNode(previewX, previewY);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load node image.");
		}
		workspaceManager.createElement(newNode);
	}
}

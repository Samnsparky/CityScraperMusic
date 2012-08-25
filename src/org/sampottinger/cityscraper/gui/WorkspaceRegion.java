package org.sampottinger.cityscraper.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import org.phineas.contrib.PhineasLine;
import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasClickListener;
import org.phineas.core.PhineasDrawable;
import org.phineas.core.PhineasGlobalMouseMovementListener;
import org.phineas.core.PhineasHoverListener;
import org.phineas.core.PhineasLocateable;
import org.phineas.core.PhineasPlaceable;
import org.sampottinger.cityscraper.connection.NodePathFinder;
import org.sampottinger.cityscraper.gui.nodeselection.NodeTypeSelectorGUI;
import org.sampottinger.cityscraper.nodes.CityScraperNode;
import org.sampottinger.cityscraper.nodes.CityScraperNodePrototype;
import org.sampottinger.cityscraper.nodes.ConnectorNode;
import org.sampottinger.cityscraper.workspace.WorkspaceElement;
import org.sampottinger.cityscraper.workspace.WorkspaceManager;

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
	private int xStep;
	private int yStep;
	private boolean drawingLine;
	private WorkspaceElement startingElement;
	
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
	
	/**
	 * Create the currently selected node type at the preview coordinates
	 * @return Newly created element
	 */
	private CityScraperNode createPreviewElement()
	{
		CityScraperNodePrototype currentPrototype = nodeTypeSelector.getSelectedPrototype();
		CityScraperNode newNode;
		try { newNode = currentPrototype.createNode(previewX, previewY); } 
		catch (IOException e) { throw new RuntimeException("Failed to load node image."); }
		workspaceManager.createElement(newNode);
		return newNode;
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
		int previewMidX;
		int previewMidY;
		CityScraperNodePrototype currentPrototype = nodeTypeSelector.getSelectedPrototype();
		
		// End if there is nothing selected
		if(currentPrototype == null)
			return;
		
		// Get preview
		preview = currentPrototype.getPreview();
		
		// Get midpoints
		previewMidX = previewX + preview.getWidth() / 2;
		previewMidY = previewY + preview.getHeight() / 2;
				
		// If a preview is available, draw it
		if(active && preview != null)
		{
			preview.setX(previewX);
			preview.setY(previewY);
			
			if(!workspaceManager.isOccupied(preview))
				preview.draw(target);
		}
		
		// Draw line if dragging line between nodes
		if(drawingLine)
		{
			PhineasLine line = new PhineasLine(
					startingElement.getX() + startingElement.getWidth() / 2,
					startingElement.getY() + startingElement.getHeight() / 2,
					previewMidX,
					previewMidY,
					Color.WHITE
			);
			line.draw(target);
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
		// If the gesture starts on an existing element, a line is being drawn
		// Otherwise an object is being placed
		drawingLine = !workspaceManager.isFree(previewX, previewY);
		
		if(drawingLine)
			startingElement = workspaceManager.getElementAt(previewX, previewY);
	}

	@Override
	public void onLeftRelease(int relativeX, int relativeY)
	{
		WorkspaceElement endElement;
		Iterable<PhineasLocateable> path;

		if(drawingLine)
		{
			drawingLine = false;
			PhineasBoundable stepBounds = workspaceManager.getStepBounds();

			// See if we are connecting with an existing element
			if(!workspaceManager.isOccupied(preview))
				endElement = createPreviewElement();
			else // Otherwise, create new element at position and connect
				endElement = workspaceManager.getElementAt(previewX, previewY);
			
			path = NodePathFinder.getInstance().findPath(startingElement, endElement, workspaceManager);
			
			if(path != null)
			{
				createPath(
						startingElement,
						endElement,
						path,
						stepBounds.getWidth(),
						stepBounds.getHeight()
				);
			}
		}
		else
		{
			if(!workspaceManager.isOccupied(preview))
				createPreviewElement();
		}
	}

	private void createPath(WorkspaceElement start, WorkspaceElement end, Iterable<PhineasLocateable> pathLocations,
			int stepWidth, int stepHeight)
	{
		ConnectorNode newConnection;
		WorkspaceElement lastElement = start;
		
		for(PhineasLocateable curPos : pathLocations)
		{
			newConnection = new ConnectorNode(curPos.getX(), curPos.getY(), stepWidth, stepHeight);
			workspaceManager.createElement(newConnection);
			newConnection.addPrevious(lastElement);
			lastElement.addNext(newConnection);
			lastElement = newConnection;
		}
		
		end.addPrevious(lastElement);
		lastElement.addNext(end);
	}
}

package org.sampottinger.cityscraper.connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasLocateable;
import org.sampottinger.cityscraper.SimpleCoordinatePair;
import org.sampottinger.cityscraper.workspace.WorkspaceManager;

/**
 * Adapter to the DijkstraPathFinder that integrates the class with the rest of
 * CityScraper
 * @author Sam Pottinger
 */
public class NodePathFinder
{
	private static NodePathFinder instance = null;
	
	public static NodePathFinder getInstance()
	{
		if(instance == null)
			instance = new NodePathFinder();
		return instance;
	}
	
	private NodePathFinder() {}
	
	public Iterable<PhineasLocateable> findPath(PhineasBoundable startNode, PhineasBoundable endNode,
			WorkspaceManager workspace)
	{
		boolean isEnd;
		boolean isStart;
		PhineasLocateable location;
		Collection<SimpleCoordinatePair> endingLocations;
		Collection<SimpleCoordinatePair> startingLocations;
		List<DijkstraSpace<PhineasLocateable>> startingSpaces;
		DijkstraSpace<PhineasLocateable> newSpace;
		DijkstraSpace<PhineasLocateable> leftSpace;
		DijkstraSpace<PhineasLocateable> aboveSpace;
		DijkstraPathFinderResult<PhineasLocateable> possiblePath;
		DijkstraConnection<PhineasLocateable> newConnection;

		DijkstraPathFinderResult<PhineasLocateable> shortestPath = null;
		DijkstraSpace<PhineasLocateable> shortestPathStartingSpace = null;
		DijkstraPathFinder<PhineasLocateable> pathFinder = new DijkstraPathFinder<PhineasLocateable>();
		PhineasBoundable gridBounds = workspace.getBounds();
		PhineasBoundable stepBounds = workspace.getStepBounds();
		int minX = gridBounds.getX();
		int minY = gridBounds.getY();
		int maxX = gridBounds.getWidth() + minX;
		int maxY = gridBounds.getHeight() + minY;
		int xStep = stepBounds.getWidth();
		int yStep = stepBounds.getHeight();
		int numXSpaces = (maxX - minX) / xStep;
		int numYSpaces = (maxY - minY) / yStep;
		
		// Find possible ending points
		endingLocations = workspace.getSpacesOccupiedBy(endNode);

		// Find possible starting points
		startingLocations = workspace.getSpacesOccupiedBy(startNode);
		startingSpaces = new ArrayList<DijkstraSpace<PhineasLocateable>>();
		
		// Create spaces
		List<DijkstraSpace<PhineasLocateable>> spaces = new ArrayList<DijkstraSpace<PhineasLocateable>>();
		for(int y=0; y<numYSpaces; y++)
		{
			for(int x=0; x<numXSpaces; x++)
			{
				location = new SimpleCoordinatePair(x * xStep, y * yStep);
				isStart = startingLocations.contains(location);
				isEnd = endingLocations.contains(location);
				if(isStart || isEnd || workspace.isFree(location.getX(), location.getY()))
				{
					newSpace = new DijkstraSpace<PhineasLocateable>(location, isEnd);
					spaces.add(newSpace);
					
					// Check for starting space
					if(isStart)
						startingSpaces.add(newSpace);
					
					// Connect to previous spaces
					// Connect left
					if(x > 0)
					{
						leftSpace = spaces.get(y * numXSpaces + x - 1);
						if(leftSpace != null)
						{
							newConnection = new DijkstraConnection<PhineasLocateable>(
									leftSpace,
									newSpace,
									1,
									true
							);
							newSpace.addConnection(newConnection);
							leftSpace.addConnection(newConnection);
						}
					}
					
					// Connect above
					if(y > 0)
					{
						aboveSpace = spaces.get((y - 1) * numXSpaces + x);
						if(aboveSpace != null)
						{
							newConnection = new DijkstraConnection<PhineasLocateable>(
									aboveSpace,
									newSpace,
									1,
									true
							);
							newSpace.addConnection(newConnection);
							aboveSpace.addConnection(newConnection);
						}
					}
				}
				else
					spaces.add(null);
			}
		}
		
		// Find shortest path
		for(DijkstraSpace<PhineasLocateable> startingSpace : startingSpaces)
		{
			// Reset spaces
			for(DijkstraSpace<PhineasLocateable> space: spaces)
			{
				if(space != null)
					space.reset();
			}

			// Try starting space and find possible path
			possiblePath = pathFinder.findPath(startingSpace);
			if(possiblePath.wasSuccessful() && (shortestPath == null || shortestPath.getLength() > possiblePath.getLength()))
			{
				shortestPath = possiblePath;
				shortestPathStartingSpace = startingSpace;
			}
		}
		
		if(shortestPath == null)
			return null;
		
		// Peel out the shortest path 
		return new NodePathIterable(shortestPath.getPath(), shortestPathStartingSpace);
	}
}

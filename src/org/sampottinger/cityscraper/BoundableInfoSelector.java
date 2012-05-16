package org.sampottinger.cityscraper;

import org.phineas.core.PhineasBoundable;

/**
 * A decorator that provides access to a specific attribute of 
 * boundable objects
 * 
 * A selector that, given an axis, decorates a boundable object 
 * and provides an interface to the bounds for
 * that object in that originally selected direction
 * 
 * @author Sam Pottinger
 */
public class BoundableInfoSelector
{
	public static enum Axis {X_AXIS, Y_AXIS};
	
	private Axis targetAxis;
	private PhineasBoundable targetBoundable;
	
	/**
	 * Creates a new boundable info selector decorator around the given boundable
	 * @param newTargetAxis The axis to select for
	 * @param newTargetBoundable The boundable to decorate
	 */
	public BoundableInfoSelector(Axis newTargetAxis, PhineasBoundable newTargetBoundable)
	{
		targetAxis = newTargetAxis;
		targetBoundable = newTargetBoundable;
	}
	
	/**
	 * Get the lower bound on the selected axis
	 * @return The lower coordinate of the bounding box on this decorator's 
	 * selected axis
	 */
	public int getLowerBound()
	{
		if(targetAxis == Axis.X_AXIS)
			return targetBoundable.getX();
		else
			return targetBoundable.getY();
	}
	
	/**
	 * Get the lower bound on the selected axis
	 * @return The higher coordinate of the bounding box on this decorator's 
	 * selected axis
	 */
	public int getUpperBound()
	{
		if(targetAxis == Axis.X_AXIS)
			return targetBoundable.getX() + targetBoundable.getWidth();
		else
			return targetBoundable.getY() + targetBoundable.getHeight();
	}
}

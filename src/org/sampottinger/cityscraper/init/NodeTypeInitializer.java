package org.sampottinger.cityscraper.init;

import java.io.IOException;

import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.gui.ToggleButton;
import org.sampottinger.cityscraper.gui.nodeselection.NodeTypeSelectorStateManager;

/**
 * Interface for factories that produce node prototypes and their selection
 * toggle buttons.
 * @author Sam Pottinger
 */
public interface NodeTypeInitializer
{
	/**
	 * Create fixed set of logic toggle buttons.
	 * @param x The x position where the left of the buttons should be aligned.
	 * @param y The y position of the upper left corner of the first button.
	 * @param verticalPadding The extra spacing to put between buttons.
	 * @param bgDepth The depth at which the backgrounds of the buttons should
	 * 		be drawn.
	 * @param fgDepth The depth at which the foregrounds of the buttons should
	 * 		be drawn.
	 * @param selectorManager The manager that should watch the state of this
	 * 		node's selection button and integrate it into a larger toggle group.
	 * @return Iterable over newly created buttons
	 * @throws IOException Thrown if the file system fails to load an image
	 * 		file.
	 * @throws PhineasException Thrown if an image is corrupt.
	 */
	public Iterable<ToggleButton> createAndRegisterButtons(int x, int y,
			int verticalPadding, int bgDepth, int fgDepth,
			NodeTypeSelectorStateManager selectorManager)
			throws IOException, PhineasException;
}

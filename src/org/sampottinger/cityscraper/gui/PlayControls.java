package org.sampottinger.cityscraper.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.phineas.core.PhineasCompoundGameObject;

/**
 * Controls for playing a graph.
 * @author Sam Pottinger
 */
public class PlayControls implements PhineasCompoundGameObject
{
	private Button playButton;
	private List<Object> components;
	private static final String PLAY_NORMAL_IMG_LOC =
			"img/button_play.png";
	private static final String PLAY_HOVER_IMG_LOC =
			"img/button_play_hover.png";
	
	public PlayControls(int x, int y)
	{
		// Load buttons
		try {
			playButton = new Button(
					x,
					y,
					PLAY_NORMAL_IMG_LOC,
					PLAY_HOVER_IMG_LOC
			);
			/*
			stopButton = new Button(
					x,
					y,
					STOP_NORMAL_IMG_LOC,
					STOP_HOVER_IMG_LOC
			);*/
		} catch (IOException e) {
			throw new RuntimeException(
					"Failed to create play controls: %s",
					e
			);
		}
		
		// Create components collection
		components = new ArrayList<Object>();
		components.add(playButton);
	}

	@Override
	public Iterable<Object> getComponents()
	{
		return components;
	}
}

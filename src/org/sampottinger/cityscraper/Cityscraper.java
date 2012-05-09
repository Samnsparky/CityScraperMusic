package org.sampottinger.cityscraper;
import java.io.IOException;

import org.phineas.core.GameFacade;
import org.phineas.core.PhineasException;
import org.sampottinger.cityscraper.TufteSlider.SliderDirection;

/**
 * Main executable class for the Cityscraper game
 * @author Sam Pottinger
 */
public class Cityscraper {
	
	private static final int WIDTH = 700;
	private static final int HEIGHT = 700;
	private static final int MIN_SPEED = 0;
	private static final int MAX_SPEED = 100;
	
	public static void main(String [] args)
	{
		SpeedController speedController;
		TufteSlider speedSlider;
		
		// Create high level game facade
		GameFacade game = GameFacade.getInstance();
		
		// Resize game window
		game.setDimensions(WIDTH, HEIGHT); 
		
		// Create speed slider
		speedController = new SpeedController(MIN_SPEED, MAX_SPEED);
		speedSlider = new TufteSlider(speedController, 100, 10, 200, 10, SliderDirection.VERTICAL, true);
		
		// Start game
		try 
		{
			game.addEntity(speedSlider);
			game.startGame();
		}
		catch (PhineasException e)
		{
			System.out.println("Failed to start game because of Phineas errror: " + e);
		}
		/*catch (IOException e) 
		{
			System.out.println("Failed to start game because of IO error: " + e);
		}*/
	}

}

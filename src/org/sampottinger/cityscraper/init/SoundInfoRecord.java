package org.sampottinger.cityscraper.init;

/**
 * Simple data transfer record for an available sound clip.
 * @author Sam Pottinger
 */
public class SoundInfoRecord implements Comparable<SoundInfoRecord>
{
	private String displayName;
	private String imageLoc;
	private String soundLoc;
	
	/**
	 * Create a new sound info DTO.
	 * @param newDisplayName The name of the sound as it should be shown to
	 * 		the user.
	 * @param newImageLoc The location of the image for the sound's icon.
	 * @param newSoundLoc The location of the actual sound.
	 */
	public SoundInfoRecord(String newDisplayName, String newImageLoc, String newSoundLoc)
	{
		displayName = newDisplayName;
		imageLoc = newImageLoc;
		soundLoc = newSoundLoc;
	}
	
	/**
	 * Get the human readable name of this sound.
	 * @return Human readable name for this sound.
	 */
	public String getDisplayName()
	{
		return displayName;
	}
	
	/**
	 * Get the location of the image to represent this sound.
	 * @return Path suitable for PhineasSprite.
	 */
	public String getImageLoc()
	{
		return imageLoc;
	}
	
	/**
	 * Get the location of the actual sound.
	 * @return Location of this sound's wav file.
	 */
	public String getSoundLoc()
	{
		return soundLoc;
	}

	@Override
	public int compareTo(SoundInfoRecord other)
	{
		return getDisplayName().compareTo(other.getDisplayName());
	}
}

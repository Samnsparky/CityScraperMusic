package org.sampottinger.cityscraper.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.yaml.snakeyaml.Yaml;

/**
 * High level facade to file manipulation and parsing to load sound info.
 * @author Sam Pottinger
 */
public class SoundConfigurationLoader
{
	private static final String SOUND_CONFIG_FILE_LOC = "sound_nodes.yaml";
	private static final String SOUND_CONIFG_YAML_STRUCT_NAME = "sounds";
	private static final String NAME_FIELD = "name";
	private static final String IMAGE_FIELD = "image";
	private static final String SOUND_FIELD = "sound";
	
	private static SoundConfigurationLoader instance;

	/**
	 * Gets a shared instance of this file manipulation facade.
	 * @return Shared instance of this singleton.
	 */
	public static SoundConfigurationLoader getInstance() {
		if(instance == null)
			instance = new SoundConfigurationLoader();
		return instance;
	}
	
	private SoundConfigurationLoader()
	{}
	
	@SuppressWarnings("unchecked")
	public Iterable<SoundInfoRecord> getSoundInfo() throws IOException
	{
		BufferedReader input;
		Yaml yamlReader;
		Map<String, List<Map<String, String>>> parsedStruct;
		List<Map<String, String>> loadedSoundConfigs;
		Collection<SoundInfoRecord> parsedSoundDTOs;
		String name;
		String imageLoc;
		String soundLoc;
		
		parsedSoundDTOs = new TreeSet<SoundInfoRecord>();
		
		// Load file
		URL url = this.getClass().getClassLoader().getResource(
				SOUND_CONFIG_FILE_LOC);
		input = new BufferedReader(new InputStreamReader(url.openStream()));
		
		// Load YAML
	    yamlReader = new Yaml();
		parsedStruct = (Map<String, List<Map<String,String>>>) yamlReader.load(
				input);
		
		// Pull sounds
		loadedSoundConfigs = parsedStruct.get(SOUND_CONIFG_YAML_STRUCT_NAME);
		
		// Build DTOs
		for(Map<String,String> rawSoundInfo : loadedSoundConfigs)
		{
			name = rawSoundInfo.get(NAME_FIELD);
			imageLoc = rawSoundInfo.get(IMAGE_FIELD);
			soundLoc = rawSoundInfo.get(SOUND_FIELD);
			parsedSoundDTOs.add(new SoundInfoRecord(name, imageLoc, soundLoc));
		}
		
		return parsedSoundDTOs;
	}
}

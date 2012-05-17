package org.sampottinger.cityscraper.nodes;

import java.util.HashMap;
import java.util.Map;

import org.sampottinger.cityscraper.nodes.SpecialNodePrototype.SpecialNodeType;

/**
 * Singleton maintaining a mapping of special nodes to the image locations for those images
 * @author Sam Pottinger
 */
public class SpecialNodeImgLocResolver
{
	private static SpecialNodeImgLocResolver instance = null;
	
	private Map<SpecialNodeType, String> typeToLocMapping;
	
	private static final String ADD_ONE_IMG_LOC = "img/plus_1.png";
	private static final String EQUAL_GATE_IMG_LOC = "img/equal_gate.png";
	private static final String GREATER_GATE_IMG_LOC = "img/larger_gate.png";
	private static final String LESS_GATE_IMG_LOC = "img/less_gate.png";
	private static final String SPAWN_NODE_IMG_LOC = "img/start.png";
	private static final String SUB_ONE_IMG_LOC = "img/minus_1.png";
	
	/**
	 * Gets a shared instance of this factory
	 * @return Shared instance of this singleton
	 */
	public static SpecialNodeImgLocResolver getInstance()
	{
		if(instance == null)
			instance = new SpecialNodeImgLocResolver();
		return instance;
	}
	
	private SpecialNodeImgLocResolver()
	{
		typeToLocMapping = new HashMap<SpecialNodeType, String>();
		typeToLocMapping.put(SpecialNodeType.ADD_ONE, ADD_ONE_IMG_LOC);
		typeToLocMapping.put(SpecialNodeType.EQUAL_GATE, EQUAL_GATE_IMG_LOC);
		typeToLocMapping.put(SpecialNodeType.GREATER_GATE, GREATER_GATE_IMG_LOC);
		typeToLocMapping.put(SpecialNodeType.LESS_GATE, LESS_GATE_IMG_LOC);
		typeToLocMapping.put(SpecialNodeType.SPAWN_NODE, SPAWN_NODE_IMG_LOC);
		typeToLocMapping.put(SpecialNodeType.SUB_ONE, SUB_ONE_IMG_LOC);
	}
	
	/**
	 * Get the location for the given node type
	 * @param type The type of special node for which a location is being asked
	 * @return
	 */
	public String getLoc(SpecialNodeType type)
	{
		String retVal = typeToLocMapping.get(type);
		if(retVal == null)
			throw new RuntimeException("Invalid special node type provided");
		return retVal;
	}
}

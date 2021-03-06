package org.sampottinger.cityscraper.nodes;

import java.io.IOException;

import org.sampottinger.cityscraper.nodes.SpecialNodePrototype.SpecialNodeType;

public class GreaterGateNode extends SimpleGraphicalNode
{	
	public GreaterGateNode(int x, int y) throws IOException
	{
		super(x, y, SpecialNodeImgLocResolver.getInstance().getLoc(
				SpecialNodeType.GREATER_GATE));
	}
}

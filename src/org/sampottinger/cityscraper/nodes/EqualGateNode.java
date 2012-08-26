package org.sampottinger.cityscraper.nodes;

import java.io.IOException;

import org.sampottinger.cityscraper.nodes.SpecialNodePrototype.SpecialNodeType;

public class EqualGateNode extends SimpleGraphicalNode
{	
	public EqualGateNode(int x, int y) throws IOException
	{
		super(x, y, SpecialNodeImgLocResolver.getInstance().getLoc(
				SpecialNodeType.EQUAL_GATE));
	}
}

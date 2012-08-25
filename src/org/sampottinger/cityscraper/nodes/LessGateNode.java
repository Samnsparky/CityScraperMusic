package org.sampottinger.cityscraper.nodes;

import java.io.IOException;

import org.sampottinger.cityscraper.nodes.SpecialNodePrototype.SpecialNodeType;

public class LessGateNode extends SimpleGraphicalNode
{	
	public LessGateNode(int x, int y) throws IOException
	{
		super(x, y, SpecialNodeImgLocResolver.getInstance().getLoc(SpecialNodeType.LESS_GATE));
	}
}

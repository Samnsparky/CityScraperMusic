package org.sampottinger.cityscraper.nodes;

import java.io.IOException;

import org.sampottinger.cityscraper.nodes.SpecialNodePrototype.SpecialNodeType;

public class SubtractOneNode extends SimpleGraphicalNode
{	
	public SubtractOneNode(int x, int y) throws IOException
	{
		super(x, y, SpecialNodeImgLocResolver.getInstance().getLoc(
				SpecialNodeType.SUB_ONE));
	}
}

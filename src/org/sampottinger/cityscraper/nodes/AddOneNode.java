package org.sampottinger.cityscraper.nodes;

import java.io.IOException;

import org.sampottinger.cityscraper.nodes.SpecialNodePrototype.SpecialNodeType;

public class AddOneNode extends SimpleGraphicalNode
{	
	public AddOneNode(int x, int y) throws IOException
	{
		super(x, y, SpecialNodeImgLocResolver.getInstance().getLoc(SpecialNodeType.ADD_ONE));
	}
}

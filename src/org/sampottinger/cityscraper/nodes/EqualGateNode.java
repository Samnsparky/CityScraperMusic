package org.sampottinger.cityscraper.nodes;

import java.io.IOException;

import org.phineas.contrib.PhineasSprite;
import org.sampottinger.cityscraper.nodes.SpecialNodePrototype.SpecialNodeType;

public class EqualGateNode extends PhineasSprite implements CityScraperNode
{
	public EqualGateNode(int x, int y) throws IOException
	{
		super(x, y, SpecialNodeImgLocResolver.getInstance().getLoc(SpecialNodeType.EQUAL_GATE));
	}
}

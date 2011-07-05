package de.banapple.graphviz4java.command;

import org.junit.Assert;
import org.junit.Test;

public class CommandLocatorFactoryTest
{
	@Test
	public void testGetCommandLocator()
	{
		CommandLocatorFactory factory = new CommandLocatorFactory();
		CommandLocator commandLocator = factory.createCommandLocator();
		
		Assert.assertNotNull(commandLocator);
	}
	
}

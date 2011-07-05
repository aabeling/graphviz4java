package de.banapple.graphviz4java.command;

/**
 * Creates a {@link CommandLocator} for the current OS.
 * 
 * @author <a href="mailto:achimabeling@web.de">Achim Abeling</a>
 */
public class CommandLocatorFactory
{
	public CommandLocator createCommandLocator()
	{
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().contains("linux")) {
			return new LinuxCommandLocator();
		}
		
		throw new RuntimeException("no CommandLocator found for os: " + osName);
	}
}

package de.banapple.graphviz4java.command;

/**
 * Locates commands.
 * 
 * @author <a href="mailto:achimabeling@web.de">Achim Abeling</a>
 */
public interface CommandLocator
{
	/**
	 * Returns the complete path to the given command.
	 * 
	 * @param command
	 * @return
	 */
	String getCommandPath(String command);
}

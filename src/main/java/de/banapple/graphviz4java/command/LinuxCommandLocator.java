package de.banapple.graphviz4java.command;

import de.banapple.graphviz4java.process.ProcessExecutor;

/**
 * Uses the linux command "which" to get the complete path to commands.
 * @author <a href="mailto:achimabeling@web.de">Achim Abeling</a>
 */
public class LinuxCommandLocator 
	implements CommandLocator
{

	public String getCommandPath(String command)
	{
		ProcessExecutor executor = new ProcessExecutor();
		String commandPath = executor.exec("/usr/bin/which " +  command);
		if (commandPath.length() > 0) {
			return commandPath.trim();
		}
		
		throw new RuntimeException("command not found: " + command);
	}

}

package de.banapple.graphviz4java.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Uses the linux command "which" to get the complete path to commands.
 * @author <a href="mailto:achimabeling@web.de">Achim Abeling</a>
 */
public class LinuxCommandLocator 
	implements CommandLocator
{

	public String getCommandPath(String command)
	{
		try {
			Process process =
				Runtime.getRuntime().exec("/usr/bin/which " +  command);
			Reader r = new InputStreamReader(process.getInputStream());
		    BufferedReader in = new BufferedReader(r);
		    String line = null;
		    StringBuilder processOutput = new StringBuilder();
		    while((line = in.readLine()) != null) {
		    	processOutput.append(line);
		    }
		    in.close();
		    
		    if (processOutput.length() > 0) {
		    	return processOutput.toString().trim();
		    }
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		throw new RuntimeException("command not found: " + command);
	}

}

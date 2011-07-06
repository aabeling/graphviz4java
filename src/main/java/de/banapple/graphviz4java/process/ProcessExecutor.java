package de.banapple.graphviz4java.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ProcessExecutor
{
	/**
	 * Executes the given command and returns the output.
	 * 
	 * @param command
	 * @return
	 */
	public String exec(String command)
	{
		try {
			Process process =
				Runtime.getRuntime().exec(command);
			Reader r = new InputStreamReader(process.getInputStream());
		    BufferedReader in = new BufferedReader(r);
		    String line = null;
		    StringBuilder processOutput = new StringBuilder();
		    while((line = in.readLine()) != null) {
		    	processOutput.append(line).append("\n");
		    }
		    in.close();
		    
		    return processOutput.toString();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

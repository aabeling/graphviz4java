package de.banapple.graphviz4java.command;

public class LinuxCommandLocatorIntegration
{
	public static void main(String[] args)
	{
		LinuxCommandLocator locator = new LinuxCommandLocator();
		String path = locator.getCommandPath("dot");
		System.out.println(path);
	}
}

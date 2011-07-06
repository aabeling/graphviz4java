package de.banapple.graphviz4java.model;

public class Node
{
	private String name;
	private double width;
	private double height;
	
	public Node(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}
	
	/**
	 * A shorthand for {@link #setWidth(double)} and
	 * {@link #setHeight(double)}.
	 * 
	 * @param width
	 * @param height
	 */
	public void setDimensions(double width, double height)
	{
		this.width = width;
		this.height = height;
	}
}

package de.banapple.graphviz4java.layout;

import de.banapple.graphviz4java.model.Node;

public class NodeLayoutInfo
{
	private Node node;
	private Coordinate center;
	private double width;
	private double height;
	
	public Node getNode()
	{
		return node;
	}
	public void setNode(Node node)
	{
		this.node = node;
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
	
	public Coordinate getCenter()
	{
		return center;
	}
	
	public void setCenter(Coordinate center)
	{
		this.center = center;
	}
	
	@Override
	public String toString()
	{
		return node.getName()+" "+getCenter()+" "+getWidth()+" "+getHeight();
	}
}

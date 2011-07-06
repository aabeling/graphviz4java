package de.banapple.graphviz4java.layout;

import de.banapple.graphviz4java.model.Node;

public class EdgeLayoutInfo
{
	private Node source;
	private Node target;
	private Coordinate[] controlPoints;
	
	public Node getSource()
	{
		return source;
	}
	public void setSource(Node source)
	{
		this.source = source;
	}
	public Node getTarget()
	{
		return target;
	}
	public void setTarget(Node target)
	{
		this.target = target;
	}
	public Coordinate[] getControlPoints()
	{
		return controlPoints;
	}
	public void setControlPoints(Coordinate[] controlPoints)
	{
		this.controlPoints = controlPoints;
	}
	
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		result.append(getSource().getName())
			.append("->").append(getTarget().getName())
			.append(" ");
		for (int i = 0; i < controlPoints.length; i++) {
			result.append(controlPoints[i]).append(" ");			
		}
		return result.toString();
	}
}

package de.banapple.graphviz4java.model;

import java.util.HashSet;
import java.util.Set;

public class Graph
{
	private Set<Node> nodes;
	private Set<Edge> edges;
	
	public Set<Node> getNodes()
	{
		if (nodes == null) {
			nodes = new HashSet<Node>();
		}
		return nodes;
	}
	
	public void setNodes(Set<Node> nodes)
	{
		this.nodes = nodes;
	}
	
	public Set<Edge> getEdges()
	{
		if (edges == null) {
			edges = new HashSet<Edge>();
		}
		return edges;
	}
	
	public void setEdges(Set<Edge> edges)
	{
		this.edges = edges;
	}
	
	
}

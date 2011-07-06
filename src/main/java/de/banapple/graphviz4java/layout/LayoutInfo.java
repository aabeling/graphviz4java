package de.banapple.graphviz4java.layout;

import java.util.HashSet;
import java.util.Set;

public class LayoutInfo
{
	private GraphLayoutInfo graphInfo;
	private Set<NodeLayoutInfo> nodesInfo;
	private Set<EdgeLayoutInfo> edgesInfo;
	
	public GraphLayoutInfo getGraphInfo()
	{
		return graphInfo;
	}
	
	public void setGraphInfo(GraphLayoutInfo graphInfo)
	{
		this.graphInfo = graphInfo;
	}
	
	public Set<NodeLayoutInfo> getNodesInfo()
	{
		if (nodesInfo == null) {
			nodesInfo = new HashSet<NodeLayoutInfo>();
		}
		return nodesInfo;
	}
	
	public void setNodesInfo(Set<NodeLayoutInfo> nodesInfo)
	{
		this.nodesInfo = nodesInfo;
	}
	
	public Set<EdgeLayoutInfo> getEdgesInfo()
	{
		if (edgesInfo == null) {
			edgesInfo = new HashSet<EdgeLayoutInfo>();
		}
		return edgesInfo;
	}
	
	public void setEdgesInfo(Set<EdgeLayoutInfo> edgesInfo)
	{
		this.edgesInfo = edgesInfo;
	}
	
}

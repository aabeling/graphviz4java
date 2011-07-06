package de.banapple.graphviz4java.layout;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import de.banapple.graphviz4java.model.Edge;
import de.banapple.graphviz4java.model.Graph;
import de.banapple.graphviz4java.model.Node;

public class DotLayouterTest
{
	@Test
	public void testDoLayout()
	{
		/* prepare some nodes */
		Node node1 = new Node("a");
		node1.setDimensions(2.0, 1.0);
		Node node2 = new Node("b");
		node2.setDimensions(1.0, 1.0);
		Node node3 = new Node("c");
		node3.setDimensions(3.0, 1.0);
		Node node4 = new Node("d");
		node4.setDimensions(1.0, 1.0);
		Node node5 = new Node("e");
		node5.setDimensions(1.0, 1.0);
		
		/* some edges */
		Edge edge1 = new Edge(node1, node2);
		Edge edge2 = new Edge(node2, node3);
		Edge edge3 = new Edge(node3, node4);
		Edge edge4 = new Edge(node3, node5);
		Edge edge5 = new Edge(node5, node1);
		
		/* prepare the graph */
		Graph graph = new Graph();
		graph.getNodes().addAll(Arrays.asList(node1, node2, node3, node4, node5));
		graph.getEdges().addAll(Arrays.asList(edge1, edge2, edge3, edge4, edge5));
		
		DotLayouter layouter = new DotLayouter();
		LayoutInfo layoutInfo = layouter.doLayout(graph);
		Assert.assertNotNull(layoutInfo);
	}
}

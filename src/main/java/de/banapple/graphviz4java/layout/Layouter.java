package de.banapple.graphviz4java.layout;

import de.banapple.graphviz4java.model.Graph;

public interface Layouter
{
	LayoutInfo doLayout(Graph graph);
}

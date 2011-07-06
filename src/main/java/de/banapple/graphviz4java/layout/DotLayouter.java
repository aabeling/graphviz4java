package de.banapple.graphviz4java.layout;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.banapple.graphviz4java.model.Edge;
import de.banapple.graphviz4java.model.Graph;
import de.banapple.graphviz4java.model.Node;
import de.banapple.graphviz4java.process.ProcessExecutor;

public class DotLayouter 
	implements Layouter
{
	private static final Logger log = LoggerFactory.getLogger(DotLayouter.class);
	
	private static int nextNodeId;
	
	private ProcessExecutor processExecutor;
	
	public DotLayouter()
	{
		processExecutor = new ProcessExecutor();
	}
	
	public LayoutInfo doLayout(Graph graph)
	{
		Map<String, Node> nodeMap = new HashMap<String, Node>();
		String input = getInput(graph, nodeMap);
		log.debug("input = {}", input);
		
		File tmpFile = null;
		try {
			/* create a temporary file with the dot data */
			tmpFile = File.createTempFile("layouter", ".dot");
			FileWriter writer = new FileWriter(tmpFile);
			writer.write(input);
			writer.close();
			log.debug("temporary file written");

			/* execute dot */
			String command = "dot -Tplain " + tmpFile.getAbsolutePath();
			log.debug("command: {}", command);
			String output = processExecutor.exec(command);
			log.debug("result: {}", output);
			
			LayoutInfo result = parseLayoutInfo(output, nodeMap);
			log.debug("result = {}", result);
			
			return result;
		} catch (IOException e) {
			throw new RuntimeException("failed to create tmp file", e);
		} finally {
			if (tmpFile != null) {
				tmpFile.delete();
				log.debug("temporary file deleted");
			}
		}
		
	}

	/**
	 * See appendix E of the dot manual.
	 * 
	 * @param plainText
	 * @return
	 */
	private LayoutInfo parseLayoutInfo(
			String plainText, 
			Map<String, Node> nodeMap)
	{
		LayoutInfo result = new LayoutInfo();
		
		StringTokenizer tokenizer = new StringTokenizer(plainText,"\n");
		
		/* parse graph layout info */
		GraphLayoutInfo graphInfo = 
			parseGraphLayoutInfo(tokenizer);
		if (graphInfo == null) {
			throw new RuntimeException("unexpected output format");
		} else {
			result.setGraphInfo(graphInfo);
		}
		
		/* parse node layout info */
		NodeLayoutInfo nodeInfo = null;
		while ((nodeInfo = parseNodeLayoutInfo(tokenizer, nodeMap)) != null) {
			result.getNodesInfo().add(nodeInfo);
		}
		
		/* parse edge layout info */
		EdgeLayoutInfo edgeInfo = null;
		while ((edgeInfo = parseEdgeLayoutInfo(tokenizer, nodeMap)) != null) {
			result.getEdgesInfo().add(edgeInfo);
		}
		
		return result;
	}

	private EdgeLayoutInfo parseEdgeLayoutInfo(
			StringTokenizer tokenizer,
			Map<String, Node> nodeMap)
	{
		if (!tokenizer.hasMoreTokens()) {
			throw new RuntimeException("expected a line");
		}
		String line = tokenizer.nextToken();
		
		if (!line.startsWith("edge")) {
			return null;
		}
		
		StringTokenizer lineTokenizer = new StringTokenizer(line, " ");
		/* skip "edge" token */
		lineTokenizer.nextToken();
		
		EdgeLayoutInfo result = new EdgeLayoutInfo();
		String sourceId = lineTokenizer.nextToken();
		result.setSource(nodeMap.get(sourceId));
		String targetId = lineTokenizer.nextToken();
		result.setTarget(nodeMap.get(targetId));		
		int pointCount = Integer.parseInt(lineTokenizer.nextToken());
		Coordinate[] controlPoints = new Coordinate[pointCount];
		for (int i=0;i<pointCount;i++) {
			double x = Double.parseDouble(lineTokenizer.nextToken());
			double y = Double.parseDouble(lineTokenizer.nextToken());
			controlPoints[i] = new Coordinate(x, y);
		}
		result.setControlPoints(controlPoints);
		log.debug("edge layout info: {}", result);
		return result;
	}

	private NodeLayoutInfo parseNodeLayoutInfo(
			StringTokenizer tokenizer,
			Map<String, Node> nodeMap)
	{
		if (!tokenizer.hasMoreTokens()) {
			throw new RuntimeException("expected a line");
		}
		String line = tokenizer.nextToken();
		
		Pattern nodePattern = Pattern.compile("node (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) .*");
		Matcher matcher = nodePattern.matcher(line);
		if (matcher.matches()) {
			NodeLayoutInfo result = new NodeLayoutInfo();
			String name = matcher.group(1);
			double centerX = Double.parseDouble(matcher.group(2));
			double centerY = Double.parseDouble(matcher.group(3));
			double width = Double.parseDouble(matcher.group(4));
			double height = Double.parseDouble(matcher.group(5));
			result.setCenter(new Coordinate(centerX, centerY));
			result.setWidth(width);
			result.setHeight(height);
			result.setNode(nodeMap.get(name));
			log.debug("node layout info: {}", result);
			return result;
		}
		return null;
	}

	private GraphLayoutInfo parseGraphLayoutInfo(StringTokenizer tokenizer)
	{
		if (!tokenizer.hasMoreTokens()) {
			throw new RuntimeException("expected a line");
		}
		String line = tokenizer.nextToken();
		Pattern graphPattern = Pattern.compile("graph (\\S+) (\\S+) (\\S+)");
		Matcher matcher = graphPattern.matcher(line);
		if (matcher.matches()) {
			GraphLayoutInfo result = new GraphLayoutInfo();
			result.setWidth(Double.parseDouble(matcher.group(2)));
			result.setHeight(Double.parseDouble(matcher.group(3)));
			log.debug("graph layout info: {} {}", 
					result.getWidth(), result.getHeight());
			return result;
		}
		
		return null;
	}

	private String getInput(Graph graph, Map<String, Node> nodeMap)
	{
		StringBuilder result = new StringBuilder();
		
		Map<Node, String> nodeIdMap = new HashMap<Node, String>();
		
		result.append("digraph {\n");
		for (Node node : graph.getNodes()) {
			String nodeId = getNextNodeId();
			nodeMap.put(nodeId, node);
			nodeIdMap.put(node, nodeId);
			result.append("  ").append(nodeId).append(";\n");
		}
		for (Edge edge : graph.getEdges()) {
			String sourceId = nodeIdMap.get(edge.getSource());
			String targetId = nodeIdMap.get(edge.getTarget());
			result.append("  ").append(sourceId)
				.append(" -> ").append(targetId)
				.append(";\n");			
		}
		result.append("}");
		return result.toString();
	}
	
	private String getNextNodeId()
	{
		nextNodeId++;
		return "node"+nextNodeId;
	}

	private String getNodeId(Node node)
	{
		return "\"" + node.getName() + "\"";
	}
}

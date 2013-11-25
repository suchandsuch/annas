package annas.graph.generate;

import java.util.Map;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.VertexFactory;

public interface GraphGenerator<V, E extends EdgeInterface<V>> {

	public void generateGraph(GraphInterface<V, E> graph,
			VertexFactory<V> vertexFactory, Map<String, ?> Additionalinfo);
}

package annas.graph.generate;

import java.util.Map;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.VertexFactory;

/**
 * Generates an empty Graph @see <a
 * href="http://mathworld.wolfram.com/EmptyGraph.html"> shown here</a>
 * 
 * @author Sam Wilson
 * 
 * @param <V>
 *            Vertex type
 * @param <E>
 *            Edge type
 */
public class EmptyGraphGenerator<V, E extends EdgeInterface<V>> implements
		GraphGenerator<V, E> {

	/**
	 * Number of nodes in the target graph
	 */
	private int order;

	public EmptyGraphGenerator(int order) {
		super();
		if (order < 0) {
			throw new IllegalArgumentException("order must be >= 0");
		}
		this.order = order;
	}

	@Override
	public void generateGraph(GraphInterface<V, E> target,
			VertexFactory<V> vertexFactory, Map<String, ?> Additionalinfo) {
		if (target == null)
			return;

		for (int i = 0; i < this.order; i++)
			target.addVertex(vertexFactory.createVertex());

	}

}

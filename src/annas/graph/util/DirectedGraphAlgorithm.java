package annas.graph.util;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

/**
 * All algorithms that operate on directed graphs should extend this class.
 * 
 * @author scsswi
 * 
 * @param <V>
 *            Vertex type
 * @param <E>
 *            Edge type
 */
public class DirectedGraphAlgorithm<V, E extends EdgeInterface<V>> extends
		Algorithm<V, E> {

	public DirectedGraphAlgorithm(GraphInterface<V, E> graph) {
		super();
		if (!graph.isDirected()) {
			throw new IllegalArgumentException(
					"Algorithm incompatible with type " + graph.getClass()
							+ " derived from UndirectedGraph");
		}
	}

}

package annas.graph.generate;

import java.util.Map;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.VertexFactory;

/**
 * Generates a linear Graph, a straight line graph
 * 
 * @author Sam Wilson
 * 
 * @param <V>
 *            Vertex type
 * @param <E>
 *            Edge type
 */
public class LinearGraphGenerator<V, E extends EdgeInterface<V>> implements
		GraphGenerator<V, E> {

	/**
	 * Start vertex
	 */
	private V start_vertex;

	/**
	 * End vertex
	 */
	private V end_vertex;

	/**
	 * Number of vertexs in the target graph
	 */
	private int length;

	public LinearGraphGenerator(int length) {
		super();
		if (length < 0) {
			throw new IllegalArgumentException("order must be >= 0");
		}
		this.length = length;
	}

	/**
	 * @return the start_vertex
	 */
	public V getStart_vertex() {
		return start_vertex;
	}

	/**
	 * @return the end_vertex
	 */
	public V getEnd_vertex() {
		return end_vertex;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	@Override
	public void generateGraph(GraphInterface<V, E> target,
			VertexFactory<V> vertexFactory, Map<String, ?> Additionalinfo) {

		if (target == null)
			return;
		V newvertex = vertexFactory.createVertex();
		V tmp;

		this.start_vertex = newvertex;
		target.addVertex(newvertex);
		tmp = newvertex;
		for (int i = 0; i < this.length - 1; i++) {
			newvertex = vertexFactory.createVertex();
			target.addVertex(newvertex);
			target.addEdge(tmp, newvertex);
			tmp = newvertex;
		}
		this.end_vertex = tmp;

	}

}

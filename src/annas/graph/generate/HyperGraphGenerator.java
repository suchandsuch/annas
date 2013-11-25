package annas.graph.generate;

import java.util.ArrayList;
import java.util.Map;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.VertexFactory;

/**
 * Generates a Hyper Graph @see <a
 * href="http://mathworld.wolfram.com/Hypergraph.html"> shown here</a>
 * 
 * @author Sam Wilson
 * 
 * @param <V>
 *            Vertex type
 * @param <E>
 *            Edge type
 */
public class HyperGraphGenerator<V, E extends EdgeInterface<V>> implements
		GraphGenerator<V, E> {

	/**
	 * Number of dimensions
	 */
	private int dim;

	public HyperGraphGenerator(int dim) {
		super();
		if (dim < 0) {
			throw new IllegalArgumentException(
					"dimensions must be >= 0");
		}
		this.dim = dim;
	}

	@Override
	public void generateGraph(GraphInterface<V, E> target,
			VertexFactory<V> vertexFactory, Map<String, ?> Additionalinfo) {
		if (target == null)
			return;

		int order = (int) Math.pow(2, dim);
		ArrayList<V> vertices = new ArrayList<V>();
		for (int i = 0; i < order; i++) {
			V newnode = vertexFactory.createVertex();
			target.addVertex(newnode);
			vertices.add(newnode);

		}

		for (int i = 0; i < order; i++) {
			for (int j = i + 1; j < order; j++) {
				for (int z = 0; z < dim; z++) {
					if ((j ^ i) == (1 << z)) {
						target.addEdge(vertices.get(i), vertices.get(j));
						break;
					}
				}
			}
		}
	}

}

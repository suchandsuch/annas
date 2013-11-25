package annas.graph.generate;

import java.util.Collection;
import java.util.Map;
import java.util.Random;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.SimpleDirectedGraph;
import annas.graph.SimpleUndirectedGraph;
import annas.graph.VertexFactory;

public class RandomGraph<V, E extends EdgeInterface<V>> implements
		GraphGenerator<V, E> {

	private int order;

	private int size;

	public RandomGraph(int order, int size) {
		super();
		if (order < 0) {
			throw new IllegalArgumentException("order must be >= 0");
		}
		if (size < 0) {
			throw new IllegalArgumentException("size must be >= 0");
		}
		this.order = order;
		this.size = size;
	}

	@Override
	public void generateGraph(GraphInterface<V, E> graph,
			VertexFactory<V> vertexFactory, Map<String, ?> Additionalinfo) {
		int maxSize = Integer.MAX_VALUE;
		if (graph instanceof SimpleDirectedGraph) {
			maxSize = this.order * this.order - 1;
		} else if (graph instanceof SimpleUndirectedGraph) {
			maxSize = (this.order * (this.order - 1)) / 2;
		}

		if (this.size <= maxSize) {
			for (int i = 0; i < this.order; i++) {
				graph.addVertex(vertexFactory.createVertex());
			}
			while (graph.getSize() != this.size) {
				V head = randomSelect(graph.getVertices());
				V tail = randomSelect(graph.getVertices());
				graph.addEdge(tail, head);
			}
		} else {
			throw new IllegalArgumentException("size must be <= " + maxSize);
		}
	}

	@SuppressWarnings("unchecked")
	private V randomSelect(Collection<V> col) {
		Random r = new Random();
		return ((V[]) col.toArray())[r.nextInt(col.size())];
	}

}

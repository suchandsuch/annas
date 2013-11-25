package annas.graph.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.UndirectedGraph;
import annas.math.combinatorics.PowersetIterator;

public class InducedSubgraph {

	/**
	 * Returns the induced subgraph by given vertices. Reuses the vertex
	 * objects, but makes new edges.
	 * 
	 * @param graph
	 * @param vertices
	 * @return induced graph graph[vertices]
	 */
	public static <V, E extends EdgeInterface<V>> GraphInterface<V, E> inducedSubgraphOf(
			GraphInterface<V, E> graph, Collection<V> vertices) {
		
		if(!graph.getVertices().containsAll(vertices)){
			return null;
		}

		GraphInterface<V, E> h = new UndirectedGraph<V, E>(graph
				.getEdgeFactory());

		for (V n : vertices) {
			h.addVertex(n);
		}

		for (E e : graph.getEdges()) {
			V s = e.getTail();
			V t = e.getHead();
			if (h.containsVertex(s) && h.containsVertex(t)) {
				h.addEdge(s, t);
			}
		}

		return h;
	}

	/**
	 * Provides an iterator for induced subgraphs.
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @param poss
	 *            Set of vertices to include/exclude from the graph
	 * @return an iterator of graphs which contains the vertices (V \ poss)
	 *         union u where u \in powerset(poss).
	 */
	public static <V, E extends EdgeInterface<V>> Iterator<GraphInterface<V, E>> inducedSubgraphIterator(
			final GraphInterface<V, E> graph, final List<V> poss) {
		return new Iterator<GraphInterface<V, E>>() {

			PowersetIterator<V> subsets = new PowersetIterator<V>(poss);

			@Override
			public boolean hasNext() {
				return subsets.hasNext();
			}

			@Override
			public GraphInterface<V, E> next() {
				Collection<V> vertices = subsets.next();
				Set<V> n =  graph.getVertices();
				n.removeAll(poss);
				n.addAll(vertices);
				return inducedSubgraphOf(graph, n);
			}

			@Override
			public void remove() {
			}
		};

	}

	/**
	 * Induced subgraph iterator
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @return an iterator over all induced subgraphs of a given graph
	 */
	public static <V, E extends EdgeInterface<V>> Iterator<GraphInterface<V, E>> inducedSubgraphIterator(
			final GraphInterface<V, E> graph) {
		return new Iterator<GraphInterface<V, E>>() {
			PowersetIterator<V> subsets = new PowersetIterator<V>(
					graph.getVertices());

			@Override
			public boolean hasNext() {
				return subsets.hasNext();
			}

			@Override
			public GraphInterface<V, E> next() {
				Collection<V> vertices = subsets.next();
				return inducedSubgraphOf(graph, vertices);
			}

			@Override
			public void remove() {
			}
		};
	}

}
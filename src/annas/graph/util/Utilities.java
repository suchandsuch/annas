package annas.graph.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import annas.graph.AbstractGraph;
import annas.graph.DirectedGraph;
import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.UndirectedGraph;
import annas.math.Matrix;
import annas.math.combinatorics.CombinatoricUtil;

public final class Utilities {

	/**
	 * Computes the complement of the graph, the vertices are reused.
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @return the complement of the given graph
	 */
	public <V, E extends EdgeInterface<V>> GraphInterface<V, E> getComplement(
			GraphInterface<V, E> graph) {
		// TODO
		return null;
	}
	
	/**
	 * Gets the adjacency matrix of the given graph.
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @return the adjacency matrix of the given graph
	 */
	public static <V, E extends EdgeInterface<V>> Matrix getAdjacencyMatrix(
			GraphInterface<V, ?> graph) {
		int order = graph.getOrder();
		double[][] adj = new double[order][order];

		Iterator<V> outer = graph.getVertices().iterator();
		Iterator<V> inner = null;
		for (int i = 0; outer.hasNext(); i++) {
			V vertex = outer.next();
			inner = graph.getVertices().iterator();
			for (int j = 0; inner.hasNext(); j++) {
				if (graph.getEdges(vertex, inner.next()).size() != 0) {
					adj[i][j] = 1;
				} else {
					adj[i][j] = 0;
				}
			}
		}

		return new Matrix(adj);
	}

	/**
	 * Gets the connected components of the graph.
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @return A collection of collections containing the vertices of the
	 *         connected components
	 */
	public static <V, E extends EdgeInterface<V>> Collection<Collection<V>> getConnectedComponents(
			GraphInterface<V, E> graph) {
		ArrayList<V> toVisit = new ArrayList<V>(graph.getVertices());

		ArrayList<Collection<V>> components = new ArrayList<Collection<V>>();

		Stack<V> stack = new Stack<V>();
		List<V> Visited = new ArrayList<V>();

		while (!toVisit.isEmpty()) {
			stack.push(toVisit.get(0));
			while (!stack.isEmpty()) {
				V current = stack.pop();
				Visited.add(current);
				toVisit.remove(current);

				Set<E> edges = graph.getEdges(current);

				for (E edge : edges) {
					V otherEnd = null;
					if (edge.getHead() == current) {
						otherEnd = edge.getTail();
					} else {
						otherEnd = edge.getHead();
					}
					if (!stack.contains(otherEnd) && toVisit.contains(otherEnd)) {
						stack.add(otherEnd);
					}
				}
			}
			components.add(Visited);
			Visited = new ArrayList<V>();
		}

		return components;
	}

	/**
	 * Gets the closed neighbourhood of a vertex.
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @param vertex
	 * @return The set of vertices adjacent to the given vertex (including the
	 *         given vertex).
	 */
	public static <V, E extends EdgeInterface<V>> Collection<V> getClosedNeighbourhood(
			GraphInterface<V, E> graph, V vertex) {
		Collection<V> vertices = Utilities.getOpenNeighbourhood(graph, vertex);
		vertices.add(vertex);

		return vertices;
	}

	/**
	 * Gets the closed neighbourhood of a vertex up to n distance from the
	 * central vertex
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph
	 *            Graph
	 * @param vertex
	 *            central vertex
	 * @param n
	 *            distance from central vertex
	 * @return Gets a collection of the vertices that are within a specified
	 *         distance from a central vertex including the central vertex.
	 */
	public static <V, E extends EdgeInterface<V>> Set<V> getClosedNNeighborhood(
			GraphInterface<V, E> graph, V vertex, int n) {
		Set<V> neighbors = new HashSet<V>(graph.getDegree(vertex));
		neighbors.add(vertex);
		for (int i = 0; i < n; i++) {
			Set<V> newneighbors = new HashSet<V>(graph.getVertices().size());
			for (V v : neighbors) {
				newneighbors.addAll(Utilities.getOpenNeighbourhood(graph, v));
			}
			neighbors.addAll(newneighbors);
		}
		return neighbors;
	}

	/**
	 * Gets the open neighbourhood of a given vertex.
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @param vertex
	 * @return A collection of vertices adjacent to a given vertex.
	 */
	public static <V, E extends EdgeInterface<V>> Collection<V> getOpenNeighbourhood(
			GraphInterface<V, E> graph, V vertex) {
		Collection<V> vertices = new LinkedHashSet<V>();
		Set<E> edges = graph.getEdges(vertex);

		for (E edge : edges) {
			if (edge.getHead() == vertex) {
				vertices.add(edge.getTail());
			} else {
				vertices.add(edge.getHead());
			}
		}

		return vertices;
	}

	/**
	 * Gets the closed neighbourhood of a vertex up to n distance from the
	 * central vertex
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph
	 *            Graph
	 * @param vertex
	 *            central vertex
	 * @param n
	 *            distance from central vertex
	 * @return Gets a collection of the vertices that are within a specified
	 *         distance from a central vertex.
	 */
	public static <V, E extends EdgeInterface<V>> Set<V> getOpenNNeighborhood(
			GraphInterface<V, E> graph, V vertex, int n) {
		Set<V> neighbors = new HashSet<V>(graph.getDegree(vertex));
		neighbors.add(vertex);
		for (int i = 0; i < n; i++) {
			Set<V> newneighbors = new HashSet<V>(graph.getVertices().size());
			for (V v : neighbors) {
				newneighbors.addAll(Utilities.getOpenNeighbourhood(graph, v));
			}
			neighbors.addAll(newneighbors);
		}
		neighbors.remove(vertex);
		return neighbors;
	}

	/**
	 * Gets the vertices of even degree.
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @return A collection of vertices of even degree.
	 */
	public static <V, E extends EdgeInterface<V>> Collection<V> getEvenDegreeVertices(
			GraphInterface<V, E> graph) {
		ArrayList<V> vertices = new ArrayList<V>();

		for (V vertex : graph.getVertices()) {
			if ((graph.getEdges(vertex).size() % 2) == 0) {
				vertices.add(vertex);
			}
		}
		return vertices;
	}

	/**
	 * Gets the vertices of odd degree.
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @return A collection of vertices of odd degree.
	 */
	public static <V, E extends EdgeInterface<V>> Collection<V> getOddDegreeVertices(
			GraphInterface<V, E> graph) {
		ArrayList<V> vertices = new ArrayList<V>();

		for (V vertex : graph.getVertices()) {
			if ((graph.getEdges(vertex).size() % 2) == 1) {
				vertices.add(vertex);
			}
		}
		return vertices;
	}

	/**
	 * Gets the vertices of a specified degree.
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @param degree
	 *            specified degree.
	 * @return A collection of vertices with a specified degree
	 */
	public static <V, E extends EdgeInterface<V>> Collection<V> getVerticesOfDegree(
			GraphInterface<V, E> graph, int degree) {
		ArrayList<V> vertices = new ArrayList<V>();

		for (V vertex : graph.getVertices()) {
			if ((graph.getEdges(vertex).size()) == degree) {
				vertices.add(vertex);
			}
		}
		return vertices;
	}

	/**
	 * Gets the minimum degree
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @return minimum degree
	 */
	public static <V, E extends EdgeInterface<V>> int getMinimumDegree(
			GraphInterface<V, E> graph) {

		int degree = Integer.MAX_VALUE;

		for (V vertex : graph.getVertices()) {
			degree = Math.min(degree, graph.getEdges(vertex).size());
		}
		return degree;
	}

	/**
	 * Gets the maximum degree
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @return maximum degree
	 */
	public static <V, E extends EdgeInterface<V>> int getMaximumDegree(
			GraphInterface<V, E> graph) {

		int degree = 0;

		for (V vertex : graph.getVertices()) {
			degree = Math.max(degree, graph.getEdges(vertex).size());
		}
		return degree;
	}

	/**
	 * Gets the diameter of the graph
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @return diameter of the given graph
	 */
	public static <V, E extends EdgeInterface<V>> int getDiameter(
			GraphInterface<V, E> graph) {
		// TODO CHECK THIS, should use adjacency instead of weighted edges.
		Floyd<V, E> f = new Floyd<V, E>(graph);
		double[][] d = f.getD();
		int max = 0;
		for (int i = 0; i < d.length; i++) {
			for (int j = 0; j < i; j++) {
				if (d[i][j] > max) {
					max = (int) d[i][j];
				}
			}
		}
		return max;

	}

	/**
	 * Gets the the graph has no edges.
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @return true if the graph is empty, false otherwise.
	 */
	public static <V, E extends EdgeInterface<V>> boolean isEmpty(
			GraphInterface<V, E> graph) {
		return graph.getEdges().size() == 0;
	}

	public static <V, E extends EdgeInterface<V>> GraphInterface<V, E> constructPowerGraph(
			GraphInterface<V, E> graph, int n) {

		UndirectedGraph<V, E> retval = new UndirectedGraph<V, E>(graph
				.getEdgeFactory());
		for (V v : graph.getVertices()) {
			retval.addVertex(v);
		}

		for (V v : graph.getVertices()) {
			Set<V> neigs = Utilities.getOpenNNeighborhood(graph, v, n);
			for (V u : neigs) {
				if (!retval.containsEdge(v, u)) {
					retval.addEdge(v, u);
				}
			}
		}

		return retval;
	}

	/**
	 * Gets the density of the graph. The density of a graph is the number of
	 * edge over the maximum number of edges in a graph of the same number of
	 * vertices.
	 * 
	 * @param <V>
	 * @param <E>
	 * @param graph 
	 * @return density of a given graph
	 */
	public static <V, E extends EdgeInterface<V>> double getDensity(
			GraphInterface<V, E> graph) {
		if (((AbstractGraph<V, E>) graph).allowMultipleEdges()) {
			throw new IllegalArgumentException("Graph allows multiple edge");
		}
		if (graph instanceof DirectedGraph) {
			return graph.getSize()
					/ Math.pow(2, CombinatoricUtil
							.nChooseK(graph.getOrder(), 2));
		}

		if (graph instanceof UndirectedGraph) {
			return graph.getSize() / graph.getOrder() * (graph.getOrder() - 1)
					/ 2;
		}

		return -1;

	}

	/**
	 * Gets the average degree of the graph.
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @return the mean degree of the graph
	 */
	public static <V, E extends EdgeInterface<V>> double getAverageDegree(
			GraphInterface<V, E> graph) {
		long sumDegree = 0;
		for (V v : graph.getVertices()) {
			sumDegree += graph.getDegree(v);
		}

		return sumDegree / graph.getOrder();
	}

	/**
	 * Gets a histogram of the degrees of the graph.
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @return array of longs where the degree is the index.
	 */
	public static <V, E extends EdgeInterface<V>> long[] getDegreeHistograph(
			GraphInterface<V, E> graph) {
		long[] degrees = new long[Utilities.getMaximumDegree(graph) + 1];
		for (V v : graph.getVertices()) {
			degrees[graph.getDegree(v)] += 1;
		}
		return degrees;

	}

	/**
	 * Gets the degree sequence of the graph
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @return an unsorted degree sequence.
	 */
	public static <V, E extends EdgeInterface<V>> long[] getDegreeSequence(
			GraphInterface<V, E> graph) {
		long[] degrees = new long[graph.getOrder()];
		int i = 0;
		for (V v : graph.getVertices()) {
			degrees[i] = graph.getDegree(v);
			i++;
		}
		return degrees;

	}

	/**
	 * Checks if a set of vertices is an independent set.
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @param vertices
	 * @return true if the set is independent, false otherwise.
	 */
	public static <V, E extends EdgeInterface<V>> boolean isIndependentSet(
			GraphInterface<V, E> graph, Set<V> vertices) {

		for (V v1 : vertices) {
			for (V v2 : vertices) {
				if (v1 != v2) {
					if (graph.containsEdge(v1, v2)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * Checks if a set of vertices is an independent set.
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @param vertices
	 * @return true if the set is independent, false otherwise.
	 */
	@SuppressWarnings("unchecked")
	public static <V, E extends EdgeInterface<V>> boolean isIndependentSet(
			GraphInterface<V, E> graph,V...vertices) {

		for (V v1 : vertices) {
			for (V v2 : vertices) {
				if (v1 != v2) {
					if (graph.containsEdge(v1, v2)) {
						return false;
					}
				}
			}
		}

		return true;
	}
	
	/**
	 * Checks if a set of vertices is a clique.
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @param vertices
	 * @return true if the set is a clique, false otherwise.
	 */
	public static <V, E extends EdgeInterface<V>> boolean isClique(
			GraphInterface<V, E> graph, Set<V> vertices) {

		for (V v1 : vertices) {
			for (V v2 : vertices) {
				if (v1 != v2) {
					if (!graph.containsEdge(v1, v2)) {
						return false;
					}
				}
			}
		}

		return true;
	}
	
	/**
	 * Checks if a set of vertices is a clique.
	 * @param <V>
	 * @param <E>
	 * @param graph
	 * @param vertices
	 * @return true if the set is a clique, false otherwise.
	 */
	@SuppressWarnings("unchecked")
	public static <V, E extends EdgeInterface<V>> boolean isClique(
			GraphInterface<V, E> graph,V...vertices) {

		for (V v1 : vertices) {
			for (V v2 : vertices) {
				if (v1 != v2) {
					if (!graph.containsEdge(v1, v2)) {
						return false;
					}
				}
			}
		}

		return true;
	}
}

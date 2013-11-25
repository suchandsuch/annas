package annas.graph.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import annas.graph.DefaultWeightedEdge;
import annas.graph.DirectedGraph;
import annas.graph.EdgeInterface;
import annas.graph.UndirectedGraph;
import annas.graph.WeightedEdgeInterface;
import annas.graph.classifier.BipartiteRecogniser;
import annas.graph.classifier.Classifier;

public class BipartiteMatching<V, E extends EdgeInterface<V>> {

	private UndirectedGraph<V, E> graph;

	private List<V> x;

	private List<V> y;

	public BipartiteMatching(UndirectedGraph<V, E> graph) throws Exception {
		super();
		Classifier<V, E> c = new BipartiteRecogniser<V, E>();
		if (!c.classify(graph)) {
			throw new Exception("Graph is not bipartite");
		}
		this.graph = graph;
		this.x = new ArrayList<V>();
		this.y = new ArrayList<V>();
		partition();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<E> getMatching() {
		DirectedGraph g = new DirectedGraph(DefaultWeightedEdge.class);
		Object src = new Object();
		Object sink = new Object();
		g.addVertex(src);
		g.addVertex(sink);
		for (V v : graph.getVertices()) {
			g.addVertex(v);
		}

		WeightedEdgeInterface<V> e;
		for (V v : x) {
			e = (DefaultWeightedEdge<V>) g.addEdge(src, v);
			e.setWeight(1d);
		}
		System.out.println(g.getSize());
		for (V v : y) {
			e = (DefaultWeightedEdge<V>) g.addEdge(v, sink);
			e.setWeight(1d);
		}
		System.out.println(g.getSize());
		for (E f : graph.getEdges()) {
			if (x.contains(f.getTail()) && y.contains(f.getHead())) {
				e = (DefaultWeightedEdge<V>) g.addEdge(f.getTail(), f.getHead());
				e.setWeight(1);
			} else if (x.contains(f.getHead()) && y.contains(f.getTail())) {
				e = (DefaultWeightedEdge<V>) g.addEdge(f.getHead(), f.getTail());
				e.setWeight(1);
			}
		}

		System.out.println(g.getSize());

		FordFulkerson ff = new FordFulkerson(g, src, sink);

		System.out.println(ff.getMaximumFlow());

		ArrayList<E> edges = new ArrayList<E>();

		for (Object o : g.getEdges()) {
			WeightedEdgeInterface<V> ee = (WeightedEdgeInterface<V>) o;
			if (ff.getFlow(ee) == 1
					&& (ee.getHead() != src && ee.getTail() != sink
							&& ee.getHead() != sink && ee.getTail() != src)) {
				edges.add(graph.getEdges(ee.getTail(), ee.getHead()).iterator()
						.next());
			}
		}
		return edges;
	}

	private void partition() {
		Set<V> n = new TreeSet<V>(graph.getVertices());
		while (!n.isEmpty()) {
			V v = n.iterator().next();
			if (!x.contains(v) && !y.contains(v)) {
				y.add(v);
				n.remove(v);
			}
			dfsX(v, n);
		}
		System.out.println(x);
		System.out.println(y);
	}

	private void dfsX(V v, Set<V> n) {
		for (E e : graph.getEdges(v)) {
			V u = e.getOtherEndpoint(v);
			if (!x.contains(u) && !y.contains(u)) {
				n.remove(u);
				x.add(u);
				dfsY(u, n);
			}
		}
	}

	private void dfsY(V v, Set<V> n) {
		for (E e : graph.getEdges(v)) {
			V u = e.getOtherEndpoint(v);
			if (!y.contains(u) && !x.contains(u)) {
				n.remove(u);
				y.add(u);
				dfsX(u, n);
			}
		}
	}

}

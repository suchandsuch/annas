package annas.graph.util;

import java.util.Comparator;
import java.util.PriorityQueue;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.UndirectedGraph;
import annas.graph.WeightedEdgeInterface;

public class Prim<V, E extends EdgeInterface<V>> implements
		MinimumSpanningTree<V, E> {

	private final double DEFAULT_EDGE_WEIGHT = 1d;

	private GraphInterface<V, E> graph;

	private PriorityQueue<E> EdgeList;

	public Prim(GraphInterface<V, E> graph) {
		super();
		this.graph = graph;
		this.EdgeList = new PriorityQueue<E>(graph.getSize(),
				new DoubleFieldComparator());
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GraphInterface<V, E> execute() {
		GraphInterface<V, E> tree = new UndirectedGraph(
				this.graph.getEdgeClass());

		V vertex = this.graph.getVertices().iterator().next();
		tree.addVertex(vertex);
		this.EdgeList.addAll(this.graph.getEdges(vertex));

		while (tree.getOrder() != this.graph.getOrder()
				&& !this.EdgeList.isEmpty()) {
			E edge = this.EdgeList.poll();
			V v = this.getOtherEnd(tree, edge);
			System.out.println(v);
			if (!tree.containsVertex(v)) {

				tree.addVertex(v);
				EdgeInterface<V> e = tree.addEdge(edge.getTail(),
						edge.getHead());
				if (edge instanceof WeightedEdgeInterface) {
					((WeightedEdgeInterface<V>) e)
							.setWeight(((WeightedEdgeInterface<V>) edge)
									.getWeight());
				}
				this.EdgeList.remove(edge);
				this.EdgeList.addAll(this.graph.getEdges(v));
			}
		}
		return tree;
	}

	private V getOtherEnd(GraphInterface<V, ?> g, E edge) {
		if (g.containsVertex(edge.getHead())) {
			return edge.getTail();
		} else {
			return edge.getHead();
		}
	}

	@Override
	public double getCost() {
		GraphInterface<?, E> graph = this.execute();
		double d = 0;
		for (E edge : graph.getEdges()) {
			if (edge instanceof WeightedEdgeInterface<?>) {
				d += ((WeightedEdgeInterface<?>) edge).getWeight();
			} else {
				d += 1;
			}
		}

		return d;
	}

	class DoubleFieldComparator implements Comparator<E> {

		@Override
		public int compare(E arg0, E arg1) {
			Double d = DEFAULT_EDGE_WEIGHT;
			Double e = DEFAULT_EDGE_WEIGHT;
			if (arg0 instanceof WeightedEdgeInterface<?>) {
				d = ((WeightedEdgeInterface<?>) arg0).getWeight();
			}

			if (arg1 instanceof WeightedEdgeInterface<?>) {
				e = ((WeightedEdgeInterface<?>) arg1).getWeight();
			}
			return d.compareTo(e);
		}
	}
}

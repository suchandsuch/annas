 package annas.graph.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.PriorityQueue;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.WeightedEdgeInterface;
import annas.misc.GraphPath;

public class Dijkstra<V, E extends EdgeInterface<V>> {

	private final double DEFAULT_EDGEWEIGHT = 1.0;

	private GraphInterface<V, E> graph;

	private Map<V, Marker<V>> lookup;

	private PriorityQueue<Marker<V>> queue;

	public Dijkstra(GraphInterface<V, E> graph) {
		super();
		this.graph = graph;
		this.queue = new PriorityQueue<Marker<V>>(this.graph.getVertices()
				.size());
		this.lookup = new Hashtable<V, Marker<V>>(this.graph.getVertices()
				.size());
		for (V n : this.graph.getVertices()) {
			this.lookup.put(n, new Marker<V>(n));
		}
	}

	public GraphPath<V, E> execute(V source, V destination) {
		int order = 0;
		Marker<V> m = this.lookup.get(source);
		m.setDistance(0);
		this.queue.add(m);
		Marker<V> tmp = m;

		while (tmp.getRepresenting() != destination
				&& this.queue.peek() != null) {

			tmp = this.queue.poll();
			tmp.setOrder(order);
			order++;
			tmp.setPermenant();

			for (E edge : this.graph.getEdges(tmp.getRepresenting())) {

				
				double weight = this.getWeight(edge);
				double distanceThrutmo = tmp.getDistance() + weight;
				Marker<V> mm = this.lookup.get(edge.getOtherEndpoint(tmp.representing));
				if (distanceThrutmo < mm.getDistance()) {
					this.queue.remove(mm);
					mm.setDistance(distanceThrutmo);
					mm.setPrevious(tmp.getRepresenting());
					this.queue.add(mm);

				}
			}

		}
		GraphPath<V, E> gp = new GraphPath<V, E>();

		V tmppp = destination;
		V tmpp = null;
		ArrayList<V> path = new ArrayList<V>();
		path.add(tmppp);
		while (tmppp != source) {
			tmpp = this.lookup.get(tmppp).getPrevious();
			if (tmpp == null) {
				return gp;
			}
			path.add(tmpp);
			tmppp = tmpp;
		}

		Marker<V> d, s = null;
		for (int i = path.size() - 1; i != 0; i--) {
			d = this.lookup.get(path.get(i - 1));
			s = this.lookup.get(path.get(i));
			gp.addEdge(getEdge(d.representing, s.representing, d.getDistance()
					- s.getDistance()));
		}

		return gp;
	}

	private E getEdge(V tail, V head, double weight) {
		Collection<E> edges = this.graph.getEdges(tail, head);

		for (E e : edges) {
			if (this.getWeight(e) == weight) {
				return e;
			}
		}

		return null;
	}

	private double getWeight(EdgeInterface<V> e) {
		if (e instanceof WeightedEdgeInterface) {
			return ((WeightedEdgeInterface<V>) e).getWeight();
		}
		return DEFAULT_EDGEWEIGHT;
	}

	private class Marker<N> implements Comparable<Marker<N>> {

		private N representing;

		private int order;

		private double distance;

		private N previous;

		private boolean permenant;

		public Marker(N representing) {
			super();
			this.representing = representing;
			this.order = Integer.MAX_VALUE;
			this.distance = Double.POSITIVE_INFINITY;
			this.permenant = false;
		}

		public N getRepresenting() {
			return representing;
		}

		public void setOrder(int order) {
			this.order = order;
		}

		public void setPermenant() {
			this.permenant = true;
		}

		public double getDistance() {
			return distance;
		}

		public void setDistance(double distance) {
			this.distance = distance;
		}

		@Override
		public int compareTo(Marker<N> o) {
			return Double.compare(this.distance, o.getDistance());
		}

		public N getPrevious() {
			return previous;
		}

		public void setPrevious(N previous) {
			this.previous = previous;
		}

		@Override
		public String toString() {
			return "Marker [distance=" + distance + ", order=" + order
					+ ", permenant=" + permenant + ", previous=" + previous
					+ ", representing=" + representing + "]";
		}

	}

}

package annas.misc;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import annas.graph.EdgeInterface;
import annas.graph.WeightedEdgeInterface;

public class GraphPath<V, E extends EdgeInterface<V>> {

	private Set<E> edges;

	public GraphPath() {
		super();
		this.edges = new LinkedHashSet<E>();
	}

	public boolean addEdge(E e) {
		return this.edges.add(e);
	}

	public double getDistance() {
		double r = 0;
		Iterator<E> i = this.edges.iterator();
		while (i.hasNext()) {
			r += (this.getWeight(i.next()));
		}
		return r;
	}

	public double getLength() {
		int r = 0;
		Iterator<E> i = this.edges.iterator();
		while (i.hasNext()) {
			r++;
			i.next();
		}
		return r == 0 ? 0 : r + 1;
	}

	public boolean contains(E e) {
		return this.edges.contains(e);
	}

	public Iterator<E> getIterator() {
		return new Iterator<E>() {

			Iterator<E> it = GraphPath.this.edges.iterator();

			@Override
			public boolean hasNext() {
				return this.it.hasNext();
			}

			@Override
			public E next() {
				return this.it.next();
			}

			@Override
			public void remove() {
			}
		};
	}

	private double getWeight(EdgeInterface<V> e) {
		if (e instanceof WeightedEdgeInterface<?>) {
			return ((WeightedEdgeInterface<V>) e).getWeight();
		}
		return 1.0;
	}

}

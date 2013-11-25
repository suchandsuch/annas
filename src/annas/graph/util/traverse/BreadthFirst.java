package annas.graph.util.traverse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

/**
 * Performs a Breadth first traversal
 * 
 * @author Sam Wilson
 * @see annas.graph.util.traverse.Traversal
 * @param <V>
 *            Vertex type
 * @param <E>
 *            Edge type
 */
public class BreadthFirst<V, E extends EdgeInterface<V>> implements
		Traversal<V, E> {

	private GraphInterface<V, E> graph;

	private LinkedList<V> Order;

	private HashMap<V, Integer> map;

	private boolean Bipartite = true;

	private V s;

	public BreadthFirst(GraphInterface<V, E> g, V s) {
		super();
		this.graph = g;
		this.s = s;
		this.map = new HashMap<V, Integer>();
		this.Order = new LinkedList<V>();
	}

	@Override
	public Iterator<V> getTraversal() {
		this.BF(this.s);
		return this.Order.iterator();
	}

	@Override
	public Iterator<V> iterator() {
		this.BF(this.s);
		return this.Order.iterator();
	}

	private void BF(V n) {
		Queue<V> l = new LinkedList<V>();

		map.put(n, 0);
		l.offer(n);

		while (l.size() > 0) {
			V m = l.poll();
			this.Order.add(m);
			Set<E> edges = this.graph.getEdges(m);
			for (E e : edges) {

				V head = this.getOtherEndPoint(m, e);
				if (this.Order.contains(head)) {

				} else {
					l.offer(head);
				}

				if (!map.containsKey(head)) {
					map.put(head, this.label(map.get(m)));

				} else {
					if (map.get(this.getOtherEndPoint(m, e)) % 2 == map.get(m) % 2) {
						this.Bipartite = false;
					}
				}
			}
		}

	}

	private int label(int i) {
		return i + 1;
	}

	public int getLevel(V node) {
		return this.map.get(node);
	}

	private V getOtherEndPoint(V tail, E edge) {

		if (edge.getHead() == edge.getTail()) {
			return edge.getHead();
		} else {
			if (edge.getHead() == tail) {
				return edge.getTail();
			} else {
				return edge.getHead();
			}
		}
	}

	public boolean isBipartite() {
		return Bipartite;
	}

}

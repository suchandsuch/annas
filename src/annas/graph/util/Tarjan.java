package annas.graph.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

/**
 * 
 * Tarjan's Algorithm (named for its discoverer, Robert Tarjan) is a graph
 * theory algorithm for finding the strongly connected components of a graph. As
 * described in Tarjan, R.: Depth-first search and linear graph algorithms, SIAM
 * J. Com- put. 1, no. 2, pp. 146-160
 * 
 * @author Sam Wilson
 * 
 * @param <V>
 *            Vertex type
 * @param <E>
 *            Edge type
 */
public class Tarjan<V, E extends EdgeInterface<V>> {

	private GraphInterface<V, E> graph;

	private Map<V, vertex<V>> verticies;

	private ArrayList<ArrayList<V>> comp;

	private int index;

	private Stack<vertex<V>> S;

	public Tarjan(GraphInterface<V, E> graph) {
		super();
		this.graph = graph;
		this.verticies = new HashMap<V, vertex<V>>();
		this.comp = new ArrayList<ArrayList<V>>();
		this.index = 0;
		this.S = new Stack<vertex<V>>();
		for (V node : this.graph.getVertices()) {
			this.verticies.put(node, new vertex<V>(node));
		}

	}

	/**
	 * executes Tarjan's algorithm of the suppled graph
	 * 
	 * @return A list of lists of node which belong to the same component
	 */
	public ArrayList<ArrayList<V>> execute() {
		ArrayList<vertex<V>> tmp = new ArrayList<vertex<V>>(this.verticies
				.values());
		for (int i = 0; i < tmp.size(); i++) {
			if (tmp.get(i).notNumbered()) {
				this.execute(tmp.get(i).node);
			}
		}

		return this.comp;
	}

	private ArrayList<ArrayList<V>> execute(V n) {

		this.run(this.verticies.get(n));

		return this.comp;
	}

	private void run(vertex<V> v) {
		v.index = this.index;
		v.lowlink = this.index;
		this.index++;
		this.S.push(v);

		Set<E> edges = this.graph.getEdges(v.node);

		for (E arc : edges) {
			vertex<V> vp = this.verticies.get(arc.getHead());

			if (vp.index == -1) {
				this.run(vp);
				v.lowlink = Math.min(v.lowlink, vp.lowlink);
			} else if (this.S.contains(vp)) {
				v.lowlink = Math.min(v.lowlink, vp.index);
			}
		}

		if (v.lowlink == v.index) {
			vertex<V> j;
			ArrayList<V> component = new ArrayList<V>();
			do {
				j = this.S.pop();
				component.add(j.node);
			} while (j != v);

			comp.add(component);
		}
	}

	@SuppressWarnings("hiding")
	private class vertex<V> {

		public V node;
		public int index;
		public int lowlink;

		/**
		 * @param n
		 */
		public vertex(V n) {
			super();
			this.index = -1;
			this.lowlink = -1;
			this.node = n;
		}

		public boolean notNumbered() {
			if (index == -1) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public String toString() {
			return "vertex [index=" + index + ", lowlink=" + lowlink
					+ ", node=" + node + "]";
		}

	}

}

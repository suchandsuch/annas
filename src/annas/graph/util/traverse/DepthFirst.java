package annas.graph.util.traverse;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

/**
 * Performs a depth first traversal
 * 
 * @author Sam Wilson
 * @see annas.graph.util.traverse.Traversal
 * @param <V>
 *            Vertex type
 * @param <E>
 *            Edge type
 */
public class DepthFirst<V, E extends EdgeInterface<V>> implements
		Traversal<V, E> {

	private GraphInterface<V, E> graph;

	private LinkedList<V> Order;

	private int depth;

	private int tmp;

	private V s;

	public DepthFirst(GraphInterface<V, E> g, V s) {
		super();
		this.depth = 0;
		this.tmp = 0;
		this.graph = g;
		this.s = s;
		this.Order = new LinkedList<V>();
	}

	/**
	 * Performs a Depth first traversal on the graph from the source vertex
	 * 
	 * @return Iterator of the traversal
	 */
	@Override
	public Iterator<V> getTraversal() {
		this.DF(this.s);
		return this.Order.iterator();
	}

	@Override
	public Iterator<V> iterator() {
		this.DF(this.s);
		return this.Order.iterator();
	}

	private void DF(V n) {
		if (!this.Order.contains(n)) {
			this.Order.add(n);
			Set<E> edges = this.graph.getEdges(n);
			if (edges.size() == 0 && this.tmp > this.depth) { // leave node
				this.depth = this.tmp;
			}
			for (E e : edges) {
				this.tmp++;
				this.DF(e.getOtherEndpoint(n));
			}
			this.tmp--;
		}

	}

	/**
	 * Gets the graph of the graph being traversed.
	 * 
	 * @return the graph being traversed
	 */
	public GraphInterface<V, E> getGraph() {
		return graph;
	}

	/**
	 * 
	 * @return the depth of the traversal
	 */
	public int getDepth() {
		return depth;
	}

}

package annas.graph.classifier;

import java.util.Hashtable;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

/**
 * Detects cycles in a graph, this classes uses a depth first search to discover
 * cycles.
 * 
 * @author Sam Wilson
 * 
 * @param <V>
 *            Vertex type
 * @param <E>
 *            Edge type
 */
public class Acyclic<V, E extends EdgeInterface<V>> implements Classifier<V, E> {

	/**
	 * Graph to detect cycles in
	 */
	protected GraphInterface<V, E> graph;

	private Hashtable<V, Integer> map;

	public Acyclic() {
		super();
		this.map = new Hashtable<V, Integer>();
	}

	@Override
	public boolean classify(GraphInterface<V, E> graph) {
		return !containsCycle();
	}

	/**
	 * Executes the algorithm
	 * 
	 * @return true if the algorithm discovers a cycles
	 */
	private boolean containsCycle() {

		for (V node : this.graph.getVertices())
			map.put(node, -1);

		for (V node : this.graph.getVertices()) {
			if (this.map.get(node) == -1) {
				if (this.visit(node)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean visit(V node) {
		this.map.put(node, 0);

		for (E arc : this.graph.getEdges(node)) {
			Integer h = this.map.get(arc.getHead());
			if (h == 0) {
				return true;
			} else if (h == -1) {
				if (this.visit(arc.getHead())) {
					return true;
				}
			}
		}
		this.map.put(node, 1);
		return false;

	}

}

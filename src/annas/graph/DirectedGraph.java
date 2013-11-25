/**
 * 
 */
package annas.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import annas.util.ArraySet;

/**
 * @author Sam
 * 
 */
@SuppressWarnings("serial")
public class DirectedGraph<V, E extends EdgeInterface<V>> extends
		AbstractGraph<V, E> {

	public DirectedGraph(EdgeFactory<V, E> edgeFactory) {
		super(edgeFactory, true);
		this.checker = new DirectedEdgeEqualityChecker<V, E>();
	}

	public DirectedGraph(Class<? extends E> edgeClass) {
		super(edgeClass, true);
		this.checker = new DirectedEdgeEqualityChecker<V, E>();
	}

	@Override
	public E addEdge(V tail, V head) {
		if (!this.allowparallelEdges && this.containsEdge(tail, head))
			return null;

		if (!this.allowloops && tail == head)
			return null;

		E edge = this.edgeFactory.create(tail, head);

		edge.setHead(head);
		edge.setTail(tail);

		boolean added = this.vertexMap.get(tail).put(head, edge) != null;
		if (added) {
			return edge;
		} else {
			return null;
		}
	}

	@Override
	public boolean addVertex(V vertex) {
		if (!this.containsVertex(vertex)) {
			return this.vertexMap.put(vertex, new MultiHashMap<V, E>()) == null;
		}
		return false;
	}

	@Override
	public boolean removeEdge(E edge) {
		return this.vertexMap.get(edge.getTail()).remove(edge.getHead(), edge) != null
				&& this.vertexMap.get(edge.getHead()).remove(edge.getTail(),
						edge) != null;

	}

	@Override
	public boolean removeEdge(V tail, V head) {
		Set<E> edges = this.getEdges(tail, head);
		boolean successful = true;
		for (E e : edges) {
			successful &= this.removeEdge(e);
		}
		return successful;
	}

	@Override
	public boolean removeEdge(V tail) {
		Set<E> edges = this.getEdges(tail);
		boolean successful = true;
		for (E e : edges) {
			successful &= this.removeEdge(e);
		}
		return successful;
	}

	@Override
	public boolean removeVertex(V vertex) {
		ArrayList<V> m = new ArrayList<V>(this.vertexMap.keySet());
		// boolean successful = true;
		for (V mhm : m) {
			this.vertexMap.get(mhm).remove(vertex);
			// successful &= !this.vertexMap.get(mhm).containsKey(vertex);
		}
		return this.vertexMap.remove(vertex) != null;
	}

	public Set<E> getInEdges(V vertex) {
		Set<E> n = this.getEdges();
		Set<E> m = new ArraySet<E>(new UndirectedEdgeEqualityChecker<V, E>());

		for (E e : n) {
			if (e.getHead().equals(vertex)) {
				m.add(e);
			}
		}

		return Collections.unmodifiableSet(m);
	}

	public Set<E> getOutEdges(V vertex) {
		return this.getEdges(vertex);
	}
}

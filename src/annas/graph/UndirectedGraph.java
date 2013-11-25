package annas.graph;

import java.util.Set;

/**
 * @author Sam
 * 
 */
@SuppressWarnings("serial")
public class UndirectedGraph<V, E extends EdgeInterface<V>> extends
		AbstractGraph<V, E> {

	public UndirectedGraph(EdgeFactory<V, E> edgeFactory) {
		super(edgeFactory, false);
		this.checker = new UndirectedEdgeEqualityChecker<V, E>();
	}

	public UndirectedGraph(Class<E> edgeClass) {
		super(edgeClass, false);
		this.checker = new UndirectedEdgeEqualityChecker<V, E>();
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
		added &= this.vertexMap.get(head).put(tail, edge) != null;
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
		this.removeEdge(vertex);
		return this.vertexMap.remove(vertex) != null;
	}
}

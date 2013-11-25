package annas.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import annas.util.ArraySet;
import annas.util.EqualityChecker;

/**
 * Base class for all graphs using the default implementation.
 * 
 * @author Sam
 * 
 */
@SuppressWarnings("serial")
public abstract class AbstractGraph<V, E extends EdgeInterface<V>> implements
		GraphInterface<V, E> {

	protected GraphObserver<V, E> go;

	protected int version;

	protected EdgeFactory<V, E> edgeFactory;

	protected Map<V, MultiHashMap<V, E>> vertexMap;

	protected EqualityChecker<E> checker;

	protected boolean allowloops;

	protected boolean allowparallelEdges;

	protected boolean directed;

	public AbstractGraph(EdgeFactory<V, E> edgeFactory, boolean directed) {
		super();
		this.edgeFactory = edgeFactory;
		this.version = 0;
		this.vertexMap = new Hashtable<V, MultiHashMap<V, E>>();
		this.allowloops = true;
		this.allowparallelEdges = true;
		this.directed = directed;
	}

	public AbstractGraph(Class<? extends E> edgeClass, boolean directed) {
		this(new ClassEdgeFactory<V, E>(edgeClass), directed);
	}

	@Override
	public boolean addVertices(Collection<? extends V> vertices) {
		boolean successful = true;
		for (V vertex : vertices) {
			successful &= this.addVertex(vertex);
		}
		return successful;
	}

	@SuppressWarnings("unchecked")
	public boolean addVertices(V... vs) {
		boolean successful = true;
		for (V vertex : vs) {
			successful &= this.addVertex(vertex);
		}
		return successful;
	}

	@Override
	public boolean removeVertices(Collection<? extends V> vertices) {
		boolean successful = true;
		for (V vertex : vertices) {
			successful &= this.removeEdge(vertex);
		}
		return successful;
	}

	@Override
	public boolean removeEdges(Collection<? extends E> edges) {
		boolean successful = true;
		for (E edge : edges) {
			successful &= this.removeEdge(edge);
		}
		return successful;
	}

	@Override
	public Set<V> getVertices() {
		return Collections.unmodifiableSet(this.vertexMap.keySet());
	}

	@Override
	public boolean resetEdges() {
		return this.removeEdges(this.getEdges());
	}

	@Override
	public boolean containsEdge(E edge) {
		return this.getEdges().contains(edge);
	}

	@Override
	public boolean containsEdge(V tail, V head) {
		return !this.getEdges(tail, head).isEmpty();
	}

	@Override
	public boolean containsVertex(V vertex) {
		return this.getVertices().contains(vertex);
	}

	@Override
	public EdgeFactory<V, E> getEdgeFactory() {
		return this.edgeFactory;
	}

	@Override
	public int getSize() {
		return this.getEdges().size();
	}

	@Override
	public int getOrder() {
		return this.getVertices().size();
	}

	public int getVersion() {
		return this.version;
	}

	@Override
	public GraphObserver<V, E> getObserver() {
		return this.go;
	}

	@Override
	public void setObserver(GraphObserver<V, E> graphObserver) {
		this.go = graphObserver;
	}

	protected boolean assertVertexExist(V vertex) {
		if (containsVertex(vertex)) {
			return true;
		} else if (vertex == null) {
			throw new NullPointerException();
		} else {
			throw new IllegalArgumentException("Vertex is not in the graph");
		}
	}

	@Override
	public Set<E> getEdges(V tail) {
		Set<E> edges = new ArraySet<E>(
				new UndirectedEdgeEqualityChecker<V, E>());
		Collection<E> es = new ArrayList<E>();

		Collection<E> h = this.vertexMap.get(tail).values();
		if (h == null) {
			return Collections.unmodifiableSet(edges);
		}
		es.addAll(h);

		for (E edge : es) {
			edges.add(edge);
		}
		return Collections.unmodifiableSet(edges);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<E> getEdges(V tail, V head) {
		Set<E> edges = new ArraySet<E>(
				new UndirectedEdgeEqualityChecker<V, E>());
		Collection<E> es = new ArrayList<E>();

		Collection<E> h = null;
		try {
			h = this.vertexMap.get(tail).getCollection(head);
		} catch (NullPointerException e) {
		}

		if (h == null) {
			return Collections.unmodifiableSet(edges);
		}
		es.addAll(h);

		for (E edge : es) {
			edges.add(edge);
		}
		return Collections.unmodifiableSet(edges);
	}

	@Override
	public Set<E> getEdges() {
		Set<E> edges = new ArraySet<E>(
				new UndirectedEdgeEqualityChecker<V, E>());
		Collection<E> es = new ArrayList<E>();

		for (V vertex : this.vertexMap.keySet()) {
			try {
				es.addAll(this.vertexMap.get(vertex).values());
			} catch (NullPointerException e) {
			}

		}

		for (E edge : es) {
			edges.add(edge);
		}
		return Collections.unmodifiableSet(edges);
	}

	@Override
	public int getDegree(V vertex) {
		return this.getEdges(vertex).size();
	}

	public boolean allowLoops() {
		return allowloops;
	}

	public boolean allowMultipleEdges() {
		return allowparallelEdges;
	}

	public boolean isDirected() {
		return this.directed;
	}

	@Override
	public Class<?> getEdgeClass() {
		return this.edgeFactory.getEdgeClass();
	}

	@Override
	public String toString() {
		String ret = "Graph on ";
		switch (this.getOrder()) {
		case 0:
			return "Null graph";
		case 1:
			ret += "1 vertex ";
			break;

		default:
			ret += this.getOrder() + " vertices ";
			break;
		}

		ret += "with ";
		switch (this.getSize()) {
		case 0:
			ret += "no edges";
			break;
		case 1:
			ret += "1 edge";
			break;

		default:
			ret += this.getSize() + " edges";
			break;
		}

		ret += ".";

		return ret;
	}
}

package annas.graph;

@SuppressWarnings("serial")
public class SimpleUndirectedGraph<V, E extends EdgeInterface<V>> extends
		UndirectedGraph<V, E> {

	public SimpleUndirectedGraph(Class<E> edgeClass) {
		super(edgeClass);
		this.allowloops = false;
		this.allowparallelEdges = false;
	}

	public SimpleUndirectedGraph(EdgeFactory<V, E> edgeFactory) {
		super(edgeFactory);
		this.allowloops = false;
		this.allowparallelEdges = false;
	}

}

package annas.graph;

@SuppressWarnings("serial")
public class SimpleDirectedGraph<V, E extends EdgeInterface<V>> extends
		DirectedGraph<V, E> {

	public SimpleDirectedGraph(Class<? extends E> edgeClass) {
		super(edgeClass);
		this.allowloops = false;
		this.allowparallelEdges = false;
	}

	public SimpleDirectedGraph(EdgeFactory<V, E> edgeFactory) {
		super(edgeFactory);
		this.allowloops = false;
		this.allowparallelEdges = false;
	}

}

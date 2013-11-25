package annas.graph.io;

public class DefaultVertexLabeller<V> implements VertexLabeller<V> {

	@Override
	public String getLabel(V v) {
		return v.toString();
	}

}

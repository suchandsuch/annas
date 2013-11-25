package annas.graph.io;

/**
 * Gets a label for the supplied vertex.
 * 
 * @author Sam Wilson
 * 
 * @param <V>
 */
public interface VertexLabeller<V> {

	public String getLabel(V v);
}

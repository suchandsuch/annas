package annas.graph;

/**
 * Factory for creating vertices
 * 
 * @author Sam Wilson
 * 
 * @param <V>
 *            Vertex type
 */
public interface VertexFactory<V> {

	public V createVertex();

}

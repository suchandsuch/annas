package annas.graph;

import java.io.Serializable;

/**
 * Factory used to construct edge.
 * 
 * @author Sam
 * 
 * @param <V>
 *            Vertex type
 * @param <E>
 *            Edge type
 */
public interface EdgeFactory<V, E extends EdgeInterface<V>> extends
		Serializable {

	/**
	 * Construct a new edge.
	 * 
	 * @param tail
	 *            endpoint
	 * 
	 * @param head
	 *            endpoint
	 * 
	 * @return newly constructed edge.
	 */
	public E create(V tail, V head);
	
	/**
	 * Gets the edge class
	 * @return edhe class
	 */
	public Class<?> getEdgeClass();

}

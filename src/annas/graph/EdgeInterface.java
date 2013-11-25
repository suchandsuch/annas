package annas.graph;

import java.io.Serializable;

/**
 * Interface of all edge used in a Graph
 * 
 * @author Sam Wilson
 * 
 * @param <V>
 *            Vertex type
 */
public interface EdgeInterface<V> extends Serializable {

	/**
	 * Gets the head of the Edge
	 * 
	 * @return Vertex at the head of the edge
	 */
	public V getHead();

	/**
	 * Gets the tail of the Edge
	 * 
	 * @return Vertex at the tail of the edge
	 */
	public V getTail();

	/**
	 * Set head of the edge
	 * 
	 * @param vertex
	 */
	public void setHead(V vertex);

	/**
	 * Set tail of the edge
	 * 
	 * @param vertex
	 */
	public void setTail(V vertex);

	/**
	 * Checks if the edge is incident to the given vertex
	 * 
	 * @param vertex
	 * @return True if the edge is incident to the vertex, otherwise false
	 */
	public boolean isIncident(V vertex);

	/**
	 * Retrieves the other end point to the end.
	 * 
	 * @param vertex
	 * @return the other end of an edge
	 */
	public V getOtherEndpoint(V vertex);

}

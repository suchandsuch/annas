package annas.graph;

import java.io.Serializable;

/**
 * Any class wishing to observer a graph must implement this interface
 * 
 * @author Sam Wilson
 */
public interface GraphObserver<V,E extends EdgeInterface<V>> extends Serializable {

	/**
	 * Method called when an edge is added to the graph being observed.
	 * @param graph Graph being observed
	 * @param args arguments passed to the method
	 */
	public void edgeAdded(GraphInterface<V,E> graph, Object[] args);
	
	/**
	 * Method called when an edge is removed from the graph being observed.
	 * @param graph Graph being observed
	 * @param args arguments passed to the method
	 */
	public void edgeRemoved(GraphInterface<V,E> graph, Object[] args);
	
	/**
	 * Method called when a vertex is added to the graph being observed.
	 * @param graph Graph being observed
	 * @param args arguments passed to the method
	 */
	public void vertexAdded(GraphInterface<V,E> graph, Object[] args);
	
	/**
	 * Method called when a vertex is removed from the graph being observed.
	 * @param graph Graph being observed
	 * @param args arguments passed to the method
	 */
	public void vertexRemoved(GraphInterface<V,E> graph, Object[] args);

}

package annas.graph.util.traverse;

import java.util.Iterator;

import annas.graph.EdgeInterface;

/**
 * Interface for traversal algorithms.
 * 
 * @author Sam Wilson
 * 
 * @param <V>
 * @param <E>
 */
public interface Traversal<V, E extends EdgeInterface<V>> extends Iterable<V> {

	/**
	 * Get an iterator containing the vertices of the traversal in the order
	 * dictated by the traversal algorithm.
	 * 
	 * @return an iterator of vertices order according to the travseral algorithm.
	 */
	public Iterator<V> getTraversal();
}

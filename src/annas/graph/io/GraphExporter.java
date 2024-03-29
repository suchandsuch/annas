package annas.graph.io;

import java.io.OutputStream;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

/**
 * Interface for all Exporters. Exporters provide an alternative representation
 * of a graph from use outside of the annas package.
 * 
 * @author Sam Wilson
 * 
 * @param <V>
 *            Vertex type
 * @param <E>
 *            Edge type
 */
public interface GraphExporter<V, E extends EdgeInterface<V>> {

	/**
	 * Exports the provided graph.
	 * 
	 * @param writer
	 *            Stream to output the graph to
	 * @param graph
	 *            Graph to export
	 */
	public void exportGraph(OutputStream writer, GraphInterface<V, E> graph);

	/**
	 * Exports the provided graph to std.
	 * 
	 * @param graph
	 *            Graph to export
	 */
	public void exportGraph(GraphInterface<V, E> graph);

}

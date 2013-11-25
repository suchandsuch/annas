package annas.graph.io;

import java.beans.XMLEncoder;
import java.io.OutputStream;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

/**
 * Exports the graph to xml
 * 
 * @author Sam Wilson
 * 
 * @param <V>
 *            Vertex type
 * @param <E>
 *            Edge type
 */
public class XmlExporter<V, E extends EdgeInterface<V>> implements
		GraphExporter<V, E> {

	/**
	 * Stream to output to
	 */
	private OutputStream ops;

	public XmlExporter() {
		super();
		this.ops = System.out;
	}

	public XmlExporter(OutputStream ops) {
		super();
		this.ops = ops;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void exportGraph(OutputStream pw, GraphInterface<V, E> graph) {
		if (pw != null) {
			this.ops = pw;
			this.exportGraph(graph);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void exportGraph(GraphInterface<V, E> graph) {
		if (this.ops != null) {
			XMLEncoder encoder;

			encoder = new XMLEncoder(this.ops);
			encoder.writeObject(graph);
			encoder.close();
		}

	}

}

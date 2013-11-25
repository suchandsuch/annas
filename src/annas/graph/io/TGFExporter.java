package annas.graph.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Hashtable;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

public class TGFExporter<V, E extends EdgeInterface<V>> implements
		GraphExporter<V, E> {

	/**
	 * Vertex labeller
	 */
	private VertexLabeller<V> labeller = new DefaultVertexLabeller<V>();

	public TGFExporter(VertexLabeller<V> labeller) {
		super();
		this.labeller = labeller;
	}

	public TGFExporter() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void exportGraph(OutputStream ops, GraphInterface<V, E> graph) {
		Writer writer = new PrintWriter(ops);
		Hashtable<V, Integer> map = new Hashtable<V, Integer>(graph.getOrder());
		int i = 1;
		try {
			for (V v : graph.getVertices()) {
				map.put(v, i);
				writer.write(i + " " + labeller.getLabel(v) + "\n");
				i++;

			}

			writer.write("#\n");

			for (E e : graph.getEdges()) {
				writer.write(map.get(e.getTail()) + " " + map.get(e.getHead())
						+ "\n");
			}
			writer.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void exportGraph(GraphInterface<V, E> graph) {
		this.exportGraph(System.out, graph);
	}

}

package annas.graph.io;

import java.io.OutputStream;
import java.io.PrintWriter;

import annas.graph.DirectedGraph;
import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.UndirectedGraph;

/**
 * Export graph to the standard .DOT format @see <a
 * href="http://en.wikipedia.org/wiki/DOT_language"> shown here </a>
 * 
 * @author Sam Wilson
 * 
 * @param <V>
 *            Vertex type
 * @param <E>
 *            Edge type
 */
public class DOTExporter<V, E extends EdgeInterface<V>> implements
		GraphExporter<V, E> {

	/**
	 * Vertex labeler
	 */
	private VertexLabeller<V> labeller = new DefaultVertexLabeller<V>();

	private boolean simple = true;
	/**
	 * Connector
	 */
	private String connector = "  ";

	public DOTExporter(VertexLabeller<V> labeller) {
		super();
		this.labeller = labeller;
	}

	public DOTExporter() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void exportGraph(OutputStream ops, GraphInterface<V, E> graph) {
		PrintWriter pw = new PrintWriter(ops);

		assert (pw != null);

		String indent = "  ";

		if (graph instanceof DirectedGraph<?, ?>) {
			pw.println("digraph G { ");
			this.connector = "->";
		}
		if (graph instanceof UndirectedGraph<?, ?>) {
			pw.println("graph G { ");
			this.connector = "--";
		}
		/*pw.println("node [ fontname = \"Arial\" \n " + "label = \"\" \n "
				+ "shape = \"circle\" \n" + " width = \"0.1\" \n"
				+ "height = \"0.1\" \n" + "color = \"black\" ] \n \n"
				+ "edge [ color = \"black\" \n" + "weight = \"1\"]");
*/
		for (V n : graph.getVertices()) {
			pw.println(indent + this.labeller.getLabel(n) + ";");
		}

		for (V n : graph.getVertices()) {
			for (E edge : graph.getEdges(n)) {
				if (this.simple
						&& this.labeller.getLabel(n).equals(
								this.labeller.getLabel(edge.getHead()))) {

				} else {
					pw.println(indent + indent + this.labeller.getLabel(n)
							+ " " + this.connector + " "
							+ this.labeller.getLabel(edge.getHead()) + ";");
				}
			}
		}

		pw.println("}");
		pw.flush();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void exportGraph(GraphInterface<V, E> graph) {
		this.exportGraph(System.out, graph);
	}

	public String getConnector() {
		return connector;
	}

	public void setConnector(String connector) {
		this.connector = connector;
	}

}

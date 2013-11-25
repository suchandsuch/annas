import annas.graph.DefaultEdge;
import annas.graph.UndirectedGraph;
import annas.graph.io.DOTExporter;
import annas.graph.io.GraphExporter;

public class ChordalRecogniserHarness {

	public static void main(String[] args) {
		UndirectedGraph<String, DefaultEdge> graph = new UndirectedGraph<String, DefaultEdge>(
				DefaultEdge.class);

		String a = "A"; // u0
		String b = "B";// u1
		String c = "C";// u2
		String d = "D";// u3
		String e = "E";// u4
		String f = "F";// u5

		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addVertex(d);
		graph.addVertex(e);
		graph.addVertex(f);

		/*
		 * graph.addArc(a, b, new DefaultWeight(1d)); graph.addArc(c, b, new
		 * DefaultWeight(1d)); graph.addArc(d, c, new DefaultWeight(1d));
		 * graph.addArc(a, d, new DefaultWeight(1d));
		 */

		graph.addEdge(a, b);
		graph.addEdge(b, c);
		graph.addEdge(c, d);
		graph.addEdge(d, a);
		graph.addEdge(a, c);
		graph.addEdge(b, e);
		graph.addEdge(c, e);
		graph.addEdge(c, f);
		graph.addEdge(d, f);

		// new ChordalRecogniser().check(graph);
		// GraphExporter gh = new TGFExporter();
		GraphExporter gh = new DOTExporter();
		gh.exportGraph(graph);
	}
}

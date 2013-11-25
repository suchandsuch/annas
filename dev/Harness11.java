import annas.graph.DefaultEdge;
import annas.graph.UndirectedGraph;
import annas.graph.util.BipartiteMatching;

public class Harness11 {

	public static void main(String[] args) throws Exception {
		final UndirectedGraph<String, DefaultEdge> g = new UndirectedGraph<String, DefaultEdge>(
				DefaultEdge.class);
		final String a = "A"; // u0
		final String b = "B";// u1
		final String c = "C";// u2
		final String d = "D";// u3
		final String e = "E";// u4
		final String f = "F";// u5

		g.addVertex(a);
		g.addVertex(b);
		g.addVertex(c);
		g.addVertex(d);
		g.addVertex(e);
		g.addVertex(f);

		g.addEdge(a, b);
		g.addEdge(b, c);
		g.addEdge(c, d);
		g.addEdge(d, a);
		g.addEdge(e, f);

		final BipartiteMatching<String, DefaultEdge> bm = new BipartiteMatching<String, DefaultEdge>(
				g);
		System.out.println(bm.getMatching());
	}
}

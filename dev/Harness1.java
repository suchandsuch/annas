import test.DefaultWeightedEdge;
import annas.graph.DirectedGraph;
import annas.graph.util.FordFulkerson;

public class Harness1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DirectedGraph<String, DefaultWeightedEdge> graph = new DirectedGraph<String, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);
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

		DefaultWeightedEdge e1 = null;
		e1 = graph.addEdge(a, b);
		e1.setWeight(5);
		e1 = graph.addEdge(a, c);
		e1.setWeight(9);
		e1 = graph.addEdge(b, d);
		e1.setWeight(4);
		e1 = graph.addEdge(d, f);
		e1.setWeight(3);
		e1 = graph.addEdge(c, d);
		e1.setWeight(8);
		e1 = graph.addEdge(c, e);
		e1.setWeight(3);
		e1 = graph.addEdge(c, f);
		e1.setWeight(2);
		e1 = graph.addEdge(d, e);
		e1.setWeight(1);
		e1 = graph.addEdge(e, f);
		e1.setWeight(9);

		FordFulkerson<String, DefaultWeightedEdge> ff = new FordFulkerson<String, DefaultWeightedEdge>(
				graph, a, f);

		System.out.println(ff.getMaximumFlow());

		test();

	}

	private static void test() {
		DirectedGraph<String, DefaultWeightedEdge> graph = new DirectedGraph<String, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);

		graph.addVertex("s");
		graph.addVertex("o");
		graph.addVertex("p");
		graph.addVertex("q");
		graph.addVertex("r");
		graph.addVertex("t");

		DefaultWeightedEdge e1 = null;
		e1 = graph.addEdge("s", "o");
		e1.setWeight(3);
		e1 = graph.addEdge("s", "p");
		e1.setWeight(3);
		e1 = graph.addEdge("o", "p");
		e1.setWeight(2);
		e1 = graph.addEdge("o", "q");
		e1.setWeight(3);
		e1 = graph.addEdge("p", "r");
		e1.setWeight(2);
		e1 = graph.addEdge("r", "t");
		e1.setWeight(3);
		e1 = graph.addEdge("q", "r");
		e1.setWeight(4);
		e1 = graph.addEdge("q", "t");
		e1.setWeight(2);

		FordFulkerson<String, DefaultWeightedEdge> ff = new FordFulkerson<String, DefaultWeightedEdge>(
				graph, "s", "t");

		System.out.println(ff.getMaximumFlow());
	}
}

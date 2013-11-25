import annas.graph.DefaultEdge;
import annas.graph.IntegerEdge;
import annas.graph.SimpleUndirectedGraph;
import annas.graph.UndirectedGraph;
import annas.graph.classifier.SplitRecogniser;

public class SplitRecogniserHarness {

	public static void main(String[] args) {
		test();
		test_();
	}

	private static void test_() {
		UndirectedGraph<String, DefaultEdge> graph = new UndirectedGraph<String, DefaultEdge>(
				DefaultEdge.class);

		String a = "A"; // u0
		String b = "B";// u1
		String c = "C";// u2
		String d = "D";// u3
		String e = "E";// u4
		String f = "F";// u4
		String g = "G";

		graph.addVertex(a); // 1
		graph.addVertex(b); // 2
		graph.addVertex(c); // 3
		graph.addVertex(d);// 4
		graph.addVertex(e);// 5
		graph.addVertex(f);// 5
		graph.addVertex(g);// 5

		graph.addEdge(a, b);
		graph.addEdge(b, c);
		graph.addEdge(c, a);
		graph.addEdge(d, a);
		graph.addEdge(d, b);
		graph.addEdge(d, c);

		graph.addEdge(e, f);

		System.out.println(new SplitRecogniser().classify(graph));
	}

	public static void test() {
		SimpleUndirectedGraph<Integer, IntegerEdge> graph = new SimpleUndirectedGraph<Integer, IntegerEdge>(
				IntegerEdge.class);

		for (int i = 0; i < 4; i++) {
			graph.addVertex(i);
		}

		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);

		System.out.println(new SplitRecogniser().classify(graph));

	}
}

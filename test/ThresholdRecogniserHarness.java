import annas.graph.DefaultEdge;
import annas.graph.UndirectedGraph;
import annas.graph.classifier.ThresholdRecogniser;


public class ThresholdRecogniserHarness {

	public static void main(String[] args){
		UndirectedGraph<String, DefaultEdge> graph = new UndirectedGraph<String, DefaultEdge>(DefaultEdge.class);

		String a = "A"; // u0
		String b = "B";// u1
		String c = "C";// u2
		String d = "D";// u3
		String e = "E";// u4
		String f = "F";// u5
		String g = "G";
		String h = "H";
			

		graph.addVertex(a); // 1
		graph.addVertex(b); // 2
		graph.addVertex(c); // 3
		graph.addVertex(d);// 4
		graph.addVertex(e);// 5
		graph.addVertex(f);// 6
		graph.addVertex(g);// 7
		graph.addVertex(h);// 8
	

		graph.addEdge(f, h);
		graph.addEdge(g, h);
		graph.addEdge(e, h);
		graph.addEdge(a, e);
		graph.addEdge(a, h);
		graph.addEdge(b, e);
		graph.addEdge(b, h);
		graph.addEdge(c, e);
		graph.addEdge(c, h);
		graph.addEdge(d, e);
		graph.addEdge(d, h);
		
		System.out.println(new ThresholdRecogniser().classify(graph));
	}
}

import annas.graph.IntegerEdge;
import annas.graph.SimpleUndirectedGraph;
import annas.graph.classifier.DisjointCliqueRecogniser;

public class DisjointCliqueRecogniserHarness {

    public static void main(String[] args) {
	final SimpleUndirectedGraph<Integer, IntegerEdge> graph = new SimpleUndirectedGraph<Integer, IntegerEdge>(
		IntegerEdge.class);

	for (int i = 0; i < 10; i++) {
	    graph.addVertex(i);
	}

	for (int i = 0; i < 5; i++) {
	    for (int j = 0; j < 5; j++) {
		graph.addEdge(i, j);
	    }
	}

	for (int i = 5; i < 7; i++) {
	    for (int j = 5; j < 7; j++) {
		graph.addEdge(i, j);
	    }
	}

	for (int i = 7; i < 10; i++) {
	    for (int j = 7; j < 10; j++) {
		graph.addEdge(i, j);
	    }
	}

	System.out.println(graph);

	final DisjointCliqueRecogniser<Integer, IntegerEdge> dcr = new DisjointCliqueRecogniser<Integer, IntegerEdge>();

	System.out.println(dcr.classify(graph));
    }
}

import annas.graph.IntegerEdge;
import annas.graph.SimpleUndirectedGraph;
import annas.graph.io.Graph6Exporter;
import annas.graph.io.GraphExporter;
import annas.graph.util.Utilities;


public class Harness111 {

	public static void main(String[] args) {
		GraphExporter ge = new Graph6Exporter();
		
		SimpleUndirectedGraph<Integer, IntegerEdge> graph = new SimpleUndirectedGraph<Integer, IntegerEdge>(
				IntegerEdge.class);
		
		for(int i = 0; i <4; i++){
			graph.addVertex(i);
		}
		
		graph.addEdge(0, 1);
		graph.addEdge(2, 1);
		graph.addEdge(2, 3);
		graph.addEdge(3, 0);
		
		System.out.println(Utilities.getAdjacencyMatrix(graph));
		ge.exportGraph(graph);

	}

}

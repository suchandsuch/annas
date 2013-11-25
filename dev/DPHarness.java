import annas.graph.GraphInterface;
import annas.graph.IntegerEdge;
import annas.graph.UndirectedGraph;
import annas.graph.observable.ObservableGraph;


public class DPHarness {

	public static void main(String args[]){
		GraphInterface graph = new UndirectedGraph<>(IntegerEdge.class);
		GraphInterface og = ObservableGraph.getObservableGraph(graph);
		og.addVertex(1);
		System.out.println(og instanceof UndirectedGraph);
	}

}

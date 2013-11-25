

import java.util.ArrayList;
import java.util.List;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.SimpleUndirectedGraph;
import annas.graph.classifier.Classifier;

public class findMinimalForbidden<V, E extends EdgeInterface<V>> {

	private Classifier recogniser;

	public findMinimalForbidden(Classifier<V, E> classifier) {
		super();
		this.recogniser = classifier;
	}

	public GraphInterface<V, E> find(SimpleUndirectedGraph<V, E> graph) {
		SimpleUndirectedGraph<V, E> tmp = this.copyGraph(graph);
		SimpleUndirectedGraph<V, E> tmp1;

		List<V> Vs = new ArrayList<V>();

		for (V v : graph.getVertices()) {
			tmp1 = this.copyGraph(tmp);
			tmp.removeVertex(v);
			if (this.recogniser.classify(tmp)) {
				Vs.add(v);
				tmp = tmp1;
			}
		}
		
		tmp = this.copyGraph(graph);
		
		for(V v : graph.getVertices()){
			if(!Vs.contains(v)){
				tmp.removeVertex(v);
			}
		}
		return tmp;
	}

	/**
	 * Makes a copy of the graph uses the same vertices and edge objects but
	 * makes a copy of the structure.
	 * 
	 * @param graph
	 * @return
	 */
	private SimpleUndirectedGraph<V, E> copyGraph(
			SimpleUndirectedGraph<V, E> graph) {
		SimpleUndirectedGraph<V, E> retval = new SimpleUndirectedGraph<V, E>(
				graph.getEdgeFactory());

		retval.addVertices(graph.getVertices());
		for (E e : graph.getEdges()) {
			retval.addEdge(e.getTail(), e.getHead());
		}
		return retval;

	}

}

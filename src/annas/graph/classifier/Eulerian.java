package annas.graph.classifier;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

/**
 * Classifies if a graph is Eulerian. 
 * @author scsswi
 *
 * @param <V>
 * @param <E>
 */
public class Eulerian<V, E extends EdgeInterface<V>> implements
		Classifier<V, E> {

	@Override
	public boolean classify(GraphInterface<V, E> graph) {
		boolean eulerian = true;

		for (V vertex : graph.getVertices()) {
			eulerian &= graph.getEdges(vertex).size() % 2 == 0;
		}

		return eulerian;
	}

}

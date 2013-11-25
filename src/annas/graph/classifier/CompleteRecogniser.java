package annas.graph.classifier;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

public class CompleteRecogniser<V, E extends EdgeInterface<V>> implements
		Classifier<V, E> {

	@Override
	public boolean classify(GraphInterface<V, E> graph) {

		for (V v : graph.getVertices()) {
			for (V u : graph.getVertices()) {
				if (u != v) {
					if (graph.getEdges(v, u).isEmpty()) {
						return false;
					}
				}
			}
		}

		return true;
	}

}

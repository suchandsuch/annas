package annas.graph.classifier;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

public class EdgelessRecogniser<V, E extends EdgeInterface<V>> implements
		Classifier<V, E> {

	@Override
	public boolean classify(GraphInterface<V, E> graph) {
		return graph.getEdges().isEmpty() ? true : false;
	}

}

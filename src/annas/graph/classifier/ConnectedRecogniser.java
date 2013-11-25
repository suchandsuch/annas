package annas.graph.classifier;

import java.util.Collection;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.util.Utilities;

public class ConnectedRecogniser<V, E extends EdgeInterface<V>> implements
		Classifier<V, E> {

	@Override
	public boolean classify(GraphInterface<V, E> graph) {
		Collection<Collection<V>> c = Utilities.getConnectedComponents(graph);
		if (c.size() == 1 || graph.getOrder() == 0) {
			return true;
		}
		return false;
	}

}

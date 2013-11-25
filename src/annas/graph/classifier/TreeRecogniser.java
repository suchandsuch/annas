package annas.graph.classifier;

import java.util.Collection;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.util.Utilities;

public class TreeRecogniser<V, E extends EdgeInterface<V>> implements
		Classifier<V, E> {

	@Override
	public boolean classify(GraphInterface<V, E> graph) {
		Collection<?> comps = Utilities.getConnectedComponents(graph);

		return comps.size() == 1 && new Acyclic<V, E>().classify(graph);
	}

}

package annas.graph.classifier;

import java.util.Collection;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.util.Utilities;

public class DisjointCliqueRecogniser<V, E extends EdgeInterface<V>> implements
		Classifier<V, E> {

	/**
	 * Takes a simple undirected graph and returns true if it is a collection
	 * of disjoint cliques in linear time. 
	 */
	@Override
	public boolean classify(GraphInterface<V, E> graph) {
		Collection<Collection<V>> C = Utilities.getConnectedComponents(graph);
		for (Collection<V> c : C) {
			int d = c.size() - 1;
			for (V v : c) {
				if (graph.getDegree(v) != d) {
					return false;
				}

			}
		}
		return true;
	}

}

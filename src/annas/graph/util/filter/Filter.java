package annas.graph.util.filter;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

public interface Filter<V, E extends EdgeInterface<V>> {

	public GraphInterface<V, E> filter(GraphInterface<V, E> graph);
}

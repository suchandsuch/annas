package annas.graph.util;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.misc.GraphPath;

public interface SingleSourceShortestPath<V, E extends EdgeInterface<V>> {

	public GraphInterface<V, E> execute(V v);

	public GraphPath<V, E> execute(V v, V u);

}

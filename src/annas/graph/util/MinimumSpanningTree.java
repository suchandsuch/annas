package annas.graph.util;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

public interface MinimumSpanningTree<V, E extends EdgeInterface<V>> {

	public GraphInterface<V,E> execute();
	
	public double getCost();
}

package annas.graph.isomorphism.precondition;

import annas.graph.GraphInterface;

public interface Precondition {

	public boolean evaluate(GraphInterface<?, ?> g1, GraphInterface<?, ?> g2);
}

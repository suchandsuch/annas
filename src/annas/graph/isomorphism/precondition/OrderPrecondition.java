package annas.graph.isomorphism.precondition;

import annas.graph.GraphInterface;

/**
 * Checks if the order of two graphs is equal.
 * 
 * @author Sam Wilson
 */
public class OrderPrecondition implements Precondition {

	@Override
	public boolean evaluate(GraphInterface<?, ?> g1, GraphInterface<?, ?> g2) {
		return g1.getOrder() == g2.getOrder();
	}

}

package annas.graph.isomorphism.precondition;

import annas.graph.GraphInterface;

/**
 * Checks if the size of two graphs is  equal.
 * 
 * @author Sam Wilson
 */
public class SizePrecondition implements Precondition {

	@Override
	public boolean evaluate(GraphInterface<?, ?> g1, GraphInterface<?, ?> g2) {
		return g1.getSize() == g2.getSize();
	}

}

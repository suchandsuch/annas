package annas.graph;

import annas.util.EqualityChecker;

public class DirectedEdgeEqualityChecker<V, E extends EdgeInterface<V>>
		implements EqualityChecker<E> {

	@SuppressWarnings("unchecked")
	@Override
	public boolean check(Object a, Object b) {
		E e1 = (E) a;
		E e2 = (E) b;

		boolean oneway = e1.getHead().equals(e2.getHead())
				&& e1.getTail().equals(e2.getTail());

		boolean sameObject = e1 == e2;

		return (oneway) && sameObject;
	}

}

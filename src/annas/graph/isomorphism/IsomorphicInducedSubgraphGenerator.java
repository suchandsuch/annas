package annas.graph.isomorphism;

import java.util.Iterator;
import java.util.List;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

public class IsomorphicInducedSubgraphGenerator<V, E extends EdgeInterface<V>>
		implements Iterable<List<V>> {

	private GraphInterface<V, E> target;

	private GraphInterface<V, E> src;

	public IsomorphicInducedSubgraphGenerator(GraphInterface<V, E> target,
			GraphInterface<V, E> src) {
		super();
		this.target = target;
		this.src = src;
	}

	@Override
	public Iterator<List<V>> iterator() {
		return new IsomorphicInducedSubgraphIterator<V, E>(target, src);
	}

}

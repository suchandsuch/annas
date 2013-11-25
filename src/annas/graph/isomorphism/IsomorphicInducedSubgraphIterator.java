package annas.graph.isomorphism;

import java.util.Iterator;
import java.util.List;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.math.combinatorics.SimpleCombinationGenerator;

public class IsomorphicInducedSubgraphIterator<V, E extends EdgeInterface<V>>
		implements Iterator<List<V>> {

	private Iterator<List<V>> comb;

	private SubgraphIsomorphism<V, E> si;

	public IsomorphicInducedSubgraphIterator(GraphInterface<V, E> target,
			GraphInterface<V, E> src) {
		super();
		SimpleCombinationGenerator<V> g = new SimpleCombinationGenerator<V>(
				target.getVertices(), src.getOrder());
		this.comb = g.iterator();

		this.si = new SubgraphIsomorphism<V, E>(target, src);
	}

	/**
	 * Returns true if there is a possibility that there is another isomorphic 
	 * induced subgraph. There is a possibility that this method returns true when
	 * the iterator has no next item, this behavior is guaranteed to only occur on the last 
	 * element of the iterator.
	 */
	@Override
	public boolean hasNext() {
		return this.comb.hasNext();
	}

	@Override
	/**
	 * Returns a set of vertices which are an induced subgraph. Returns null if 
	 * there are no more isomorphic induced subgraphs.
	 */
	public List<V> next() {
		List<V> vertices;
		while (this.comb.hasNext()) {
			vertices = this.comb.next();
			if (si.isInducedSubgraph(vertices)) {
				return vertices;
			}
		}
		return null;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}

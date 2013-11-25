package annas.graph.isomorphism;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.util.InducedSubgraph;
import annas.math.combinatorics.PermutationGenerator;

/**
 * 
 * @author Sam Wilson
 * 
 */
public class SubgraphIsomorphism<V, E extends EdgeInterface<V>> {

	private GraphInterface<V, E> target;

	private GraphInterface<V, E> src;

	public SubgraphIsomorphism(GraphInterface<V, E> target,
			GraphInterface<V, E> src) {
		super();
		this.target = target;
		this.src = src;
	}

	public boolean isInducedSubgraph(Collection<V> vertices) {
		return this.isInducedSubgraph(this.target, this.src, vertices);
	}

	private boolean isInducedSubgraph(
			GraphInterface<V, ? extends EdgeInterface<V>> target,
			GraphInterface<V, ? extends EdgeInterface<V>> src,
			Collection<V> vertices) {
		if (src.getOrder() != vertices.size()) {
			throw new IllegalArgumentException(
					"Order of src should be equal to size of vertex set");
		}

		GraphInterface<V, ? extends EdgeInterface<V>> id = InducedSubgraph
				.inducedSubgraphOf(target, vertices);

		PermutationGenerator<V> perm = new PermutationGenerator<V>(vertices);
		for (List<V> ll : perm)
			if (subgraphEquality(id, src, ll)) {
				return true;
			}

		return false;
	}

	private boolean subgraphEquality(
			GraphInterface<V, ? extends EdgeInterface<V>> target,
			GraphInterface<V, ? extends EdgeInterface<V>> src, List<V> vertices) {
		if (src.getOrder() != target.getOrder()
				&& src.getSize() != target.getSize()) {
			return false;
		}

		List<V> lst = new ArrayList<V>(src.getVertices());
		for (V v : lst) {

			for (V u : lst) {
				if (src.getEdges(v, u).size() != target.getEdges(
						vertices.get(lst.indexOf(v)),
						vertices.get(lst.indexOf(u))).size()) {
					return false;
				}
			}
		}

		return true;
	}
}

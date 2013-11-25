package annas.graph.classifier;

import java.util.Collection;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.util.Utilities;
import annas.graph.util.traverse.BreadthFirst;

/**
 * Recognizes undirected bipartite graphs
 * @author scsswi
 *
 * @param <V>
 * @param <E>
 */
public class BipartiteRecogniser<V, E extends EdgeInterface<V>> implements
		Classifier<V, E> {

	@Override
	public boolean classify(GraphInterface<V, E> graph) {
		boolean retval = true;
		for(Collection<V> c : Utilities.getConnectedComponents(graph)){
			retval &= new BreadthFirst<V, E>(graph,c.iterator().next()).isBipartite();
		}
		return retval;
		
	}

}

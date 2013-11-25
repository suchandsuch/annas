package annas.graph.classifier;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.util.Utilities;
import annas.math.Matrix;

/**
 * 
 * @author scsswi
 * 
 * @param <V>
 * @param <E>
 */
public class TriangleFreeRecogniser<V, E extends EdgeInterface<V>> implements
		Classifier<V, E> {

	@Override
	public boolean classify(GraphInterface<V, E> graph) {
		Matrix m = Utilities.getAdjacencyMatrix(graph);

		m = m.pow(3);
		if (m.Trace() != 0)
			return false;
		return true;
	}
}

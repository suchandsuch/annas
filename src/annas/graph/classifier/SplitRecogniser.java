package annas.graph.classifier;

import java.util.Arrays;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.util.Utilities;

/**
 * Recognizes Split graphs from the degree sequence characteristic.
 * 
 * @author Sam Wilson
 * 
 * @param <V>
 * @param <E>
 */
public class SplitRecogniser<V, E extends EdgeInterface<V>> implements
		Classifier<V, E> {

	@Override
	public boolean classify(GraphInterface<V, E> graph) {
		if (graph.getOrder() == 0) {
			return true;
		}
		long[] ds = Utilities.getDegreeSequence(graph);
		Arrays.sort(ds);
		reverse(ds);

		int m = 0;
		for (int i = 1; i <= ds.length; i++) {
			if (ds[i - 1] >= i) {
				m = i - 1;
			} else {
				break;
			}
		}
		long sum = sum(ds, 0, m + 1);
		long f = m * (m + 1);
		long l = sum(ds, m + 1, ds.length);
		return sum == f + l;
	}

	private long sum(long[] d, int start, int end) {
		long retval = 0;
		for (int i = start; i < end; i++) {
			retval += d[i];
		}
		return retval;
	}

	private void reverse(long[] d) {

		for (int i = 0; i < d.length / 2; i++) {
			long tmp = d[i];
			d[i] = d[d.length - i - 1];
			d[d.length - i - 1] = tmp;
		}
	}

}

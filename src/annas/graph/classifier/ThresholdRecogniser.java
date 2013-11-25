package annas.graph.classifier;

import java.util.Arrays;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.util.Utilities;

public class ThresholdRecogniser<V, E extends EdgeInterface<V>> implements
		Classifier<V, E> {

	@Override
	public boolean classify(GraphInterface<V, E> graph) {
		long[] ds = Utilities.getDegreeSequence(graph);
		Arrays.sort(ds);

		while (ds.length > 0) {
			if (ds[0] <= 0) {
				ds = Arrays.copyOfRange(ds, 1, ds.length);
				continue;
			}
			long n = ds.length;
			if (ds[ds.length - 1] != n - 1) {
				return false;
			} else {
				ds = Arrays.copyOfRange(ds, 0, ds.length - 1);
				this.decrementBy(ds, 1);

			}

		}
		return true;
	}

	private void decrementBy(long[] ds, long decrementBy) {
		for (int i = 0; i < ds.length; i++) {
			ds[i] = ds[i] - decrementBy;
		}
	}
}

package annas.graph.classifier;

import java.util.ArrayList;
import java.util.List;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.util.traverse.LexBFS;

public class ChordalRecogniser<V, E extends EdgeInterface<V>> implements
		Classifier<V, E> {

	public boolean check(GraphInterface<V, E> graph) {
		LexBFS<V, E> lex = new LexBFS<V, E>(graph);
		lex.run();
		List<V> out = lex.getOutput();
		System.out.println(out);

		for (int i = 1; i < out.size(); i++) {
			V nearest = getNearestNeighbour(graph, out, out.get(i));
			if (nearest != null) {
				System.out.println(out.get(i) + " " + nearest);
				List<V> v_n = this.neighboursOf(graph, out, out.get(i));
				v_n.remove(nearest);
				List<V> w_n = this.neighboursOf(graph, out, nearest);
				System.out.println("V_n " + v_n);
				System.out.println("W_n " + w_n);

				if (!w_n.containsAll(v_n) && !v_n.isEmpty() && !w_n.isEmpty()) {
					System.out.println("FALSE");
					return false;
				}
			}
		}
		System.out.println("TRUE");
		return true;
	}

	private List<V> neighboursOf(GraphInterface<V, E> graph, List<V> out, V v) {
		ArrayList<V> l = new ArrayList<V>();
		int i = out.indexOf(v);

		for (int j = i - 1; j >= 0; j--) {
			V ret = out.get(j);
			if (graph.getEdges(v, ret).size() != 0) {
				l.add(ret);
			}
		}
		return l;
	}

	private V getNearestNeighbour(GraphInterface<V, E> graph, List<V> out, V n) {
		int i = out.indexOf(n);

		for (int j = i; j >= 0; j--) {
			V ret = out.get(j);
			if (graph.getEdges(n, ret).size() != 0) {
				return ret;
			}
		}
		return null;
	}

	@Override
	public boolean classify(GraphInterface<V, E> graph) {
		return check(graph);
	}

}

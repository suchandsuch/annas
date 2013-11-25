package annas.graph.util.traverse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

public class LexBFS<V, E extends EdgeInterface<V>> implements Traversal<V, E> {

	private GraphInterface<V, E> graph;

	private List<V> output;

	public LexBFS(GraphInterface<V, E> graph) {
		super();
		this.graph = graph;
		this.output = new ArrayList<V>(this.graph.getOrder());
	}

	public void run() {
		ArrayList<List<V>> input = new ArrayList<List<V>>();
		input.add(new ArrayList<V>(graph.getVertices()));
		lexbfs(input);

	}

	public List<V> getOutput() {
		return output;
	}

	private void lexbfs(List<List<V>> input) {

		while (!input.isEmpty()) {
			V vertex = input.get(0).remove(0);
			if (input.get(0).isEmpty()) {
				input.remove(0);
			}

			this.output.add(vertex);
			ArrayList<List<V>> input_new = new ArrayList<List<V>>();
			for (int i = 0; i < input.size(); i++) {
				ArrayList<V> tmp_in = new ArrayList<V>();
				ArrayList<V> tmp_out = new ArrayList<V>();

				for (int j = 0; j < input.get(i).size(); j++) {
					if (this.graph.getEdges(vertex, input.get(i).get(j)).size() != 0) {
						tmp_in.add(input.get(i).get(j));
					} else {
						tmp_out.add(input.get(i).get(j));
					}
				}
				if (!tmp_in.isEmpty())
					input_new.add(tmp_in);

				if (!tmp_out.isEmpty())
					input_new.add(tmp_out);
			}
			input = input_new;
		}
	}

	@SuppressWarnings("unused")
	private void lexbfs_complement(List<List<V>> input) {

		while (!input.isEmpty()) {
			V vertex = input.get(0).remove(0);
			if (input.get(0).isEmpty()) {
				input.remove(0);
			}

			this.output.add(vertex);
			ArrayList<List<V>> input_new = new ArrayList<List<V>>();
			for (int i = 0; i < input.size(); i++) {
				ArrayList<V> tmp_in = new ArrayList<V>();
				ArrayList<V> tmp_out = new ArrayList<V>();

				for (int j = 0; j < input.get(i).size(); j++) {
					if (this.graph.getEdges(vertex, input.get(i).get(j)).size() != 0) {
						tmp_in.add(input.get(i).get(j));
					} else {
						tmp_out.add(input.get(i).get(j));
					}
				}
				if (!tmp_out.isEmpty())
					input_new.add(tmp_out);
				if (!tmp_in.isEmpty())
					input_new.add(tmp_in);

			}
			input = input_new;
		}
	}


	@Override
	public Iterator<V> getTraversal() {
		return this.output.iterator();
	}

	@Override
	public Iterator<V> iterator() {
		return this.output.iterator();
	}

}

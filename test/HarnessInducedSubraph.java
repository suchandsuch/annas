import java.util.List;

import annas.graph.DefaultEdge;
import annas.graph.UndirectedGraph;
import annas.graph.isomorphism.IsomorphicInducedSubgraphGenerator;

public class HarnessInducedSubraph {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UndirectedGraph<String, DefaultEdge> src = new UndirectedGraph<String, DefaultEdge>(
				DefaultEdge.class);

		src.addVertex("A");
		src.addVertex("B");
		src.addVertex("C");
		// src.addVertex("D");

		src.addEdge("A", "B");
		src.addEdge("B", "C");
		//src.addEdge("C", "A");
		// src.addEdge("D","A");

		UndirectedGraph<String, DefaultEdge> target = new UndirectedGraph<String, DefaultEdge>(
				DefaultEdge.class);

		target.addVertex("A");
		target.addVertex("B");
		target.addVertex("C");
		target.addVertex("D");
		target.addVertex("E");
		target.addVertex("F");

		target.addEdge("A", "B");
		target.addEdge("B", "C");
		target.addEdge("C", "D");
		target.addEdge("D", "E");
		target.addEdge("E", "F");
		target.addEdge("A", "C");
		target.addEdge("D", "B");

		IsomorphicInducedSubgraphGenerator<String, DefaultEdge> iisg = new IsomorphicInducedSubgraphGenerator<String, DefaultEdge>(
				target, src);

		for (List<String> l : iisg) {
			System.out.println(l);
		}

	}

}

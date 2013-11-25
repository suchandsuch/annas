import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;

import annas.graph.GraphInterface;
import annas.graph.IntegerEdge;
import annas.graph.SimpleUndirectedGraph;
import annas.graph.util.Utilities;

public class RootedSubTreeIso {

	public static void main(String[] args) {
		GraphInterface<Integer, IntegerEdge> S = new SimpleUndirectedGraph<Integer, IntegerEdge>(
				IntegerEdge.class);
		GraphInterface<Integer, IntegerEdge> T = new SimpleUndirectedGraph<Integer, IntegerEdge>(
				IntegerEdge.class);

		for (int i = 0; i < 6; i++)
			S.addVertex(i);

		S.addEdge(0, 1);
		S.addEdge(0, 2);
		S.addEdge(2, 3);
		S.addEdge(2, 4);
		S.addEdge(4, 5);

		for (int i = 0; i < 9; i++)
			T.addVertex(i);

		T.addEdge(0, 1);
		T.addEdge(0, 2);
		T.addEdge(2, 3);
		T.addEdge(2, 4);
		T.addEdge(2, 5);
		T.addEdge(4, 6);
		T.addEdge(6, 7);
		T.addEdge(6, 8);

		rootedSubTreeIso(S, 0, T, 0);
	}

	public static boolean rootedSubTreeIso(
			GraphInterface<Integer, IntegerEdge> S, Integer s,
			GraphInterface<Integer, IntegerEdge> T, Integer t) {

		Collection<Integer> leafs = Utilities.getVerticesOfDegree(T, 1);
		Collection<Integer> l = Utilities.getVerticesOfDegree(S, 1);

		Hashtable<Integer, Collection<Integer>> table = new Hashtable<Integer, Collection<Integer>>();

		Hashtable<Integer, Integer> depths = processDepth(T, 0);

		int max_depth = Collections.max(depths.values());
		for (Integer i : leafs) {
			table.put(i, l);
		}

		return table.get(t).contains(s);

	}

	/**
	 * Below is stuff for calculating the distance of each vertex from the root
	 * of the tree
	 */

	private static Hashtable<Integer, Integer> processDepth(
			GraphInterface<Integer, IntegerEdge> g, Integer root) {
		Hashtable<Integer, Integer> depths = new Hashtable<Integer, Integer>();

		depths.put(0, 0);
		rec(g, depths, -1, root, 1);
		return depths;
	}

	private static void rec(GraphInterface<Integer, IntegerEdge> g,
			Hashtable<Integer, Integer> depths, int parent, int current,
			int depth) {

		for (IntegerEdge e : g.getEdges(current)) {
			int otherEnd = otherEndPoint(e, current);
			if (otherEnd != parent) {
				depths.put(otherEnd, depth);
				rec(g, depths, current, otherEnd, depth + 1);
			}
		}
	}

	private static int otherEndPoint(IntegerEdge e, int h) {
		if (e.getHead() == h) {
			return e.getTail();
		} else {
			return e.getHead();
		}
	}
}

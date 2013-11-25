package annas.graph.util;

import java.util.ArrayList;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.WeightedEdgeInterface;
import annas.misc.GraphPath;

/**
 * Determines all pair shortest paths, as described <a
 * href="http://mathworld.wolfram.com/Floyd-WarshallAlgorithm.html"> here</a>
 * 
 * @author Sam Wilson
 * 
 * @param <V>
 *            Vertex type
 * @param <E>
 *            Edge type
 */
public class Floyd<V, E extends EdgeInterface<V>> {

	private final double DEFAULT_EDGE_WEIGHT = 1.0d;

	/**
	 * Graph to perform the algorithm on.
	 */
	private GraphInterface<V, E> g;

	/**
	 * Integer array containing the route from source to destination.
	 */
	private int[][] R;

	/**
	 * Double array containing the distance from source to destination.
	 */
	private double[][] D;

	/**
	 * Default constructor
	 * 
	 * @param g
	 *            Graph o perform algorithm on.
	 */
	public Floyd(GraphInterface<V, E> g) {
		super();
		this.g = g;
		makeMatrix();
		run();

	}

	/**
	 * The route matrix for the current graph.
	 * 
	 * @return The route matrix.
	 */
	public int[][] getR() {
		return R;
	}

	/**
	 * The distance matrix for the current graph.
	 * 
	 * @return distance matrix
	 */
	public double[][] getD() {
		return D;
	}

	/**
	 * Finds the distance between the two nodes, by looking in the distance
	 * matrix.
	 * 
	 * @param a
	 *            Source
	 * @param b
	 *            Destination
	 * @return Distance between the nodes.
	 */
	public double getDistance(V a, V b) {
		return D[indexOf(a)][indexOf(b)];
	}

	/**
	 * Find the route through the Graph from the source node to the destination
	 * node, by using the route matrix.
	 * 
	 * @param a
	 *            Source
	 * @param b
	 *            Destination
	 * @return Graph of the route between the two nodes.
	 */
	public GraphPath<V, E> getRoute(V a, V b) {
		GraphPath<V, E> m = new GraphPath<V, E>();
		V t = a;
		int col = indexOf(b);
		int row = indexOf(a);
		int temp = row;

		ArrayList<V> fg = new ArrayList<V>(this.g.getVertices());

		while (temp != col) {
			row = R[col][row] - 1;

			m.addEdge(this.g.getEdges(t, fg.get(row)).iterator().next());
			t = fg.get(row);
			temp = row;
		}

		return m;
	}

	/**
	 * Runs the algorithm that finds the shortest path between all pairs.
	 */
	private void run() {
		int loc;
		for (int it = 1; it <= this.D.length; it++) {
			loc = it - 1;

			for (int x = 0; x < this.D.length; x++) {
				for (int y = 0; y < this.D[0].length; y++) {
					if ((D[loc][y] + D[x][loc] < D[x][y])
							&& (x != loc || y != loc)) {
						D[x][y] = D[it - 1][y] + D[x][loc];
						this.R[x][y] = this.R[loc][y];

					}
				}
			}

		}
	}

	private int indexOf(V vertex) {
		int index = 0;
		for (V v : this.g.getVertices()) {
			if (v == vertex) {
				return index;
			}
			index++;
		}
		return -1;
	}

	/**
	 * Sets up the matrices, and converts from the adjacent list format into
	 * distance matrix.
	 */
	private void makeMatrix() {
		int SIZE = this.g.getOrder();
		D = new double[SIZE][SIZE];
		R = new int[SIZE][SIZE];

		for (int i = 0; i < R.length; i++) {
			for (int j = 0; j < R[0].length; j++) {
				R[i][j] = i + 1;
			}
		}

		for (int i = 0; i < D.length; i++) {
			for (int j = 0; j < D[0].length; j++) {
				D[i][j] = Double.MAX_VALUE;
			}
		}

		for (V current : this.g.getVertices()) {
			int x = indexOf(current);

			for (E edge : this.g.getEdges(current)) {

				D[x][indexOf(edge.getOtherEndpoint(current))] = getWeight(edge);

			}

		}

	}

	private double getWeight(E edge) {
		if (edge instanceof WeightedEdgeInterface<?>) {
			return ((WeightedEdgeInterface<?>) edge).getWeight();
		}

		return DEFAULT_EDGE_WEIGHT;
	}

}

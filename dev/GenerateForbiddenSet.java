import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.IntegerEdge;
import annas.graph.SimpleUndirectedGraph;
import annas.graph.classifier.AlmostC;
import annas.graph.classifier.Classifier;
import annas.graph.classifier.DisjointCliqueRecogniser;
import annas.graph.io.DOTExporter;
import annas.graph.io.GraphExporter;
import annas.math.Matrix;

public class GenerateForbiddenSet<V, E extends EdgeInterface<V>> {

	public Classifier<V, E> classifier;

	public int MaxSize;

	public GenerateForbiddenSet(Classifier<V, E> classifier, int MaxSize) {
		super();
		this.classifier = classifier;
		this.MaxSize = MaxSize;
	}

	/**
	 * Generates the list of forbidden graphs, will contain duplicates.
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<GraphInterface<V, E>> generate() throws IOException {
		List<GraphInterface<V, E>> forb = new ArrayList<GraphInterface<V, E>>();

		for (int i = 2; i <= this.MaxSize; i++) {
			BufferedReader br = new BufferedReader(new FileReader("graph" + i
					+ ".g6"));
			String line;
			while ((line = br.readLine()) != null) {
				GraphInterface<Integer, IntegerEdge> g = Fromg6.fromg6(line);

				if (!classifier.classify((GraphInterface<V, E>) g)) {
					findMinimalForbidden<Integer, IntegerEdge> fmf = new findMinimalForbidden<Integer, IntegerEdge>(
							(Classifier<Integer, IntegerEdge>) classifier);
					GraphInterface<Integer, IntegerEdge> tmp = fmf
							.find((SimpleUndirectedGraph<Integer, IntegerEdge>) g);

					if (g.getOrder() == tmp.getOrder()) {
						forb.add((GraphInterface<V, E>) tmp);
					}
				} else {

				}

			}
			br.close();
		}
		return forb;
	}

	public static void main(String[] args) throws IOException {
		
		long t1 = System.currentTimeMillis();
		/*
		 * GenerateForbiddenSet<Integer, IntegerEdge> gfs = new
		 * GenerateForbiddenSet<Integer, IntegerEdge>( new
		 * SplitRecogniser<Integer, IntegerEdge>(), 6);
		 */

		/*
		 * GenerateForbiddenSet<Integer, IntegerEdge> gfs = new
		 * GenerateForbiddenSet<Integer, IntegerEdge>( new
		 * ThresholdRecogniser<Integer, IntegerEdge>(), 6);
		 */

		Classifier<Integer, IntegerEdge> ap3 = new AlmostC<Integer, IntegerEdge>(
				new DisjointCliqueRecogniser<Integer, IntegerEdge>(), 1);
		
		GenerateForbiddenSet<Integer, IntegerEdge> gfs = new GenerateForbiddenSet<Integer, IntegerEdge>(
				ap3, 10);

		GraphExporter<Integer, IntegerEdge> ge = new DOTExporter<Integer, IntegerEdge>();
		for (GraphInterface<Integer, IntegerEdge> g : gfs.generate()) {
			//System.out.println(g);
			 ge.exportGraph(g);
		}
		long t2 = System.currentTimeMillis();
		System.out.println((t2-t1)/100 + " Seconds");
	}

	private static class Fromg6 {

		public static GraphInterface<Integer, IntegerEdge> fromg6(String str) {
			Matrix m = decodeGraph(StringToIntArray(str));

			GraphInterface<Integer, IntegerEdge> graph = new SimpleUndirectedGraph<Integer, IntegerEdge>(
					IntegerEdge.class);

			for (int i = 0; i < m.getMatrix().length; i++) {
				graph.addVertex(i);
			}

			double[][] ma = m.getMatrix();
			for (int i = 0; i < ma.length; i++) {
				for (int j = 0; j < ma.length; j++) {
					if (ma[i][j] == 1) {
						graph.addEdge(i, j);
					}
				}
			}

			return graph;
		}

		private static Matrix decodeGraph(int[] i) {
			long nuNodes = decodeN(i);
			String a = "";
			if (nuNodes <= 62) {
				a = decodeR(Arrays.copyOfRange(i, 1, i.length));
			} else if (nuNodes > 62 && nuNodes <= 258047) {
				a = decodeR(Arrays.copyOfRange(i, 4, i.length));
			} else {
				a = decodeR(Arrays.copyOfRange(i, 8, i.length));
			}

			int[][] adj = new int[(int) nuNodes][(int) nuNodes];

			int q = 0;
			for (int w = 0; w < nuNodes; w++) {
				for (int e = 0; e < w; e++) {
					adj[w][e] = Integer.parseInt((a.charAt(q)) + "");
					q++;
				}
			}

			return new Matrix(adj);
		}

		private static long decodeN(int i[]) {
			if (i.length > 2 && i[0] == 126 && i[1] == 126) {
				return Long.parseLong(decodeR(new int[] { i[2], i[3], i[4],
						i[5], i[6], i[7] }), 2);
			} else if (i.length > 1 && i[0] == 126) {
				return Long.parseLong(decodeR(new int[] { i[1], i[2], i[3] }),
						2);
			} else {
				return i[0] - 63;
			}
		}

		private static String decodeR(int[] bytes) {
			String retval = "";
			for (int i = 0; i < bytes.length; i++) {
				retval += padL(Integer.toBinaryString(bytes[i] - 63), 6);
			}
			return retval;
		}

		private static String padL(String str, int h) {
			String retval = "";
			for (int i = 0; i < h - str.length(); i++) {
				retval += "0";
			}
			return retval + str;
		}

		private static int[] StringToIntArray(String str) {
			int[] v = new int[str.length()];
			for (int l = 0; l < str.length(); l++) {
				v[l] = str.charAt(l);
			}
			return v;
		}

	}
}

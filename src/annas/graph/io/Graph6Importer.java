package annas.graph.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import annas.graph.GraphInterface;
import annas.graph.IntegerEdge;
import annas.math.Matrix;

public class Graph6Importer implements GraphImporter<Integer, IntegerEdge> {

	@Override
	public GraphInterface<Integer, IntegerEdge> importGraph(InputStream input,
			GraphInterface<Integer, IntegerEdge> graph) {
		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		try {
			String line = in.readLine();

			
			int[] r = StringToIntArray(line);
			Matrix m = decodeGraph(r);
			for (int h = 0; h < m.getMatrix().length; h++) {
				graph.addVertex(h);
			}

			for (int i = 0; i < m.getMatrix().length; i++) {
				for (int j = 0; j < i; j++) {
					if (m.getMatrix()[i][j] != 0) {
						graph.addEdge(i, j);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return graph;
	}

	@Override
	public GraphInterface<Integer, IntegerEdge> importGraph(String filename,
			GraphInterface<Integer, IntegerEdge> graph) {
		try {
			return this.importGraph(new FileInputStream(filename), graph);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return graph;
	}

	private Matrix decodeGraph(int[] i) {
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

	private String decodeR(int[] bytes) {
		String retval = "";
		for (int i = 0; i < bytes.length; i++) {
			retval += padL(Integer.toBinaryString(bytes[i] - 63), 6);
		}
		return retval;
	}

	private long decodeN(int i[]) {
		if (i.length > 2 && i[0] == 126 && i[1] == 126) {
			return Long.parseLong(decodeR(new int[] { i[2], i[3], i[4], i[5],
					i[6], i[7] }), 2);
		} else if (i.length > 1 && i[0] == 126) {
			return Long.parseLong(decodeR(new int[] { i[1], i[2], i[3] }), 2);
		} else {
			return i[0] - 63;
		}
	}

	private int[] StringToIntArray(String str) {
		int[] v = new int[str.length()];
		for (int l = 0; l < str.length(); l++) {
			v[l] = str.charAt(l);
		}
		return v;
	}

	private String padL(String str, int h) {
		String retval = "";
		for (int i = 0; i < h - str.length(); i++) {
			retval += "0";
		}
		return retval + str;
	}

}

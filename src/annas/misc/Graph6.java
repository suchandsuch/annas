package annas.misc;

import java.util.Arrays;

import annas.graph.GraphInterface;
import annas.graph.IntegerEdge;
import annas.graph.SimpleUndirectedGraph;
import annas.graph.util.Utilities;
import annas.math.Matrix;

/**
 * Encodes and decodes graphs in graph6 format.
 * 
 * @author Sam Wilson
 * @version v1.0
 */
public class Graph6 {

    /**
     * Given a string representing a graph in graph6 format this method returns
     * a Simple Undircted graph.
     * 
     * @param entry
     *            String representing a graph in graph6 format.
     * @return the graph represented by the given string
     */
    public static GraphInterface<Integer, IntegerEdge> decodeGraph(String entry) {
	SimpleUndirectedGraph<Integer, IntegerEdge> graph = new SimpleUndirectedGraph<>(
		IntegerEdge.class);
	Matrix m = Graph6.decodeGraph(StringToIntArray(entry));
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
	return graph;
    }

    /**
     * This method given a graph produces a string representing the graph in
     * graph6 file format.
     * 
     * @param graph
     * @return String representing the graph in graph6 file format.
     */
    public static String encodeGraph(GraphInterface<?, ?> graph) {
	double[][] m = Utilities.getAdjacencyMatrix(graph).getMatrix();
	String adjmatrix = "";

	for (int i = 0; i < m.length; i++) {
	    for (int j = 1 + i; j < m.length; j++) {
		if (m[i][j] != 0) {
		    adjmatrix += "1";
		} else {
		    adjmatrix += "0";
		}
	    }
	}
	return Graph6.encodeGraph(graph.getOrder(), adjmatrix);
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

    private static String encodeGraph(int NoNodes, String adjmatrix) {
	String rv = "";
	int[] nn = encodeN(NoNodes);
	int[] adj = encodeR(adjmatrix);
	int[] res = new int[nn.length + adj.length];
	System.arraycopy(nn, 0, res, 0, nn.length);
	System.arraycopy(adj, 0, res, nn.length, adj.length);
	for (int i = 0; i < res.length; i++) {
	    rv = rv + (char) res[i];
	}
	return rv;
    }

    private static int[] encodeN(long i) {

	if (0 <= i && i <= 62) {
	    return new int[] { (int) (i + 63) };
	} else if (63 <= i && i <= 258047) {
	    int[] ret = new int[4];
	    ret[0] = 126;
	    int[] g = R(padL(Long.toBinaryString(i), 18));
	    for (int j = 0; j < 3; j++)
		ret[j + 1] = g[j];
	    return ret;
	} else {
	    int[] ret = new int[8];
	    ret[0] = 126;
	    ret[1] = 126;
	    int[] g = R(padL(Long.toBinaryString(i), 36));
	    for (int j = 0; j < 6; j++)
		ret[j + 2] = g[j];
	    return ret;
	}

    }

    private static long decodeN(int i[]) {
	if (i.length > 2 && i[0] == 126 && i[1] == 126) {
	    return Long.parseLong(decodeR(new int[] { i[2], i[3], i[4], i[5],
		    i[6], i[7] }), 2);
	} else if (i.length > 1 && i[0] == 126) {
	    return Long.parseLong(decodeR(new int[] { i[1], i[2], i[3] }), 2);
	} else {
	    return i[0] - 63;
	}
    }

    private static int[] R(String a) {
	int[] bytes = new int[a.length() / 6];
	for (int i = 0; i < a.length() / 6; i++) {
	    bytes[i] = Integer.valueOf(a.substring(i * 6, ((i * 6) + 6)), 2);
	    bytes[i] = (byte) (bytes[i] + 63);
	}

	return bytes;
    }

    private static int[] encodeR(String a) {
	a = padR(a);
	return R(a);
    }

    private static String decodeR(int[] bytes) {
	String retval = "";
	for (int i = 0; i < bytes.length; i++) {
	    retval += padL(Integer.toBinaryString(bytes[i] - 63), 6);
	}
	return retval;
    }

    private static int[] StringToIntArray(String str) {
	int[] v = new int[str.length()];
	for (int l = 0; l < str.length(); l++) {
	    v[l] = str.charAt(l);
	}
	return v;
    }

    private static String padR(String str) {
	int padwith = 6 - (str.length() % 6);
	for (int i = 0; i < padwith; i++) {
	    str += "0";
	}
	return str;
    }

    private static String padL(String str, int h) {
	String retval = "";
	for (int i = 0; i < h - str.length(); i++) {
	    retval += "0";
	}
	return retval + str;
    }
}

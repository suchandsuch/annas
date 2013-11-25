import java.util.Arrays;

import annas.graph.GraphInterface;
import annas.graph.IntegerEdge;
import annas.graph.UndirectedGraph;
import annas.math.Matrix;

public class Fromg6 {
	
	public GraphInterface<Integer, IntegerEdge> fromg6(String str) {
		Matrix m = decodeGraph(StringToIntArray(str));

		GraphInterface<Integer, IntegerEdge> graph = new UndirectedGraph<Integer, IntegerEdge>(
				IntegerEdge.class);
		
		for (int i = 0; i < m.getMatrix().length; i++) {
			graph.addVertex(i);
		}
		
		System.out.println(m);
		double[][] ma = m.getMatrix();
		for(int i = 0; i <ma.length; i++){
			for(int j = 0; j<ma.length; j++){
				if(ma[i][j]==1){
					graph.addEdge(i, j);
				}
			}
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

	private String decodeR(int[] bytes) {
		String retval = "";
		for (int i = 0; i < bytes.length; i++) {
			retval += padL(Integer.toBinaryString(bytes[i] - 63), 6);
		}
		return retval;
	}

	private String padL(String str, int h) {
		String retval = "";
		for (int i = 0; i < h - str.length(); i++) {
			retval += "0";
		}
		return retval + str;
	}

	private int[] StringToIntArray(String str) {
		int[] v = new int[str.length()];
		for (int l = 0; l < str.length(); l++) {
			v[l] = str.charAt(l);
		}
		return v;
	}

}

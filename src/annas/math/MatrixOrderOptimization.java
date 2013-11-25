package annas.math;


/**
 * Calculates the optimal parameterisation for matrix chain product. Implements
 * the Dynamic programming algorithm outlined in Introduction to algorithms (MIT
 * press)
 * 
 * @author Sam Wilson
 */
public class MatrixOrderOptimization {

	/**
	 * Matrices (in order)
	 */
	private Matrix[] matrices;

	/**
	 * Minimum number of multiplications to multiply each subsequence
	 */
	private int[][] m;

	/**
	 * How to parenthesis the sequence of matrices.
	 */
	private int[][] s;

	/**
	 * Finds the optimal solution for multiplying the sequence of matrices.
	 * 
	 * @param p
	 */
	public MatrixOrderOptimization(Matrix[] p) {
		this.matrices = p;
		int[] i = new int[p.length + 1];
		i[0] = p[0].getMatrix().length;
		for (int j = 0; j < p.length; j++) {
			i[j + 1] = p[j].getMatrix()[0].length;
		}
		matrixChainOrder(i);
	}

	private void matrixChainOrder(int... p) {
		int n = p.length - 1;
		m = new int[n][n];
		s = new int[n][n];
		for (int i = 0; i < n; i++) {
			m[i] = new int[n];
			m[i][i] = 0;
			s[i] = new int[n];
		}
		for (int ii = 1; ii < n; ii++) {
			for (int i = 0; i < n - ii; i++) {
				int j = i + ii;
				m[i][j] = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					int q = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
					if (q < m[i][j]) {
						m[i][j] = q;
						s[i][j] = k;
					}
				}
			}
		}
		System.out.println(toString());

	}

	/**
	 * Computes the product of the sequence of matrices.
	 * 
	 * @return product of the sequence
	 */
	public Matrix multiplyChain() {
		return mmo(s, 0, s.length - 1);
	}

	private Matrix mmo(int[][] s, int i, int j) {
		if (i == j) {
			return this.matrices[i];
		}
		Matrix a = mmo(s, i, s[i][j]);
		Matrix b = mmo(s, s[i][j] + 1, j);
		try {
			return a.MultiplyMatrix(b);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

}
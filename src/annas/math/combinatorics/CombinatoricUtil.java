package annas.math.combinatorics;

public class CombinatoricUtil {

	public static long gcd(long l, long m) {
		if (l == 0)
			return m;
		if (m == 0)
			return l;
		if (l == m)
			return l;
		if (l == 1 | m == 1)
			return 1;
		if ((l % 2 == 0) & (m % 2 == 0))
			return 2 * gcd(l / 2, m / 2);
		if ((l % 2 == 0) & (m % 2 != 0))
			return gcd(l / 2, m);
		if ((l % 2 != 0) & (m % 2 == 0))
			return gcd(l, m / 2);
		return gcd(m, Math.abs(l - m));
	}

	public static long lcm(long l, long m) {
		return (l * m) / gcd(l, m);
	}

	public static long nChooseK(long n, long k) {
		if (k == 0) {
			return 1;
		}

		if (n == 0) {
			return 0;
		}
		return nChooseK(n - 1, k - 1) + nChooseK(n - 1, k);
		/**
		 * Swap for a more effective way of calculating n choose k. return
		 * factorial(n) / (factorial(k) * factorial(n - k));
		 */
	}

	public static long permutations(long n, long k) {
		return (long) Math.pow(n, k);
	}

	public static long permutationsNoRepetition(long n, long k) {
		return factorial(n) / factorial(n - k);
	}

	public static long factorial(long n) {
		long val = 1;

		for (int i = 1; i <= n; i++) {
			val *= i;
		}
		return val;
	}

	public static long catalan(long n) {
		return nChooseK(2 * n, n) - nChooseK(2 * n, n + 1);
		// return factorial(2 * n) / (factorial(n + 1) * factorial(n));

	}

	public static long sterling(long n, long k) {
		if (n == 0 && k == 0) {
			return 1;
		}

		if (n == 0 || k == 0) {
			return 0;
		}

		return k * sterling(n - 1, k) + sterling(n - 1, k - 1);
	}

	/**
	 * Returns the number of non-crossing partitions of n elements into k parts
	 * 
	 * @param n
	 * @param k
	 * @return number of non-crossing partitions of an n element set into k parts
	 */
	public static long narayana(long n, long k) {
		return CombinatoricUtil.nChooseK(n, k)
				* CombinatoricUtil.nChooseK(n, k - 1) / n;
	}
}

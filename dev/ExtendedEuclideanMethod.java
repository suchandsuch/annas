public class ExtendedEuclideanMethod {

	public static long[] ExtendedEuclid(long a, long b) {
		long x = 0;
		long y = 1;

		long lastx = 1;
		long lasty = 0;
		long q;
		long tmpa = a;
		long tmpb = b;
		while (b != 0) {
			q = a / b;
			tmpa = b;
			tmpb = a % b;
			lastx = x;
			x = lastx - q * x;
			lasty = y;
			y = lasty - q * y;
			a = tmpa;
			b = tmpb;

		}

		return new long[] { lastx, lasty };

	}

	public static void main(String[] args) {
		long[] l = ExtendedEuclid(240, 46);
		System.out.println(l[0]);
		System.out.println(l[1]);
	}

}

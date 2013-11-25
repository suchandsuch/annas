public class Simple {

    public static void main(String[] args) {
	int k = 0;
	final int j = 0 + k;
	final int l = 0 + j;
	final int m = 0 + l;
	for (int i = 0; i < 10; i++) {
	    k = k + i;
	}
	System.out.println(k);
    }

}

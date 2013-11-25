import java.util.ArrayList;
import java.util.Iterator;

import annas.math.combinatorics.SimpleCombinationGenerator;

public class CombHarness {

    public static void main(String[] args) {
	final ArrayList<Integer> l = new ArrayList<Integer>(5);
	for (int i = 1; i <= 9; i++) {
	    l.add(i);
	}
	final SimpleCombinationGenerator<Integer> scg = new SimpleCombinationGenerator<Integer>(
		l, 2);
	final Iterator it = scg.iterator();
	while (it.hasNext()) {
	    System.out.println(it.next());
	}

    }
}

import java.util.ArrayList;
import java.util.List;

import annas.math.combinatorics.SimpleCombinationGenerator;


public class almostk4free {

	public static void main(String[] args){
		List<String> l = new ArrayList<String>();
		l.add("a");
		l.add("b");
		l.add("c");
		l.add("d");
		l.add("e");
		l.add("f");
		
		SimpleCombinationGenerator<String> scg = new SimpleCombinationGenerator<>(l, 3);
		for(List<String> s : scg){
			System.out.println(s);
		}
	}
}

import annas.graph.GraphInterface;
import annas.graph.IntegerEdge;
import annas.graph.SimpleUndirectedGraph;
import annas.graph.generate.DefaultVertexFactory;
import annas.graph.generate.RandomGraph;




public class Harness_ {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
/*		List<String> s = new ArrayList<String>();
		s.add("red");
		s.add("green");
		s.add("blue");
		//s.add("yellow");
		
		SimpleCombinationGenerator<String> gen = new SimpleCombinationGenerator<String>(s, 2);
		for(List<String> sa : gen){
			System.out.println(sa);
		}
		
		PermutationGenerator<String> gen = new PermutationGenerator<String>(s);
		for(List<String> sa : gen){
			System.out.println(sa);
		}*/
		
		RandomGraph<Integer, IntegerEdge> rg = new RandomGraph<>(5, 2);
		GraphInterface<Integer, IntegerEdge> g = new SimpleUndirectedGraph<>(IntegerEdge.class);
		rg.generateGraph(g, new DefaultVertexFactory(), null);
		System.out.println(g.getOrder());
		System.out.println(g.getSize());
	}

}

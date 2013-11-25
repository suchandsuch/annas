

import annas.graph.IntegerEdge;
import annas.graph.SimpleUndirectedGraph;
import annas.graph.generate.CirculantGraphGenerator;
import annas.graph.generate.DefaultVertexFactory;


public class CirculantHarness {

	public static void main(String[] args) {

		SimpleUndirectedGraph<Integer, IntegerEdge> g = new SimpleUndirectedGraph<Integer, IntegerEdge>(
				IntegerEdge.class);
		
		CirculantGraphGenerator<Integer, IntegerEdge> cg = 
			new CirculantGraphGenerator<Integer, IntegerEdge>(8,1,2,4);
		
		cg.generateGraph(g, new DefaultVertexFactory(),null);
		System.out.println(g.getOrder());
		System.out.println(g.getSize());
		System.out.println(g.getEdges(0, 3).size());
	}

}



import annas.graph.GraphInterface;
import annas.graph.IntegerEdge;
import annas.graph.SimpleUndirectedGraph;
import annas.graph.classifier.AlmostC;
import annas.graph.classifier.SplitRecogniser;

public class Harness {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test_();

	}

	private static void test_() {
		SimpleUndirectedGraph<Integer, IntegerEdge> graph = new SimpleUndirectedGraph<Integer, IntegerEdge>(
				IntegerEdge.class);
	/*	for (int i = 0; i < 8; i++) {
			graph.addVertex(i);
		}

		graph.addEdge(0, 1);
		graph.addEdge(2, 3);
		graph.addEdge(4, 5);*/
		
		for(int i = 0; i <8; i++){
			graph.addVertex(i);
		}
		
		graph.addEdge(0, 1);
		graph.addEdge(2, 1);
		graph.addEdge(2, 3);
		graph.addEdge(3, 0);
		graph.addEdge(4, 5);
		graph.addEdge(6, 7);

		AlmostC<Integer, IntegerEdge> c = new AlmostC<Integer, IntegerEdge>(
				new SplitRecogniser<Integer, IntegerEdge>(), 3);

		System.out.println(c.classify(graph));
	
		findMinimalForbidden<Integer, IntegerEdge> fmf = new findMinimalForbidden<Integer, IntegerEdge>(
				c);
		GraphInterface<Integer, IntegerEdge> g = fmf.find(graph);
		System.out.println(g);
	}
	
	public static void test(){
		SimpleUndirectedGraph<Integer, IntegerEdge> graph = new SimpleUndirectedGraph<Integer, IntegerEdge>(
				IntegerEdge.class);

		
		for(int i = 0; i <4; i++){
			graph.addVertex(i);
		}
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);


		AlmostC<Integer, IntegerEdge> c = new AlmostC<Integer, IntegerEdge>(
				new SplitRecogniser<Integer, IntegerEdge>(),0);

		System.out.println(c.classify(graph));
		System.out.println(new SplitRecogniser<Integer, IntegerEdge>().classify(graph));
	}

}

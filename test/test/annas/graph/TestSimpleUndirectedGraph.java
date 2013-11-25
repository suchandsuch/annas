package test.annas.graph;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import annas.graph.ClassEdgeFactory;
import annas.graph.DefaultEdge;
import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.SimpleUndirectedGraph;


public class TestSimpleUndirectedGraph {
	
	private GraphInterface<String, DefaultEdge> graph;

	private String a;
	private String b;
	private String c;
	private String d;

	@Before
	public void setUp() throws Exception {
		this.graph = new SimpleUndirectedGraph<String, DefaultEdge>(DefaultEdge.class);

		this.a = new String("A");
		this.b = new String("B");
		this.c = new String("C");
		this.d = new String("D");
	}

	@After
	public void tearDown() throws Exception {
		this.a = null;
		this.b = null;

		this.graph = null;
		System.gc();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testAlternativeConstructor(){
		this.graph = new SimpleUndirectedGraph<String, DefaultEdge>(new ClassEdgeFactory(String.class));
		assertTrue(this.graph != null);
	}

	@Test
	public void testAddEdge() {
		this.graph.addVertex(a);
		this.graph.addVertex(b);
		
		assertTrue(this.graph.getOrder() == 2);
		this.graph.addEdge(a, b);
		assertTrue(this.graph.getEdges().size() == 1);
		EdgeInterface<String> e  = this.graph.addEdge(a, b);
		assertNull(e);
		
		assertTrue(this.graph.getEdges().size() == 1);
	}
	
	@Test
	public void testgetEdgesV(){
		this.graph.addVertex(a);
		this.graph.addVertex(b);
		this.graph.addVertex(c);
		this.graph.addVertex(d);

		DefaultEdge e1 = this.graph.addEdge(a, b);
		DefaultEdge e2 = this.graph.addEdge(b, c);
		DefaultEdge e3 = this.graph.addEdge(c, d);
		DefaultEdge e4 = this.graph.addEdge(d, a);
		DefaultEdge e5 = this.graph.addEdge(d, a);

		assertTrue(this.graph.getEdges(a).contains(e1));
		assertTrue(this.graph.getEdges(b).contains(e2));
		assertTrue(this.graph.getEdges(c).contains(e3));
		assertTrue(this.graph.getEdges(d).contains(e4));

		assertTrue(this.graph.getEdges(a).contains(e4));
		assertTrue(this.graph.getEdges(b).contains(e1));
		assertTrue(this.graph.getEdges(c).contains(e2));
		assertTrue(this.graph.getEdges(d).contains(e3));
		
		assertTrue(this.graph.getEdges(a).size() ==2);
		assertNull(e5);
		Collection<DefaultEdge> es = this.graph.getEdges(a);
		assertTrue(es.contains(e1));
		assertTrue(es.contains(e4));
		assertFalse(es.contains(e5));
		assertFalse(es.contains(e3));
		
		DefaultEdge e6 = this.graph.addEdge(a, a);
		assertNull(e6);
		assertFalse(this.graph.getEdges(a).contains(e3));
	}

}

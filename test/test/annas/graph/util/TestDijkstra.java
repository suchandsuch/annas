package test.annas.graph.util;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.DefaultWeightedEdge;
import annas.graph.UndirectedGraph;
import annas.graph.util.Dijkstra;
import annas.misc.GraphPath;

public class TestDijkstra {

	private UndirectedGraph<String, DefaultWeightedEdge> graph;

	private Dijkstra<String, DefaultWeightedEdge> dij;

	String a = "A"; // u0
	String b = "B";// u1
	String c = "C";// u2
	String d = "D";// u3
	String e = "E";// u4
	String f = "F";// u5
	String g = "G";// u5

	GraphPath<String, DefaultWeightedEdge> gp;

	@Before
	public void setUp() throws Exception {

		graph = new UndirectedGraph<String, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);

		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addVertex(d);
		graph.addVertex(e);
		graph.addVertex(f);
		graph.addVertex(g);

		DefaultWeightedEdge e1 = graph.addEdge(a, c);
		e1.setWeight(13);
		e1 = graph.addEdge(a, f);
		e1.setWeight(8);
		e1 = graph.addEdge(a, e);
		e1.setWeight(16);
		e1 = graph.addEdge(b, f);
		e1.setWeight(10);
		e1 = graph.addEdge(b, d);
		e1.setWeight(6);
		e1 = graph.addEdge(c, e);
		e1.setWeight(11);
		e1 = graph.addEdge(c, d);
		e1.setWeight(14);
		e1 = graph.addEdge(d, e);
		e1.setWeight(5);
		e1 = graph.addEdge(d, f);
		e1.setWeight(17);
		e1 = graph.addEdge(e, f);
		e1.setWeight(7);

		dij = new Dijkstra<String, DefaultWeightedEdge>(graph);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void AB() {
		this.gp = dij.execute(a, b);
		assertTrue(gp.getLength() == 3);
		assertTrue(gp.getDistance() == 18);
	}

	@Test
	public void AC() {
		this.gp = dij.execute(a, c);
		assertTrue(gp.getLength() == 2);
		assertTrue(gp.getDistance() == 13);
	}

	@Test
	public void AD() {
		this.gp = dij.execute(a, d);
		assertTrue(gp.getLength() == 4);
		assertTrue(gp.getDistance() == 20);
	}

	@Test
	public void AE() {
		this.gp = dij.execute(a, e);
		assertTrue(gp.getLength() == 3);
		assertTrue(gp.getDistance() == 15);
	}

	@Test
	public void AF() {
		this.gp = dij.execute(a, f);
		assertTrue(gp.getLength() == 2);
		assertTrue(gp.getDistance() == 8);
	}

	@Test
	public void AG() {
		this.gp = dij.execute(a, g);
		assertTrue(gp.getLength() == 0);
		assertTrue(gp.getDistance() == 0);
	}

}

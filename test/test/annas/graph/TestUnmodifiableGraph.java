package test.annas.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import annas.graph.DefaultEdge;
import annas.graph.GraphInterface;
import annas.graph.UndirectedGraph;
import annas.graph.UnmodifiableGraph;

public class TestUnmodifiableGraph {

	private GraphInterface<String, DefaultEdge> graph;

	private String a;
	private String b;
	private String c;
	private String d;

	@Before
	public void setUp() throws Exception {
		this.a = new String("A");
		this.b = new String("B");
		this.c = new String("C");
		this.d = new String("D");

		this.graph = new UndirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		this.graph.addVertex(a);
		this.graph.addVertex(b);
		this.graph.addVertex(c);
		this.graph.addEdge(a, b);
		this.graph = new UnmodifiableGraph<String, DefaultEdge>(this.graph);
	}

	@Test
	public void testUnmodifiableGraph() {
		assertNotNull(this.graph);
	}

	@Test
	public void testAddEdge() {
		try {
			this.graph.addEdge(a, c);
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testAddVertex() {
		try {
			this.graph.addVertex(d);
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testAddVertices() {
		try {
			this.graph.addVertices(null);
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testContainsEdgeVV() {
		assertTrue(this.graph.containsEdge(a, b));
		assertFalse(this.graph.containsEdge(b, c));
	}

	@Test
	public void testContainsEdgeE() {
		assertTrue(this.graph.containsEdge(this.graph.getEdges().iterator().next()));
	}

	@Test
	public void testContainsVertex() {
		assertTrue(this.graph.containsVertex(a));
		assertFalse(this.graph.containsVertex(d));
	}

	@Test
	public void testGetDegree() {
		assertEquals(this.graph.getDegree(a), 1);
	}

	@Test
	public void testGetEdgeFactory() {
		assertNotNull(this.graph.getEdgeFactory());
	}

	@Test
	public void testGetEdgesV() {
		assertEquals(this.graph.getEdges(a).size(), 1);
	}

	@Test
	public void testGetEdgesVV() {
		assertEquals(this.graph.getEdges(a,b).size(), 1);
		assertEquals(this.graph.getEdges(a, c).size(), 0);
	}

	@Test
	public void testGetEdges() {
		assertEquals(this.graph.getEdges().size(), 1);
	}

	@Test
	public void testGetObserver() {
		assertNull(this.graph.getObserver());
	}

	@Test
	public void testGetOrder() {
		assertEquals(this.graph.getOrder(), 3);
	}

	@Test
	public void testGetSize() {
		assertEquals(this.graph.getSize(), 1);
	}

	@Test
	public void testGetVertices() {
		assertEquals(this.graph.getVertices().size(), 3);
	}

	@Test
	public void testRemoveEdgeE() {
		try {
			this.graph.removeEdge(this.graph.getEdges().iterator().next());
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testRemoveEdgeVV() {
		try {
			this.graph.removeEdge(b, c);
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testRemoveEdgeV() {
		try {
			this.graph.removeEdge(c);
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testRemoveEdges() {
		try {
			this.graph.removeEdges(null);
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testRemoveVertex() {
		try {
			this.graph.removeVertex(d);
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testRemoveVertices() {
		try {
			this.graph.removeVertices(null);
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testResetEdges() {
		try {
			this.graph.resetEdges();
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testSetObserver() {
		try {
			this.graph.setObserver(null);
		} catch (UnsupportedOperationException e) {
			assertTrue(true);
		}
	}

}

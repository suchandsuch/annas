package annas.graph.util;

import java.util.Comparator;
import java.util.PriorityQueue;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.UndirectedGraph;
import annas.graph.WeightedEdgeInterface;
import annas.util.DisjointSet;

public class Kruskal<V, E extends EdgeInterface<V>> implements MinimumSpanningTree<V, E> {
	
	private final double DEFAULT_EDGE_WEIGHT = 1d;
	
	private GraphInterface<V, E> graph;
	
	private PriorityQueue<E> EdgeList;

	public Kruskal(GraphInterface<V, E> g) {
		this.graph = g;
		this.EdgeList = new PriorityQueue<E>(graph.getSize(),
				new DoubleFieldComparator());
	}

	@Override
	public GraphInterface<V, E> execute() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		GraphInterface<V, E> tree = new UndirectedGraph(this.graph.getEdgeClass());
		DisjointSet<V> ds = new DisjointSet<>(this.graph.getVertices());
		
		this.EdgeList.addAll(this.graph.getEdges());
		
		
		for(E edge : this.EdgeList){
			if(!ds.findSet(edge.getHead()).equals(ds.findSet(edge.getTail()))){
				tree.addVertex(edge.getHead());
				tree.addVertex(edge.getTail());
				EdgeInterface<V> e = tree.addEdge(edge.getTail(), edge.getHead());
				if(edge instanceof WeightedEdgeInterface){
					((WeightedEdgeInterface<V>) e).setWeight(((WeightedEdgeInterface<V>) edge).getWeight());
				} 
				ds.union(edge.getHead(), edge.getTail());
			}
		}
		
		
		return tree;
	}
	
	@Override
	public double getCost() {
		GraphInterface<?, E> graph = this.execute();
		double d = 0;
		for(E edge : graph.getEdges()){
			if (edge instanceof WeightedEdgeInterface<?>) {
				d += ((WeightedEdgeInterface<?>) edge).getWeight();
			}else{
				d += 1;
			}
		}
		
		return d;
	}
	
	class DoubleFieldComparator implements Comparator<E> {

		@Override
		public int compare(E arg0, E arg1) {
			Double d = DEFAULT_EDGE_WEIGHT;
			Double e = DEFAULT_EDGE_WEIGHT;
			if (arg0 instanceof WeightedEdgeInterface<?>) {
				d = ((WeightedEdgeInterface<?>) arg0).getWeight();
			}

			if (arg1 instanceof WeightedEdgeInterface<?>) {
				e = ((WeightedEdgeInterface<?>) arg1).getWeight();
			}
			return d.compareTo(e);
		}
	}

}

package annas.graph.io;

import java.io.InputStream;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

public interface GraphImporter<V, E extends EdgeInterface<V>> {

	public GraphInterface<V, E> importGraph(InputStream input,
			GraphInterface<V, E> graph);

	public GraphInterface<V, E> importGraph(String filename,
			GraphInterface<V, E> graph);
}

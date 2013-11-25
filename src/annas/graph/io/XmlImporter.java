package annas.graph.io;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

/**
 * Imports a graph from xml, as exported by XmlExporter
 * 
 * @author Sam Wilson
 * 
 * @param <V>
 *            Vertex type
 * @param <E>
 *            Edge type
 */
public class XmlImporter<V, E extends EdgeInterface<V>> implements
		GraphImporter<V, E> {


	@SuppressWarnings("unchecked")
	@Override
	public GraphInterface<V, E> importGraph(InputStream input,
			GraphInterface<V, E> graph) {
		// TODO Auto-generated method stub
		XMLDecoder d = new XMLDecoder(input);
		Object result = d.readObject();
		d.close();
		graph = (GraphInterface<V, E>) result;
		return graph;
	}

	@Override
	public GraphInterface<V, E> importGraph(String filename,
			GraphInterface<V, E> graph) {
		try {
			return this.importGraph(new BufferedInputStream(
			        new FileInputStream(filename)), graph);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}

package annas.graph.io;

import java.io.OutputStream;
import java.io.PrintWriter;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.misc.Graph6;

public class Graph6Exporter<V, E extends EdgeInterface<V>> implements
	GraphExporter<V, E> {

    @Override
    public void exportGraph(OutputStream writer, GraphInterface<V, E> graph) {
	new PrintWriter(writer, true).println(Graph6.encodeGraph(graph));
    }

    @Override
    public void exportGraph(GraphInterface<V, E> graph) {
	System.out.println(Graph6.encodeGraph(graph));
    }

}

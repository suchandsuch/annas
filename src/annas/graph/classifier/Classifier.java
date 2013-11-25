package annas.graph.classifier;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

public interface Classifier<V, E extends EdgeInterface<V>> {

    /**
     * Classifies if the graph has a property.
     * 
     * @param graph
     * @return true is the graph has the property.
     */
    public boolean classify(GraphInterface<V, E> graph);
}

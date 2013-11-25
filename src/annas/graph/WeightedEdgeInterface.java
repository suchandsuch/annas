package annas.graph;

public interface WeightedEdgeInterface<V> extends EdgeInterface<V> {

	public double getWeight();

	public void setWeight(double weight);
}

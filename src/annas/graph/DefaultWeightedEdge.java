package annas.graph;

@SuppressWarnings("serial")
public class DefaultWeightedEdge<V> implements WeightedEdgeInterface<V> {

	private V head;

	private V tail;

	private double weight;

	public DefaultWeightedEdge() {
		super();
	}

	@Override
	public String toString() {
		return this.tail + "-[" +this.weight + "]->"+ this.head;
	}

	@Override
	public V getHead() {
		return head;
	}

	@Override
	public void setHead(V head) {
		this.head = head;
	}

	@Override
	public V getTail() {
		return tail;
	}

	@Override
	public void setTail(V tail) {
		this.tail = tail;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public boolean isIncident(V vertex) {
		return this.head.equals(vertex) || this.tail.equals(vertex);
	}

	@Override
	public V getOtherEndpoint(V vertex) {
		if (this.head.equals(vertex)) {
			return this.tail;
		} else if(this.tail.equals(vertex)) {
			return this.head;
		}else {
			return null;
		}
	}

}

package annas.graph;


@SuppressWarnings("serial")
public class IntegerEdge implements EdgeInterface<Integer> {

	private Integer head;

	private Integer tail;

	public IntegerEdge() {
		super();
	}

	@Override
	public String toString() {
		return this.tail + "->" + this.head;
	}

	@Override
	public Integer getHead() {
		return head;
	}

	@Override
	public void setHead(Integer head) {
		this.head = head;
	}

	@Override
	public Integer getTail() {
		return tail;
	}

	@Override
	public void setTail(Integer tail) {
		this.tail = tail;
	}

	@Override
	public boolean isIncident(Integer vertex) {
		return this.head.equals(vertex) || this.tail.equals(vertex);
	}

	@Override
	public Integer getOtherEndpoint(Integer vertex) {
		if (this.head.equals(vertex)) {
			return this.tail;
		} else if(this.tail.equals(vertex)) {
			return this.head;
		}else {
			return null;
		}
	}

}

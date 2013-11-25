package annas.graph.generate;

import annas.graph.VertexFactory;

public class DefaultVertexFactory implements VertexFactory<Integer> {

	private int count;

	public DefaultVertexFactory() {
		super();
		this.count = -1;
	}

	@Override
	public Integer createVertex() {
		this.count++;
		return Integer.valueOf(count);
	}

}

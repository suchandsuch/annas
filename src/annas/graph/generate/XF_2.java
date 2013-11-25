package annas.graph.generate;

import java.util.Map;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.VertexFactory;

/**
 * See Graph classes for description of class <a
 * href="http://www.graphclasses.org/smallgraphs.html#families_XF">Graph
 * classes</a>
 * 
 * @author Sam
 * 
 * @param <N>
 * @param <A>
 */
public class XF_2<N, A extends EdgeInterface<N>> implements
		GraphGenerator<N, EdgeInterface<N>> {

	private int n;

	public XF_2(int n) {
		super();

	}

	@Override
	public void generateGraph(GraphInterface<N, EdgeInterface<N>> target,
			VertexFactory<N> factory, Map<String, ?> Additionalinfo) {
		N fan = factory.createVertex();
		target.addVertex(fan);
		N pend1 = factory.createVertex();
		N pend2 = factory.createVertex();
		N pend3 = factory.createVertex();
		target.addVertex(pend1);
		target.addVertex(pend2);
		target.addVertex(pend3);

		target.addEdge(pend3, fan);

		N newnode = factory.createVertex();
		N tmp;
		target.addVertex(newnode);
		target.addEdge(pend1, newnode);

		tmp = newnode;
		for (int i = 0; i < this.n - 1; i++) {
			newnode = factory.createVertex();
			target.addVertex(newnode);
			target.addEdge(tmp, newnode);
			target.addEdge(fan, newnode);
			tmp = newnode;
		}

		target.addEdge(tmp, pend2);

	}

}

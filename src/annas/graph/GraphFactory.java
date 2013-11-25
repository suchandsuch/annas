package annas.graph;

public class GraphFactory {

	public static <V, E extends EdgeInterface<V>,C extends GraphInterface<V,E>> C getGraphByClass(Class<C> c) {

		try {
			return c.newInstance();
		} catch (Exception ex) {
			throw new RuntimeException("Cant get new instance of graph type", ex);
		}
	}

}

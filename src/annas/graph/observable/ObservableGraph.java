/**
 * 
 */
package annas.graph.observable;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.GraphObserver;

/**
 * Provides a way for observing a graph.
 * 
 * @author scsswi
 */
public class ObservableGraph<V, E extends EdgeInterface<V>> extends
		DynamicProxy {

	private ObservableGraph(GraphInterface<V, E> obj) {
		super(obj);
	}

	/**
	 * Returns a graph that is the observable form of the graph provided as an argument.
	 * For a method to be seen by the observe the method must be called on the returned graph not
	 * the graph provided as the argument.
	 * @param graph
	 * @return an observable graph of the provided graph
	 */
	@SuppressWarnings("unchecked")
	public static <V, E extends EdgeInterface<V>> GraphInterface<V, E> getObservableGraph(
			GraphInterface<V, E> graph) {
		return (GraphInterface<V, E>) Proxy.newProxyInstance(graph.getClass()
				.getClassLoader(), new Class[] { GraphInterface.class },
				new ObservableGraph<V, E>(graph));
	}

	@Override
	protected void beforeMethod(Object graph, Method m,
			Object[] args) {
		return;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void afterMethod(Object graph, Method m,
			Object[] args, Object retval) {
		GraphObserver<V, E> go = ((GraphInterface<V, E>) graph).getObserver();
		if (go != null) {

			switch (m.getName()) {

			case "addEdge":
				go.edgeAdded((GraphInterface<V, E>) graph, args);
				break;
			case "addVertex":
				go.vertexAdded((GraphInterface<V, E>) graph, args);
				break;
			case "removeEdge":
				go.edgeRemoved((GraphInterface<V, E>) graph, args);
				break;
			case "removeVertex":
				go.vertexRemoved((GraphInterface<V, E>) graph, args);
				break;

			}
		}
	}

}

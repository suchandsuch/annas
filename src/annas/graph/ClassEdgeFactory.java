package annas.graph;

@SuppressWarnings("serial")
public class ClassEdgeFactory<V, E extends EdgeInterface<V>> implements
		EdgeFactory<V, E> {

	private Class<? extends E> edgeClass;

	public ClassEdgeFactory(Class<? extends E> edgeClass) {
		this.edgeClass = edgeClass;
	}

	@Override
	public E create(V source, V target) {
		try {
			return edgeClass.newInstance();
		} catch (Exception ex) {
			throw new RuntimeException("Cant get new instance of edge type", ex);
		}
	}
	
	@Override
	public Class<?> getEdgeClass(){
		return this.edgeClass;
	}
}
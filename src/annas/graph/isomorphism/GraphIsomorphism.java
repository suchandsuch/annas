/**
 * 
 */
package annas.graph.isomorphism;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;
import annas.graph.isomorphism.precondition.Precondition;

/**
 * @author Sam Wilson
 *
 */
public abstract class GraphIsomorphism<V, E extends EdgeInterface<V>> {
	
	private List<Precondition> precons;

	/**
	 * 
	 */
	public GraphIsomorphism() {
		super();
		this.precons = new LinkedList<Precondition>();
	}
	
	/**
	 * Tests if two graphs are isomorphic
	 * @param g1
	 * @param g2
	 * @return True if the two graphs are isomorphic, false otherwise.
	 */
	public boolean isIsomorphic(GraphInterface<V, E> g1, GraphInterface<V, E> g2){
		return this.getBijection(g1, g2) != null;
	}
	
	/**
	 * Gets a Bijection between the two graphs if one exists.
	 * @param g1
	 * @param g2
	 * @return A mapping between vertices of g1 and g2. returns null if know such map can be found.
	 */
	public Map<V,V> getBijection(GraphInterface<V, E> g1, GraphInterface<V, E> g2){
		if(this.evaluatePreconitions(g1, g2)){
			return this.find_Isomorphism(g1, g2);
		}
		return null;
	}
	
	/**
	 * Adds a precondition two be tested before the graph isomorphism algorithm runs.
	 * @param p
	 * @return true if the precondition was successfully added
	 */
	public boolean addPrecondition(Precondition p){
		return this.precons.add(p);
	}
	
	/**
	 * Evaluates the preconditions in the order they appear in the list.
	 * @param g1
	 * @param g2
	 * @return True if all preconditions pass, false otherwise.
	 */
	protected boolean evaluatePreconitions(GraphInterface<V, E> g1, GraphInterface<V, E> g2){
		for(Precondition p : precons){
			if(!p.evaluate(g1, g2)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method does the "hard" work. Should be implemented by extending classes.
	 * @param g1
	 * @param g2
 	 * @return A mapping between vertices of g1 and g2. returns null if know such map can be found.
	 */
	protected abstract Map<V,V> find_Isomorphism(GraphInterface<V, E> g1, GraphInterface<V, E> g2);

}

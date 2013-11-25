package annas.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class DisjointSet<V> {
	
	private final HashMap<V,Identifier<V>> objectToSetIdentifiers = new HashMap<V,Identifier<V>>();
	
	public DisjointSet(Set<V> vs){
		super();
		for(V v : vs){
			this.makeSet(v);
		}
	}

	
	public V findSet(V o) {
		DisjointSet.Identifier<V> node = objectToSetIdentifiers.get(o);
		if (node == null)
			return null;
		if (o != node.parent)
			node.setParent(findSet(node.parent));
		return node.getParent();
	}

	/**
	 * Adds a new set to the group of disjoint sets for the given object.
	 * It is assumed that the object does not yet belong to any set.
	 * @param o The object to add to the set
	 */
	public void makeSet(V o) {
		objectToSetIdentifiers.put(o, new Identifier<V>(o, 0));
	}

	/**
	 * Removes all elements belonging to the set of the given object.
	 * @param o The object to remove
	 */
	public void removeSet(V o) {
		V set = findSet(o);
		if (set == null)
			return;
		for (Iterator<V> it = objectToSetIdentifiers.keySet().iterator(); it.hasNext();) {
			V next = it.next();
			//remove the set representative last, otherwise findSet will fail
			if (next != set && findSet(next) == set)
				it.remove();
		}
		objectToSetIdentifiers.remove(set);
	}

	/**
	 * Unions the set represented by token x with the set represented by 
	 * token y. Has no effect if either x or y is not in the disjoint set, or
	 * if they already belong to the same set.
	 * @param x The first set to union
	 * @param y The second set to union
	 */
	public void union(V x, V y) {
		V setX = findSet(x);
		V setY = findSet(y);
		if (setX == null || setY == null || setX == setY)
			return;
		Identifier<V> nodeX = objectToSetIdentifiers.get(setX);
		Identifier<V> nodeY = objectToSetIdentifiers.get(setY);
		
	
		if (nodeX.getRank() > nodeY.getRank()) {
			nodeY.setParent(x);
		} else {
			nodeX.setParent(y);;
			if (nodeX.getRank() == nodeY.getRank())
				nodeY.setRank(nodeY.getRank()+1);
		}
	}
	
	/**
	 * A Identifier in the disjoint set forest.  Each tree in the forest is
	 * a disjoint set, where the root of the tree is the set identifier.
	 */
	private static class Identifier<V> {
		
		/**
		 * Used for optimization
		 */
		 private int rank;
		 
		/**
		 * Identifier of the disjoint set
		 */
		private V parent;

		Identifier(V parent, int rank) {
			this.parent = parent;
			this.rank = rank;
		}

		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public V getParent() {
			return parent;
		}

		public void setParent(V parent) {
			this.parent = parent;
		}
		
	}
}
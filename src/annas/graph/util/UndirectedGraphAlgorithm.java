/*******************************************************************************
 * Copyright (c) 2013 scsswi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     scsswi - initial API and implementation
 ******************************************************************************/
package annas.graph.util;

import annas.graph.EdgeInterface;
import annas.graph.GraphInterface;

/**
 * All algorithms that operate on undirected graphs should extend this class.
 * 
 * @author scsswi
 * 
 * @param <V>
 *            Vertex type
 * @param <E>
 *            Edge type
 */
public class UndirectedGraphAlgorithm<V, E extends EdgeInterface<V>> extends
		Algorithm<V, E> {

	public UndirectedGraphAlgorithm(GraphInterface<V, E> graph) {
		super();
		if (graph.isDirected()) {
			throw new IllegalArgumentException(
					"Algorithm incompatible with type " + graph.getClass()
							+ " derived from DirectedGraph");
		}
	}

}

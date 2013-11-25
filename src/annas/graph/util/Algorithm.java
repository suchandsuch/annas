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

/**
 * Class for all algorithms to extend. This provides the base functionality to
 * determine if an algorithm is suitable for use with directed or undirected
 * graphs.
 * 
 * @author scsswi
 * 
 * @param <V>
 *            Vertex type
 * @param <E>
 *            Edge type
 */
public class Algorithm<V, E extends EdgeInterface<V>> {

	public Algorithm() {
	}

}

package annas.math.combinatorics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * 
 * Adapted from <a
 * href="http://code.google.com/p/combinatoricslib/">combinatoricslib</a>
 * 
 * @author Dmytro Paukov
 * @author Sam Wilson
 * @see PermutationIterator
 * @param <T>
 *            Type of the elements in the permutations
 */
public class PermutationGenerator<T> implements Iterable<List<T>> {

	/**
	 * Initial vector
	 */
	protected final List<T> originalVector;

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Vector which is used for permutation generation
	 */
	public PermutationGenerator(Collection<T> originalVector) {
		this.originalVector = new ArrayList<T>(originalVector);
	}

	/**
	 * Returns core permutation
	 * 
	 */
	public List<T> getOriginalVector() {
		return this.originalVector;
	}

	/**
	 * Returns the number of all generated permutations
	 * 
	 */
	public long getNumberOfGeneratedObjects() {
		if (this.originalVector.size() == 0)
			return 0;

		return CombinatoricUtil.factorial(this.originalVector.size());
	}

	/**
	 * Creates an iterator
	 * 
	 */
	@Override
	public Iterator<List<T>> iterator() {
		return new PermutationIterator<T>(this);

	}

}

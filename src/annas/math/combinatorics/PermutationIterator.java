package annas.math.combinatorics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Iterator for the permutation generator
 * 
 * Adapted from <a
 * href="http://code.google.com/p/combinatoricslib/">combinatoricslib</a>
 * 
 * @author Dmytro Paukov
 * @author Sam Wilson
 * @see PermutationGenerator
 * @param <T>
 *            Type of elements in the permutations
 */
public class PermutationIterator<T> implements Iterator<List<T>> {

	/**
	 * Generator
	 */
	protected final PermutationGenerator<T> generator;

	/**
	 * Current permutation
	 */
	protected List<T> currentPermutation;

	/**
	 * Current index of current permutation
	 */
	protected long currentIndex = 0;

	/**
	 * Number of elements in the permutations
	 */
	protected final int length;

	/**
	 * Internal data
	 */
	private int[] pZ = null;
	private int[] pP = null;
	private int[] pD = null;
	private int m = 0;
	private int w = 0;
	private int pm = 0;
	private int dm = 0;
	private int zpm = 0;

	/**
	 * Constructor
	 * 
	 * @param generator
	 *            Permutation generator
	 */
	public PermutationIterator(PermutationGenerator<T> generator) {
		this.generator = generator;
		this.length = generator.getOriginalVector().size();
		this.currentPermutation = new ArrayList<T>(
				generator.getOriginalVector());
		this.pZ = new int[length + 2];
		this.pP = new int[length + 2];
		this.pD = new int[length + 2];
		init();
	}

	/**
	 * Initialize the iteration process
	 */
	private void init() {
		this.currentIndex = 0;

		this.m = 0;
		this.w = 0;
		this.pm = 0;
		this.dm = 0;
		this.zpm = 0;

		for (int i = 1; i <= this.length; i++) {
			this.pP[i] = i;
			this.pZ[i] = i;
			this.pD[i] = -1;
		}
		this.pD[1] = 0;
		this.pZ[this.length + 1] = m = this.length + 1;
		this.pZ[0] = this.pZ[this.length + 1];

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return this.m != 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#next()
	 */
	@Override
	public List<T> next() {
		if (!this.hasNext())
			throw new NoSuchElementException();
		for (int i = 1; i <= this.length; i++) {
			int index = this.pZ[i] - 1;
			this.currentPermutation.set(i - 1, this.generator
					.getOriginalVector().get(index));
		}
		this.m = this.length;
		while (this.pZ[this.pP[this.m] + this.pD[this.m]] > this.m) {
			this.pD[this.m] = -this.pD[this.m];
			this.m--;
		}
		this.pm = this.pP[this.m];
		this.dm = this.pm + this.pD[this.m];
		this.w = this.pZ[this.pm];
		this.pZ[this.pm] = this.pZ[this.dm];
		this.pZ[this.dm] = this.w;
		this.zpm = this.pZ[this.pm];
		this.w = this.pP[this.zpm];
		this.pP[this.zpm] = this.pm;
		this.pP[this.m] = this.w;
		this.currentIndex++;

		return new ArrayList<T>(this.currentPermutation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException("Method not supported");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PermutationIterator [currentIndex=" + currentIndex
				+ ", currentPermutation=" + currentPermutation + "]";
	}

}

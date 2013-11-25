package annas.util;

/**
 * This class provides an equivalent method to the equals method. For use with
 * ArraySet.
 * 
 * @author Sam
 * 
 * @param <T>
 */
public interface EqualityChecker<T> {

	public boolean check(Object a, Object b);
}

/**
 * 
 */
package interfaces;

/**
 * @author $hadow$torm
 * @param <E>
 *
 */
public interface Copyable<E> {
	
	/**
	 * Returns a copy of the object such that a reference to the callee object cannot reach the returned value.
	 * @return a copy of the object such that a reference to the callee object cannot reach the returned value.
	 */
	public E copy();

}

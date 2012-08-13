/**
 * 
 */
package towers;

import interfaces.CLInteraction;
import interfaces.Copyable;
import interfaces.HasSize;

import java.util.Stack;


/**
 * @author $hadow$torm
 *
 */
public class Tower<E extends HasSize & Copyable<? super E> & CLInteraction & Comparable<? super E>>
	implements HasSize, CLInteraction {
	private int maxSize;
	private Stack<E> stack;
	
	/**
	 * Creates a new tower with a capacity of max. Max must be greater than 0 because a tower cannot be forced to have
	 * nothing on it.
	 * @param max > 0.
	 */
	public Tower(int max) {
		if (max < 1) 
			throw new IllegalArgumentException("Integer max must b greater 0. A Tower cannot have a max of 0.");
		maxSize = max;
		stack = new Stack<E>();
	}
	
	/**
	 * Tries to push the item onto the tower if possible. The item is copied to prevent the object from being tampered 
	 * with after the push. Compares the item to the top most item in the tower, if item is less than the top of the tower, 
	 * successfully pushs the item onto the stack and returns true. Otherwise, if there is no more room on the tower, 
	 * (size() == max), or the item is greater than the top of the tower, then the item is not pushed onto the tower and
	 * returns false.
	 * @param item
	 * @return true if the item was successful pushed onto the stack, otherwise false.
	 */
	@SuppressWarnings("unchecked")
	public boolean push(E  item) {
		if ((size() == 0)) {
			if (item.size() <= maxSize)
				return item == stack.push((E) item.copy());
			return false;
		}
		if ((item.compareTo(stack.peek()) < 0)) {
			if (stack.size() < maxSize)
				return item == stack.push((E) item.copy());
		}
		return false;
	}
	
	/**
	 * Returns a copy of the object at the top of the tower. If the tower is empty, returns null.
	 * @return A copy of the top element of the tower. If there is no element, returns null.
	 */
	@SuppressWarnings("unchecked")
	public E peek() {
		if (stack.isEmpty())
			return null;
		return (E) stack.peek().copy();
		
	}
	
	/**
	 * Returns the top element of the tower, if there is no element, returns null.
	 * @return the top element of the tower, if there is no element, returns null.
	 */
	public E pop() {
		if (stack.isEmpty())
			return null;
		return stack.pop();
	}
	
	/**
	 * Empties the tower to have a size of 0 again, nothing is returned, the values are thrown away.
	 */
	public void clear() {
		stack.clear();
	}
	
	/**
	 * Returns the current size of the tower.
	 * @return the current size of the tower.
	 */
	public int size() {
		return stack.size();
	}
	
	@Override
	public String cliDraw() {
		StringBuilder base = new StringBuilder();
		StringBuilder tower= new StringBuilder();
		int middle = (maxSize + 1);
		for (int i=0; i<maxSize + 2; i++) {
			base.append("mm");
		}
		
		for (int i=0; i<(maxSize+1 - size()); i++) {
			for (int j=0; j<middle; j++){
				tower.append(" ");	
			}
			tower.append("||\n");
		}
		
		
		@SuppressWarnings("unchecked")
		Stack<E> s = (Stack<E>) stack.clone();
		
		for (int i=0; i<size(); i++) {
			E temp = s.pop();
			for (int j=0; j<middle - (temp.size() - 1); j++){
				tower.append(" ");	
			}
			tower.append(temp.cliDraw() + "\n");
		}
		
		tower.append(base.toString());
		return tower.toString();
	}
	
	/**
	 * Returns a String of the class name, with the field names and their values.
	 */
	@Override
	public String toString() {
		return "Tower[size=" + size() +", maxSize="+ maxSize + "]";
	}
}

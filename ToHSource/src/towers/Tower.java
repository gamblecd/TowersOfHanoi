/**
 * 
 */
package towers;

import interfaces.CLInteraction;
import interfaces.HasSize;

import java.util.Stack;


/**
 * @author $hadow$torm
 *
 */
public class Tower<E extends HasSize & CLInteraction & Comparable<? super E>> 
	implements HasSize, CLInteraction {
	private int maxSize;
	private Stack<E> stack;
	
	public Tower(int max) {
		if (max < 1) 
			throw new IllegalArgumentException();
		maxSize = max;
		stack = new Stack<E>();
	}
	
	public boolean push(E  item) {
		if ((size() == 0)) {
			if (item.size() <= maxSize)
				return item == stack.push(item);
			return false;
		}
		if ((item.compareTo(stack.peek()) < 0)) {
			if (stack.size() < maxSize)
				return item == stack.push(item);
		}
		return false;
	}
	
	public E peek() {
		return stack.peek();
		
	}
	
	public E pop() {
		return stack.pop();
	}
	
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
	
	@Override
	public String toString() {
		return "Tower[size=" + size() +", maxSize="+ maxSize + "]";
	}
}

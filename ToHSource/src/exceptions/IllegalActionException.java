package exceptions;

public class IllegalActionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3840108090584705359L;

	/**
	 * Creates a new IllegalActionException
	 * 
	 * @param message
	 *            a message describing why the exception was created.
	 */
	public IllegalActionException(String message) {
		super(message);
	}
}

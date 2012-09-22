package abstracts;

import interfaces.Display;

public abstract class AbstractDisplay implements Display {
	protected String error;
	protected String message;

	public AbstractDisplay() {
		super();
	}

	@Override
	public void setPlayableError(String error) {
		this.error = error;
	}

	@Override
	public void setPlayableMessage(String message) {
		this.message = message;
	}

}
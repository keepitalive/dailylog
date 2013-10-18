package net.jetidea.forge.dailylog;

public class GenericException extends Exception {

	private static final long serialVersionUID = 2271661383750531644L;

	public GenericException() {
		super();
	}

	public GenericException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenericException(String message) {
		super(message);
	}

	public GenericException(Throwable cause) {
		super(cause);
	}

}

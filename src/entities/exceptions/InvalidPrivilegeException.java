package entities.exceptions;

public class InvalidPrivilegeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  InvalidPrivilegeException() {
		
	}
	public InvalidPrivilegeException(String message) {
		super(message);
	}
}
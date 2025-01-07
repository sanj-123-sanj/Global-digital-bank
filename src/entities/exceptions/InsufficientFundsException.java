package entities.exceptions;

public class InsufficientFundsException extends Exception {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public InsufficientFundsException() {
		
		
	}
	
	public InsufficientFundsException(String string) {
		
		super(string);
	}
}

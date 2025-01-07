package entities.exceptions;

public class PinNumberInvalidException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PinNumberInvalidException() {
		
		
	}
	
	public PinNumberInvalidException(String string) {
		
		super(string);
	}
}

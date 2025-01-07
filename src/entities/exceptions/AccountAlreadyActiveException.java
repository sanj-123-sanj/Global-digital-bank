package entities.exceptions;

public class AccountAlreadyActiveException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountAlreadyActiveException() {
		
		
	}
	
	public AccountAlreadyActiveException(String string) {
		
		super(string);
	}
  
	
	
}

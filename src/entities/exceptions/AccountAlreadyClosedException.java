package entities.exceptions;


public class AccountAlreadyClosedException extends Exception {

public AccountAlreadyClosedException() {
		
		
	}
	
	public AccountAlreadyClosedException(String string) {
		
		super(string);
	}
}

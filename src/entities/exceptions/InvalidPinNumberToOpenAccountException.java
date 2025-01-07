package entities.exceptions;

public class InvalidPinNumberToOpenAccountException extends Exception {
	public InvalidPinNumberToOpenAccountException(String message) {
		super(message);
	}
}

package business;

import java.time.LocalDate;
import java.time.Period;

import entities.accounts.*;
import entities.exceptions.*;


public class SavingsAccountImpl implements IAccountImpl {
	Savings savingsAccount;
	
	AccountManager accountManager = new AccountManager();

	
	
	public boolean open(Account account) throws  AccountAlreadyActiveException {
		boolean isOpened = false;
		savingsAccount = (Savings) account;
		if(savingsAccount.isActive()) 
			throw new AccountAlreadyActiveException("Account Already Active"); 
			
		try {
			
		checkAgeValidity(savingsAccount.getDateOfBirth());
		isOpened = true;
		String accNo=(Account.generateAccountNumber());
        System.out.println("Account is opened with account number: " + accNo);
        savingsAccount.setAccountNumber(accNo);
		}
		catch(AgeValidityException ex) {
			
			isOpened = false;  
		}

		if(isOpened) {
		
		isOpened = accountManager.activateAccount(savingsAccount);
		
		System.out.println("open status: "+savingsAccount.isActive());
		}
		
 
		return isOpened;
	}
	
	public boolean checkAgeValidity(LocalDate dateOfBirth) throws AgeValidityException {
	    boolean isValid = true;
	    
	    // Get the current date
	    LocalDate currentDate = LocalDate.now();
	    
	    // Calculate the period between the current date and the user's date of birth
	    Period period = Period.between(dateOfBirth, currentDate);
	    
	    // Check if the user is at least 18 years old
	    if (period.getYears() < 18) {
	        throw new AgeValidityException("Age less than 18 years.");
	    }
	    
	    // If the user is at least 18 years old, the age is valid
	    return isValid;
	}
	
//	boolean activateAccount( status) {
//		boolean isActivated = false;
//		
//		if(status) {
//			savingsAccount.setActive(status);
//			savingsAccount.setActivatedDate(LocalDate.now());
//			isActivated = true;
//		}
//		
//		return isActivated;
//	}
}

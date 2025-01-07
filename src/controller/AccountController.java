package controller;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import business.AccountManager;

import entities.accounts.*;
import entities.exceptions.*;

/**
 * Acts as a controller between the UI and the {@code AccountManager} to manage account-related operations.
 * Provides methods to open, close, withdraw from, deposit into, transfer between accounts, and check account balance.
 */
public class AccountController {

    // Instance of AccountManager to perform business operations
    private AccountManager accountManager;
    private Scanner scanner = new Scanner(System.in);
    /**
     * Constructor for AccountController. Initializes an instance of AccountManager.
     */
    public AccountController() {
        accountManager = new AccountManager();
    }

    /**
     * Opens a new account with the provided account details.
     *
     * @param account the {@code Account} object containing account details
     * @return true if the account is successfully opened, false otherwise
     * @throws InvalidAccountTypeException   if the account type is invalid
     * @throws AgeValidityException          if the account holder's age is invalid
     * @throws AccountAlreadyActiveException if the account is already active
     * @throws InvalidRepositoryTypeException 
     */
    public boolean open(Account account) throws InvalidAccountTypeException, AgeValidityException, AccountAlreadyActiveException, InvalidRepositoryTypeException {
        return accountManager.open(account);
    }

    /**
     * Closes an existing account by its account number.
     *
     * @param accountNumber the account number of the account to close
     * @return true if the account is successfully closed, false otherwise
     * @throws AccountAlreadyClosedException if the account is already closed
     */
    public boolean close(String accountNumber) throws AccountAlreadyClosedException {
        return accountManager.close(accountNumber);
    }

    /**
     * Withdraws a specified amount from the given account, verifying the PIN.
     *
     * @param withdrawAccountNumber the account number to withdraw from
     * @param withdrawAmount        the amount to withdraw
     * @param pinNumber             the account's PIN for verification
     * @return true if the withdrawal is successful, false otherwise
     * @throws AccountAlreadyClosedException if the account is closed
     * @throws InsufficientFundsException    if the account has insufficient funds
     * @throws PinNumberInvalidException     if the provided PIN is incorrect
     */
    public boolean withdraw(String withdrawAccountNumber, double withdrawAmount, int pinNumber) throws AccountAlreadyClosedException, InsufficientFundsException, PinNumberInvalidException {
        return accountManager.withdraw(withdrawAccountNumber, withdrawAmount, pinNumber);
    }

    /**
     * Deposits a specified amount into the given account, verifying the PIN.
     *
     * @param depositAccountNumber the account number to deposit into
     * @param depositAmount        the amount to deposit
     * @param pin                  the account's PIN for verification
     * @return true if the deposit is successful, false otherwise
     * @throws AccountAlreadyClosedException if the account is closed
     * @throws PinNumberInvalidException     if the provided PIN is incorrect
     */
    public boolean deposit(String depositAccountNumber, double depositAmount, int pin) throws AccountAlreadyClosedException, PinNumberInvalidException {
        return accountManager.deposit(depositAccountNumber, depositAmount, pin);
    }

    /**
     * Transfers a specified amount from one account to another using a specified transfer mode.
     *
     * @param fromAccount    the account number to transfer from
     * @param toAccount      the account number to transfer to
     * @param transferAmount the amount to transfer
     * @param transferMode   the mode of transfer (e.g., IMPS, NEFT)
     * @return true if the transfer is successful, false otherwise
     * @throws Exception if any error occurs during the transfer process
     */
    public boolean transfer(String fromAccount, String toAccount, double transferAmount, TransferMode transferMode) throws Exception {
        return accountManager.transfer(fromAccount, toAccount, transferAmount, transferMode);
    }

    /**
     * Checks the balance of the specified account, verifying the PIN.
     *
     * @param accountNo the account number to check
     * @param pinNumber the account's PIN for verification
     * @throws InvalidAccountTypeException   if the account type is invalid
     * @throws AccountAlreadyClosedException if the account is closed
     * @throws PinNumberInvalidException     if the provided PIN is incorrect
     */
    public void checkBalance(String accountNo, int pinNumber) throws InvalidAccountTypeException, AccountAlreadyClosedException, PinNumberInvalidException {
        accountManager.checkBalance(accountNo, pinNumber);
    }

    // Private getter and setter to manage AccountManager internally

    /**
     * Private getter for {@code accountManager}.
     * 
     * @return the current instance of {@code AccountManager}
     */
    private AccountManager getAccountManager() {
        return accountManager;
    }

    /**
     * Private setter for {@code accountManager}.
     * 
     * @param accountManager the {@code AccountManager} instance to set
     */
    private void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }
    
    
    
    
    //Validation mehtods for user's input
    public String checkAccountUserName(String name) throws InvalidNameException {
        // Check if name contains only alphabet characters
        if (!name.matches("^[a-zA-Z ]+$")) {
            throw new InvalidNameException("Invalid name! Name must contain only alphabet characters.");
        }
        return name; // Return the valid name
    }
	
	public int displayGenderOptions() {
		System.out.println("Select your gender:");
		System.out.println("1. Male");
		System.out.println("2. Female");
		System.out.println("3. Others");
		int genderChoice = scanner.nextInt();
		scanner.nextLine();
		return genderChoice;
	}
	
	public String selectGender(int genderChoice) throws InvalidGenderException {
		String gender;
		switch(genderChoice) {
		case 1: gender = "Male";
		break;
		case 2: gender = "Female";
		break;
		case 3: gender = "Others";
		break;
		default : throw new InvalidGenderException("Enter valid gender selection...");
		}
		return gender;
	}
	
	public double checkOpeningAmount(double amount) throws AmountLessThanMinimumAomuntException {
		// Here we are assuming minimum amount to open account is 3000
		if(amount>=3000)
		{
			return amount;
		}
		else {
			throw new AmountLessThanMinimumAomuntException("Opening balance less than minimum");
		}
	}
	
	
	public int displayPrivilegeOptions() {
	    System.out.println("Select user privilege you want to enroll into");
	    System.out.println("1. Premium");
	    System.out.println("2. Gold");
	    System.out.println("3. Silver");
	    try {
	        String input = scanner.nextLine();
	        if (input.isEmpty()) {
	            System.out.println("No input detected. Please enter a number between 1 and 3.");
	            return -1; // Indicate invalid input
	        }
	        int privilegeChoice = Integer.parseInt(input);
	        return privilegeChoice;
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid input. Please enter a valid number.");
	        return -1; // Indicate invalid input
	    }
	}

	
//	protected Privilege selectPrivilege(int privilegeChoice) {
//		Privilege privilege = Privilege.values()[privilegeChoice - 1];
//		return privilege;
//	}
	
	
	
	public Privilege selectPrivilege(int privilegeChoice) {
	    try {
	        if (privilegeChoice < 1 || privilegeChoice > Privilege.values().length) {
	            throw new IllegalArgumentException("Invalid privilege choice: " + privilegeChoice);
	        }
	        Privilege privilege = Privilege.values()[privilegeChoice - 1];
	        return privilege;
	    } catch (ArrayIndexOutOfBoundsException e) {
	        System.out.println("Error: The chosen privilege is out of range.");
	        return null; // Or handle it appropriately (e.g., return a default value or re-prompt the user)
	    } catch (IllegalArgumentException e) {
	        System.out.println(e.getMessage());
	        return null;
	    }
	}

	
	public int checkPinFormat(String pin) throws InvalidPinNumberToOpenAccountException {
		if((pin.length()==4) && pin.matches("[0-9]{4}")) {
			return Integer.parseInt(pin);
		}
		else {
			throw new InvalidPinNumberToOpenAccountException("Enter valid pin number of length 4 and pin containing only numeric");
		}
	}
	
	public LocalDate dobFormatter(String dobInput) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         return LocalDate.parse(dobInput, formatter);
	}
	
	public String checkUrlFormat(String url) throws InvalidUrlException {
        // Check if name contains only alphabet characters
		url.trim();
        if (url.matches("^www\\.[a-zA-Z0-9]+\\.(com|org|net|edu|gov)$")) {
        	return url; // Return the valid name
        }
        else {
        	throw new InvalidUrlException("Invalid URL entered! Name must contain only alphabet characters.");
        }
    }
	
    
	
	public String checkRegistrationNumber(String regNo) throws InvalidRegistrationNumberException {
        // Check if name contains only alphabet characters
        if (regNo.matches("^[a-zA-Z0-9]+$")) {
        	return regNo; // Return the valid name
        }
        throw new InvalidRegistrationNumberException("Invalid registration number!It must contain only alphanumeric characters.");
    }
    
}

package ui;

import java.time.LocalDate;



import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import controller.AccountController;
import entities.accounts.*;
import entities.exceptions.*;


/**
 * This class handles user interactions for account operations in the Bank Of Success Pvt Ltd.
 * It provides options to open, close, withdraw, deposit, transfer, and check balance for accounts.
 */


public class AccountUI {
	private AccountController accountController;
	//    AccountOpening openAccount = new ();

	public AccountUI() {
		accountController = new AccountController();
	}

	Scanner scanner = new Scanner(System.in);

	/**
	 * Starts the AccountUI, providing a menu for various account operations.
	 * @throws Exception if any error occurs during operation.
	 */

	public void Start() throws Exception {
		System.out.println("Welcome to Bank Of Success Pvt Ltd");
		System.out.println("Select operation to  perform:");
		System.out.println("1. Open");
		System.out.println("2. Close");
		System.out.println("3. Withdraw");
		System.out.println("4. Deposit");
		System.out.println("5. Transfer");
		System.out.println("6. Check Balance");
		
		//put this in do while to check
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.nextLine();

		switch(choice) {
		case "1": open();break;
		case "2": close();break;
		case "3": withdraw();break;
		case "4": deposit();break;
		case "5": transfer();break;
		case "6": checkBalance();break;
		default : System.out.println("Choose valid option from the given menu . . .");break;
		}

	}

	
	
	/**
	 * Checks the balance of an account.
	 * @throws InvalidAccountTypeException if the account type is invalid.
	 * @throws AccountAlreadyClosedException if the account is already closed.
	 * @throws PinNumberInvalidException if the entered PIN is invalid.
	 */
	private void checkBalance() throws InvalidAccountTypeException, AccountAlreadyClosedException, PinNumberInvalidException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Account Number: ");
		String accountNo = scanner.next();

		System.out.println("Enter PIN: ");
		int pinNumber = scanner.nextInt();

		accountController.checkBalance(accountNo,pinNumber);

	}

	
	/**
	 * Opens a new account (either Savings or Current) based on user input.
	 * @throws AccountAlreadyActiveException if the account is already active.
	 * @throws InvalidAccountTypeException if the account type is invalid.
	 * @throws AgeValidityException if the user's age is not valid for account opening.
	 * @throws InvalidRepositoryTypeException 
	 */
	public void open() throws AccountAlreadyActiveException, InvalidAccountTypeException, AgeValidityException, InvalidRepositoryTypeException {
		boolean isOpened = false;

		String choice = acceptUserAccountTypeChoice();

		if (choice.equals("1")) 
		{
			Account account = acceptSavingsAccountInfoFromUser();
			isOpened = accountController.open(account);
		} 
		else if (choice.equals("2")) 
		{
			Account account = acceptCurrentAccountInfoFromUser();
			isOpened = accountController.open(account);
			displayAccountOpenstatus(isOpened);
		}
		else {
			System.out.println("Invalid type ");
		}


	}
	
	/**
	 * Displays the status of the account opening operation.
	 * @param isOpened true if account was opened successfully, false otherwise.
	 */
	protected void displayAccountOpenstatus(boolean isOpened) {
		if (isOpened) {
			System.out.println("Account Opened Successfully");
		} else {
			System.out.println("Sorry, couldn't open the account");
		}
	}
	
	
	/**
	 * Prompts the user to select the type of account they want to open.
	 * @return A string representing the user's choice (1 for Savings, 2 for Current).
	 */
	protected String acceptUserAccountTypeChoice() {
		System.out.println("Which account do you want to open?:");
		System.out.println("1. Savings");
		System.out.println("2. Current");
		String choice = scanner.nextLine();
		if(choice == "")
			choice = scanner.nextLine();
		return choice;
	}
	


	
	

	
	
	/**
	 * Collects information from the user to create a new Savings account.
	 * @return A Savings account object populated with user-provided information.
	 */
	protected Account acceptSavingsAccountInfoFromUser() {
	    
		// 1. Getting name input
		String name = "";
	    do {
	        System.out.println("Enter Name: ");
	        name = scanner.nextLine();
	        try { 
	            name = accountController.checkAccountUserName(name);  // Check if the name is valid
	            break;  // Break the loop if the name is valid
	        } catch (InvalidNameException e) {
	            System.out.println("Invalid name! Name must contain only alphabet characters.");
	        }
	    } while (true);  // Keep asking until a valid name is provided

	    
	    // Getting gender input
	    String gender = "";
	    do {
	        try {
	            gender = accountController.selectGender(accountController.displayGenderOptions());
	            break;  // Break the loop if the gender selection is valid
	        } catch (InvalidGenderException e) {
	            System.out.println("Invalid gender selection. Please try again.");
	        }
	    } while (true);  // Keep asking until a valid gender is selected

	    
	    // Getting opening balance and checking minimum of >=3000
	    double amount = 0;
	    do {
	    	try {
	    	    System.out.print("Enter amount: ");
	    	     amount = scanner.nextDouble();
	    	     scanner.nextLine();// Consume the newline after reading the number

	    	    // Proceed with using the amount variable as needed
	    	} catch (InputMismatchException e) {
	    	    System.out.println("Invalid input. Please enter a valid numeric value.");
	    	    scanner.nextLine(); // Clear the invalid input from the buffer
	    	}
	        try {
	            amount = accountController.checkOpeningAmount(amount);  // Check if the amount is valid
	            break;  // Break the loop if the amount is valid
	        } catch (AmountLessThanMinimumAomuntException e) {
	            System.out.println("Opening balance must be at least 3000.");
	        }
	    } while (true);  // Keep asking until a valid amount is entered

	    
	    // Getting privilege input and validating
	    Privilege privilege = null;
	    do {
	        privilege = accountController.selectPrivilege(accountController.displayPrivilegeOptions());
	        if (privilege != null) {
	            break;  // Break if the privilege is valid
	        } 
//	        else {
//	            System.out.println("Invalid privilege. Please choose a valid option.");
//	        }
	    } while (true);

	    
	    // Getting PIN input and validating
	    int pin = 0;
	    do {
	        System.out.println("Enter the Pin No: ");
	        String spin = scanner.nextLine();
	        try {
	            pin = accountController.checkPinFormat(spin);  // Validate the pin format
	            break;  // Break if the pin is valid
	        } catch (InvalidPinNumberToOpenAccountException e) {
	            System.out.println("Invalid pin. It should be 4 digits.");
	        }
	    } while (true);  // Keep asking until a valid pin is entered

	    
	    // Getting DOB input and handling exception
	    LocalDate dateOfBirth = null;
	    do {
	        System.out.println("Enter Date of Birth (yyyy-MM-dd): ");
	        String dobInput = scanner.nextLine().trim();
	        try {
	            dateOfBirth = accountController.dobFormatter(dobInput);  // Validate DOB format
	            break;  // Break if DOB is valid
	        } catch (DateTimeParseException e) {
	            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
	        }
	    } while (true);  // Keep asking until a valid DOB is entered

	    
	    // Create and return the account object
	    Savings account = new Savings();
	    account.setName(name);
	    account.setGender(gender);
	    account.setDateOfBirth(dateOfBirth);
	    account.setPinNumber(pin);
	    account.setPrivilege(privilege);
	    account.setBalance(amount);

	    return account;
	}

	
	/**
	 * Collects information from the user to create a new Current account.
	 * @return A Current account object populated with user-provided information.
	 */
	protected Account acceptCurrentAccountInfoFromUser(){
		
		String companyName = "";
	    do {
	        System.out.println("Enter Name: ");
	        companyName = scanner.nextLine();
	        try { 
	        	companyName = accountController.checkAccountUserName(companyName);  // Check if the name is valid
	            break;  // Break the loop if the name is valid
	        } catch (InvalidNameException e) {
	            System.out.println("Invalid name! Name must contain only alphabet characters.");
	        }
	    } while (true);
		
	    
	    
		String websiteURL = "";
		do {
	        System.out.println("Enter Website URL: ");
	        websiteURL = scanner.nextLine();
	        try { 
	        	websiteURL = accountController.checkUrlFormat(websiteURL);  // Check if the url is valid
	            break;  // Break the loop if the name is valid
	        } catch (InvalidUrlException e) {
	            System.out.println("Invalid URL! Enter valid url Ex : 'www.companyname.com'");
	        }
	    } while (true);
		
		
		String registrationNumber = "";
		do {
			System.out.println("Enter Registration Number: ");
	        registrationNumber = scanner.nextLine();
	        try { 
	        	registrationNumber = accountController.checkRegistrationNumber(registrationNumber);  // Check if the url is valid
	            break;  // Break the loop if the name is valid
	        } catch (InvalidRegistrationNumberException e) {
				// TODO Auto-generated catch block
	            System.out.println("Invalid registration number!It must contain only alphanumeric characters.");
			}
	    } while (true);
		
		
		
		double amount = 0;
	    do {
	    	try {
	    	    System.out.println("Enter amount: ");
	    	     amount = scanner.nextDouble();
	    	     scanner.nextLine();// Consume the newline after reading the number

	    	    // Proceed with using the amount variable as needed
	    	} catch (InputMismatchException e) {
	    	    System.out.println("Invalid input. Please enter a valid numeric value.");
	    	    scanner.nextLine(); // Clear the invalid input from the buffer
	    	}
	        try {
	            amount = accountController.checkOpeningAmount(amount);  // Check if the amount is valid
	            break;  // Break the loop if the amount is valid
	        } catch (AmountLessThanMinimumAomuntException e) {
	            System.out.println("Opening balance must be at least 3000.");
	        }
	    } while (true);  // Keep asking until a valid amount is entered
		
		
		
		
		// Getting privilege input and validating
	    Privilege privilege = null;
	    do {
	        privilege = accountController.selectPrivilege(accountController.displayPrivilegeOptions());
	        if (privilege != null) {
	            break;  // Break if the privilege is valid
        } 
//	        else {
//	           System.out.println("Invalid privilege. Please choose a valid option.");
//	        }
	    } while (true);
	    
		
	    // Getting PIN input and validating
	    int pin = 0;
	    do {
	        System.out.println("Enter the Pin No: ");
	        String spin = scanner.next();
	        try {
	            pin = accountController.checkPinFormat(spin);  // Validate the pin format
	            break;  // Break if the pin is valid
	        } catch (InvalidPinNumberToOpenAccountException e) {
	            System.out.println("Invalid pin. It should be 4 digits.");
	        }
	    } while (true);  // Keep asking until a valid pin is entered

		
	    
		Current account = new Current();
		account.setPinNumber(pin);
		account.setPrivilege(privilege);
		account.setBalance(amount);
		account.setCompanyName(companyName);
		account.setWebsiteURL(websiteURL);
		account.setRegistrationNumber(registrationNumber);

		return account;
	}

	
	/**
	 * Closes an existing account based on user input for account number.
	 */
	public void close() {

		System.out.println("Which account do you want to close?:");
		String accountNo = scanner.nextLine();
		try {
			boolean isClosed = accountController.close(accountNo);
			if (isClosed) {
				System.out.println("Account closed successfully.");
			} else {
				System.out.println("Could not close account. Account number may be invalid or already closed.");
			}
		} catch (AccountAlreadyClosedException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}


	/**
	 * Withdraws a specified amount from an account if the PIN and balance requirements are met.
	 */
	public void withdraw() {

		System.out.println("Enter Account Number: ");
		String accountNo = scanner.next();
		
		System.out.println("Enter Amount to Withdraw: ");
		double amount = scanner.nextDouble();
		
		System.out.println("Enter PIN: ");
		int pinNumber = scanner.nextInt();
		scanner.nextLine();
		
		boolean isWithdrawn=false;
		try {
			isWithdrawn = accountController.withdraw(accountNo, amount, pinNumber);
		} catch (InsufficientFundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PinNumberInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AccountAlreadyClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isWithdrawn) {
			System.out.println("Withdrawal successful.");
		} else {
			System.out.println("Withdrawal failed. Please check your account status, PIN, or balance.");
		}
	}
	
	/**
	 * Deposits a specified amount into an account based on user input for account number and PIN.
	 */
	public void deposit() {

		System.out.println("Enter the account number for deposit:");
		String accountNo = scanner.next();  // Get the account number
		
		System.out.println("Enter the amount to deposit:");
		double amount = scanner.nextDouble();   // Get the deposit amount
		
		System.out.println("Enter pin for the account number "+accountNo);
		int pin = scanner.nextInt();
		// Call the controller's deposit method to perform the deposit
		try {
			boolean isDeposited = accountController.deposit(accountNo, amount, pin); // Call the deposit method in AccountController
			if (isDeposited) {
				System.out.println("Amount deposited successfully.");
			} else {
				System.out.println("Deposit failed. Account number may be invalid or account may be closed.");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); // Handle any exceptions that may arise (e.g., invalid account, insufficient funds)
		}
	}

	// Method to handle the transfer functionality
	
	/**
	 * Transfers a specified amount from one account to another based on transfer mode selected by user.
	 * @throws Exception if any error occurs during the transfer process.
	 */
	public void transfer() throws Exception {

		System.out.println("Enter source account number: ");
		String sourceAccountNo = scanner.next();

		System.out.println("Enter destination account number: ");
		String destinationAccountNo = scanner.next();

		System.out.println("Enter amount to transfer: ");
		double amount = scanner.nextDouble();

		// Assuming TransferMode is an enum, you can choose the mode of transfer
		System.out.println("Choose transfer mode: ");
		System.out.println("1. IMPS");
		System.out.println("2. NEFT");
		System.out.println("3. RTGS");
		int modeChoice = scanner.nextInt();
		TransferMode transferMode = TransferMode.values()[modeChoice - 1];  
		// Call AccountController to handle transfer
		boolean isTransferred = accountController.transfer(sourceAccountNo, destinationAccountNo, amount, transferMode);
		if (isTransferred) {
			System.out.println("Transfer successful.");
		} else {
			System.out.println("Transfer failed.");
		}	
	}
}



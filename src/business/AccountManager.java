package business;

import repository.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import entities.accounts.Account;
import entities.accounts.Transfer;
import entities.accounts.TransferMode;
import entities.exceptions.*;

/**
 * Manages operations on accounts, including opening, closing, activation, withdrawal,
 * deposit, transfer, and balance checking. Utilizes an AccountRepository to persist
 * account data.
 */
public class AccountManager {

    AccountRepositoryCollection accountRepositoryCollection = new AccountRepositoryCollection();

    /**
     * Opens a new account of the specified type.
     *
     * @param account the account object to open
     * @return true if the account is successfully opened, false otherwise
     * @throws InvalidAccountTypeException if the account type is invalid
     * @throws AccountAlreadyActiveException if the account is already active
     * @throws InvalidRepositoryTypeException 
     */
    
    
    
    
    /*
     * check the accountrepositoryfactory object creation
     * 
     * 
     * 
     * */
    public boolean open(Account account) throws InvalidAccountTypeException, AccountAlreadyActiveException, InvalidRepositoryTypeException {
        String accountType = account.getClass().getSimpleName();
        IAccountImpl accountImpl = AccountImplFactory.create(accountType);
        IAccountRepository jdbc = AccountRepositoryFactory.create("JDBC");
        IAccountRepository collection = AccountRepositoryFactory.create("Collection");
		
        
        boolean isOpened = accountImpl.open(account);
        if (isOpened) {
            
        		AccountRepositoryCollection collection1 = (AccountRepositoryCollection) collection;
        		if(collection1.storeForOpenAccount(account) == true) {
        			AccountRepositoryJDBC jdbc1 = (AccountRepositoryJDBC) jdbc;
        			jdbc1.storeForOpenAccount(account);
        		}
        		// write else part 
        		/*
        		 * 1. retrive data from db
        		 * 2. update query for balance updation 
        		 * 		1. deposit
        		 * 		2. withdraw
        		 * 3. update query to set closed date after account closing
        		 * 4. UID neeedded 
        		 * 
        		 * */
        		System.out.println("Account added to repository");
        		
        	}
        return isOpened;
        }
        
    

    /**
     * Closes an active account by setting its status to inactive and recording the close date.
     *
     * @param accountNumber the unique identifier of the account to be closed
     * @return true if the account is successfully closed, false otherwise
     * @throws AccountAlreadyClosedException if the account is already closed or doesn't exist
     */
    public boolean close(String accountNumber) throws AccountAlreadyClosedException {
        boolean isClosed = false;
        Account account = accountRepositoryCollection.getAccount(accountNumber);
        
        if (account == null || !account.isActive()) {
            throw new AccountAlreadyClosedException("Account Already Closed");
        }

        account.setActive(false);
        account.setClosedDate(LocalDate.now());
        isClosed = true;
        return isClosed;
    }

    /**
     * Activates a given account by setting it to active and recording the activation date.
     *
     * @param account the account object to activate
     * @return true if the account is successfully activated, false otherwise
     */
    public boolean activateAccount(Account account) {
        boolean isActive = true;
        account.setActive(isActive);
        account.setActivatedDate(LocalDate.now());
        return isActive;
    }

    /**
     * Withdraws a specified amount from an account if it is active and has sufficient funds.
     *
     * @param accountNumber the account number to withdraw from
     * @param amount the amount to withdraw
     * @param pinNumber the account's PIN number for validation
     * @return true if the withdrawal is successful, false otherwise
     * @throws AccountAlreadyClosedException if the account is inactive or closed
     * @throws InsufficientFundsException if the account has insufficient funds
     * @throws PinNumberInvalidException if the provided PIN number is incorrect
     */
    public boolean withdraw(String accountNumber, double amount, int pinNumber)
            throws AccountAlreadyClosedException, InsufficientFundsException, PinNumberInvalidException {
        Account account = accountRepositoryCollection.getAccount(accountNumber);
        boolean isWithdrawn = false;
        
        if (account == null || !account.isActive()) {
            throw new AccountAlreadyClosedException("Account Already Closed");
        }
        
        if (checkIfPinNumberIsValid(account, pinNumber)) {
            if (checkFundsAvailability(account, amount)) {
                if (withdrawnAmount(account, amount)) {
                    isWithdrawn = true;
                }
            }
        }
        return isWithdrawn;
    }

    /**
     * Deposits a specified amount into an account if it is active and PIN is valid.
     *
     * @param depositAccountNumber the account number to deposit into
     * @param amount the amount to deposit
     * @param pin the account's PIN number for validation
     * @return true if the deposit is successful, false otherwise
     * @throws AccountAlreadyClosedException if the account is inactive or closed
     * @throws PinNumberInvalidException if the provided PIN number is incorrect
     */
    public boolean deposit(String depositAccountNumber, double amount, int pin)
            throws AccountAlreadyClosedException, PinNumberInvalidException {
        Account account = accountRepositoryCollection.getAccount(depositAccountNumber);
        boolean isDeposited = false;
        
        if (account == null || !account.isActive()) {
            throw new AccountAlreadyClosedException("Account Already Closed");
        }
        
        if (checkIfPinNumberIsValid(account, pin)) {
            account.setBalance(account.getBalance() + amount);
            System.out.println("Account balance: " + account.getBalance());
            isDeposited = true;
        }
        return isDeposited;
    }

    /**
     * Transfers a specified amount from one account to another if both accounts are active
     * and the source account has sufficient funds.
     *
     * @param fromAccountNumber the source account number
     * @param toAccountNumber the destination account number
     * @param amount the amount to transfer
     * @param mode the transfer mode (e.g., NEFT, RTGS)
     * @return true if the transfer is successful, false otherwise
     * @throws Exception if an error occurs during transfer processing
     */
    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount, TransferMode mode)
            throws Exception {
        Account fromAccount = accountRepositoryCollection.getAccount(fromAccountNumber);
        Account toAccount = accountRepositoryCollection.getAccount(toAccountNumber);
        
        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Invalid account number(s)");
        }

        if (fromAccount.getBalance() < amount) {
            return false; // Insufficient funds
        }

        Transfer transfer = new Transfer(fromAccount, toAccount, amount, mode);
        return transfer1(transfer, fromAccountNumber, toAccountNumber);
    }
    public boolean transfer1(Transfer transfer, String fromAccountNumber, String toAccountNumber)
			throws InvalidPrivilegeException, AccountAlreadyClosedException, InsufficientFundsException,
			PinNumberInvalidException {
		// Initialization
		boolean isTransferred = false;
		// logic
		// Check if from account is active
		if (isAccountActive(transfer.getFromAccount())) {
	//			System.out.println(transfer.getFromAccount()+ " is active");
			// check if account is active
			if (isAccountActive(transfer.getToAccount())) {
	//				System.out.println(transfer.getToAccount()+ " is active");
				// check daily limit is not exceeded for from account
				if (checkDailyTransferLimit(transfer.getFromAccount(), transfer.getAmount())) {
					// withdraw from one account and transfer into another account
					if (withdraw(fromAccountNumber, transfer.getAmount(), transfer.getFromAccount().getPinNumber()))
						// Deposit into Account
						if (deposit(toAccountNumber, transfer.getAmount(), transfer.getToAccount().getPinNumber()))
							isTransferred = true;
				}
			}
		}
		// Store into log
		TransferLog log = new TransferLog();
		log.logTransfer(transfer);
	
		return isTransferred;
	}
    /**
     * Checks the account balance if the account is active and the PIN is valid.
     *
     * @param accountNo the account number to check balance for
     * @param pinNumber the account's PIN number for validation
     * @throws InvalidAccountTypeException if the account type is invalid
     * @throws AccountAlreadyClosedException if the account is inactive or closed
     * @throws PinNumberInvalidException if the provided PIN number is incorrect
     */
    public void checkBalance(String accountNo, int pinNumber)
            throws InvalidAccountTypeException, AccountAlreadyClosedException, PinNumberInvalidException {
        Account account = accountRepositoryCollection.getAccount(accountNo);
        
        if (account != null) {
            if (account.isActive() && checkIfPinNumberIsValid(account, pinNumber)) {
                System.out.println("The account balance is " + account.getBalance());
            } else {
                throw new AccountAlreadyClosedException();
            }
        } else {
            throw new InvalidAccountTypeException();
        }
    }


		
    // Private helper methods for internal validation and processing

    private boolean checkIfPinNumberIsValid(Account account, int pinNumber) throws PinNumberInvalidException {
        if (account.getPinNumber() != pinNumber) {
            throw new PinNumberInvalidException("Invalid Pin Number");
        }
        return true;
    }

    private boolean checkFundsAvailability(Account account, double amount) throws InsufficientFundsException {
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient Funds");
        }
        return true;
    }

    private boolean withdrawnAmount(Account account, double amount) {
        account.setBalance(account.getBalance() - amount);
        System.out.println("Remaining balance: " + account.getBalance());
        return true;
    }

    protected boolean isAccountActive(Account account) {
        return account.isActive();
    }

    private boolean checkDailyTransferLimit(Account account, double amountToTransfer) throws InvalidPrivilegeException {
        double dailyTransferLimit = AccountPrivilegesManager.getPrivilegeLimit(account.getPrivilege());
        TransferLog log = new TransferLog();
        List<Transfer> transfers = log.getTransfers(account);
        
        double transferDone = 0.0;
        if (transfers != null) {
            for (Transfer transfer : transfers) {
                transferDone += transfer.getAmount();
            }
        }

        return dailyTransferLimit > (transferDone + amountToTransfer);
    }
}




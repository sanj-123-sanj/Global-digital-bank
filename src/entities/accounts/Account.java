package entities.accounts;

import java.time.LocalDate;

import entities.accounts.Privilege;

public abstract class Account {
	
	private String accountNumber; // Instance-specific account number
	private static int accountCounter = 1000; // Counter for generating unique account numbers*/
	// Instance variables representing account details
	private String name;
	private Privilege privilege;
	private int pinNumber;
	private double balance;
	private boolean isActive;
	private LocalDate activatedDate;
	private LocalDate closedDate;

	public void setAccountNumber(String accountNumber) {
		this.accountNumber=accountNumber;
	}


	/**
	 * Gets the unique account number for this account.
	 *
	 * @return the account number as a String
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Gets the account holder's name.
	 *
	 * @return the name of the account holder
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the account holder's name.
	 *
	 * @param name the name to set for the account holder
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the privilege level associated with the account.
	 *
	 * @return the privilege level of the account
	 */
	public Privilege getPrivilege() {
		return privilege;
	}

	/**
	 * Sets the privilege level for the account.
	 *
	 * @param privilege the privilege level to set
	 */
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	/**
	 * Gets the PIN number for this account.
	 *
	 * @return the account PIN number
	 */
	public int getPinNumber() {
		return pinNumber;
	}

	/**
	 * Sets the PIN number for this account.
	 *
	 * @param pinNumber the PIN number to set for the account
	 */
	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}

	/**
	 * Gets the current balance of the account.
	 *
	 * @return the account balance as a double
	 */
	public double getBalance() {
		return this.balance;
	}

	/**
	 * Sets the current balance for the account.
	 *
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Checks if the account is active.
	 *
	 * @return true if the account is active, false otherwise
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Sets the active status of the account.
	 *
	 * @param isActive true to activate the account, false to deactivate
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets the activation date of the account.
	 *
	 * @return the date the account was activated
	 */
	public LocalDate getActivatedDate() {
		return activatedDate;
	}

	/**
	 * Sets the activation date for the account.
	 *
	 * @param activatedDate the date to set as activation date
	 */
	public void setActivatedDate(LocalDate activatedDate) {
		this.activatedDate = activatedDate;
	}

	/**
	 * Gets the date the account was closed.
	 *
	 * @return the account's closed date
	 */
	public LocalDate getClosedDate() {
		return closedDate;
	}

	/**
	 * Sets the date the account was closed.
	 *
	 * @param closedDate the date to set as the closed date
	 */
	public void setClosedDate(LocalDate closedDate) {
		this.closedDate = closedDate;
	}

	/**
	 * Generates a unique account number by incrementing the static account number.
	 *
	 * @return the newly generated account number as a String
	 */
	/* public static String generateAccountNumber() {
       // Parse the current account number to an integer, increment by 1, and return as a string
       int number = Integer.parseInt(accountNumber);
       number++;
      // this.setAccountNumber(String.valueOf(number));
       return String.valueOf(number);
   }*/


	public static String generateAccountNumber() {
		accountCounter++;
		return String.valueOf(accountCounter); // Convert counter to string for account number
	}
}

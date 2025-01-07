package entities.accounts;


import entities.accounts.Account;
import entities.accounts.TransferMode;

/**
 * The Transfer class represents a money transfer between two accounts.
 * <p>
 * It includes details about the source and destination accounts, the transfer amount,
 * and the transfer mode.
 * </p>
 */
public class Transfer {

	/**
	 * The account from which the amount is transferred.
	 */
	private Account fromAccount;

	/**
	 * The account to which the amount is transferred.
	 */
	private Account toAccount;

	/**
	 * The amount to be transferred.
	 */
	private double amount;

	/**
	 * The mode of transfer (e.g., ONLINE, ATM, etc.).
	 */
	private TransferMode transferMode;

	/**
	 * Constructs a new Transfer with specified details.
	 *
	 * @param fromAccount   The source account of the transfer.
	 * @param toAccount     The destination account of the transfer.
	 * @param amount        The amount to be transferred.
	 * @param transferMode  The mode of the transfer.
	 */
	public Transfer(Account fromAccount, Account toAccount, double amount, TransferMode transferMode) {
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.transferMode = transferMode;
	}

	/**
	 * Retrieves the source account of the transfer.
	 *
	 * @return the source account.
	 */
	public Account getFromAccount() {
		return fromAccount;
	}

	/**
	 * Sets the source account of the transfer.
	 *
	 * @param fromAccount the source account to set.
	 */
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	/**
	 * Retrieves the destination account of the transfer.
	 *
	 * @return the destination account.
	 */
	public Account getToAccount() {
		return toAccount;
	}

	/**
	 * Sets the destination account of the transfer.
	 *
	 * @param toAccount the destination account to set.
	 */
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}

	/**
	 * Retrieves the amount of the transfer.
	 *
	 * @return the transfer amount.
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Sets the amount of the transfer.
	 *
	 * @param amount the transfer amount to set.
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Retrieves the transfer mode.
	 *
	 * @return the transfer mode.
	 */
	public TransferMode getTransferMode() {
		return transferMode;
	}

	/**
	 * Sets the transfer mode.
	 *
	 * @param transferMode the transfer mode to set.
	 */
	public void setTransferMode(TransferMode transferMode) {
		this.transferMode = transferMode;
	}
}

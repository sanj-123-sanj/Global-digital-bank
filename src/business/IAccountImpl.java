package business;

import entities.exceptions.*;

import entities.accounts.*;

/**
 * Interface defining the basic operations for account implementations.
 */
public interface IAccountImpl {

    /**
     * Opens an account of a specific type if it meets the necessary conditions.
     *
     * @param account the account to be opened
     * @return true if the account is successfully opened, false otherwise
     * @throws InvalidAccountTypeException if the account type is invalid
     * @throws AccountAlreadyActiveException if the account is already active
     */
    boolean open(Account account) throws InvalidAccountTypeException, AccountAlreadyActiveException;

}

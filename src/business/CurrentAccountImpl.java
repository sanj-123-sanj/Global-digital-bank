package business;

import java.time.LocalDate;


import entities.accounts.Account;
import entities.accounts.Current;
import entities.exceptions.*;

/**
 * Implementation for handling operations specific to current accounts.
 */
public class CurrentAccountImpl implements IAccountImpl {

    private Current currentAccount;
    private AccountManager accountManager = new AccountManager();

    /**
     * Opens a current account if it is not already active and has a valid registration number.
     * 
     * @param account the current account to be opened
     * @return true if the account is successfully opened and activated, false otherwise
     * @throws AccountAlreadyActiveException if the account is already active
     */
    public boolean open(Account account) throws AccountAlreadyActiveException {
        boolean isOpened = false;
        boolean isValid = false;

        currentAccount = (Current) account;

        // Check if the account is already active
        if (currentAccount.isActive()) {
            throw new AccountAlreadyActiveException("Account Already Active");
        }

        try {
            // Validate the registration number of the current account
            isValid = checkRegistrationNumberValidity(currentAccount.getRegistrationNumber());
            isOpened = true;

            // Generate and set account number
            String accNo = Account.generateAccountNumber();
            System.out.println("Account is opened with account number: " + accNo);
            currentAccount.setAccountNumber(accNo);

        } catch (RegistrationNumberInvalidException ex) {
            isOpened = false;
        }

        // Activate account if opened successfully
        if (isOpened) {
            isOpened = accountManager.activateAccount(currentAccount);
        }
        System.out.println("Active status: " + currentAccount.isActive());

        // Return account open status
        return isOpened;
    }

    /**
     * Checks if the provided registration number is valid.
     * 
     * @param registrationNumber the registration number to be validated
     * @return true if the registration number is valid
     * @throws RegistrationNumberInvalidException if the registration number is null or invalid
     */
    private boolean checkRegistrationNumberValidity(String registrationNumber) throws RegistrationNumberInvalidException {
        boolean isValid = true;

        if (registrationNumber == null) {
            throw new RegistrationNumberInvalidException("Registration Number Not Valid");
        }

        return isValid;
    }
}

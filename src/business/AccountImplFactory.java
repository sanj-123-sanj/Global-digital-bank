package business;

import java.util.HashMap;
import java.util.Map;
import entities.exceptions.*;

/**
 * Factory class for creating instances of account implementations based on account type.
 * This class utilizes the Factory design pattern to provide different types of accounts,
 * such as Savings and Current accounts, by mapping each type to a corresponding implementation.
 */
public class AccountImplFactory {

    // A static map to hold the different account implementations by account type.
    private static final Map<String, IAccountImpl> accountImpls = new HashMap<>();

    // Static block to initialize and map account types to their respective implementations.
    static {
        accountImpls.put("Savings", new SavingsAccountImpl());
        accountImpls.put("Current", new CurrentAccountImpl());
    }

    /**
     * Creates an account implementation based on the specified account type.
     *
     * @param accountType the type of account to create, e.g., "Savings" or "Current".
     * @return an implementation of {@link IAccountImpl} corresponding to the provided account type.
     * @throws InvalidAccountTypeException if the provided account type is not recognized.
     */
    public static IAccountImpl create(String accountType) throws InvalidAccountTypeException {
        IAccountImpl accountImpl = accountImpls.get(accountType);
        if (accountImpl == null) {
            throw new InvalidAccountTypeException();
        }
        return accountImpl;
    }
}

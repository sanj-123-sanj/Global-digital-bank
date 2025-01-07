package repository;

import java.util.HashMap;
import java.util.Map;

import business.CurrentAccountImpl;
import business.IAccountImpl;
import business.SavingsAccountImpl;
import entities.exceptions.InvalidAccountTypeException;
import entities.exceptions.InvalidRepositoryTypeException;

public class AccountRepositoryFactory {
	// A static map to hold the different account implementations by account type.
    private static final Map<String, IAccountRepository> accountRepositories = new HashMap<>();

    // Static block to initialize and map account types to their respective implementations.
    static {
    	accountRepositories.put("Collection", new AccountRepositoryCollection());
    	accountRepositories.put("JDBC", new AccountRepositoryJDBC());
    }

    
    public static IAccountRepository create(String accountType) throws  InvalidRepositoryTypeException {
        IAccountRepository accountRepository = accountRepositories.get(accountType);
        if (accountRepository == null) {
            throw new InvalidRepositoryTypeException("Invalid repository object creation call");
        }
        return accountRepository;
    }
}

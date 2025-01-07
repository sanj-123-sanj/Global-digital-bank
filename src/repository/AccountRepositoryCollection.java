package repository;

import java.util.HashMap;
import java.util.Map;

import entities.accounts.Account;


public class AccountRepositoryCollection implements IAccountRepository{
	// private HashMap<String, Account> accounts;
		public HashMap<String, Account> accounts = new HashMap<>();
		
		public Account getAccount(String accountNumber) {
			// TODO Auto-generated method stub
			return accounts.get(accountNumber);
		}
		
		//Implementing interface method
		public boolean storeForOpenAccount(Account account) {
//			boolean accountAdded = false;
			if(accounts.putIfAbsent(account.getAccountNumber(), account) != null) {
				return true;
			}
			return false;
		}
		
		
}

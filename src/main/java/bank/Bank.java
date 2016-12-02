package bank;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Bank {
	private Map<AccountId, Account> accounts = new HashMap<>();
	private int lastUsedAccountId = 0;

	public AccountId openAccount(String customerName) {
		int newAccountId = lastUsedAccountId + 1;
		AccountId accountId = new AccountId(newAccountId);
		accounts.put(accountId, new Account(accountId, customerName));
		lastUsedAccountId = newAccountId;
		return accountId;
	}

	public Account getAccount(AccountId accountId) {
		if (!accounts.containsKey(accountId)) {
			throw new NoSuchElementException("unknown Account-Id");
		}
		return accounts.get(accountId);
	}

	public void closeAccount(AccountId accountId) {
		Account account = accounts.get(accountId);
		if (account.getBalance() > 0) {
			throw new IllegalStateException("balance must be zero");
		}
		accounts.remove(accountId);
	}
}

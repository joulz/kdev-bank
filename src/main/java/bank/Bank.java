package bank;

import java.util.HashMap;
import java.util.Map;

public class Bank {
	private Map<AccountId, Account> accounts = new HashMap<>();
	private int lastUsedAccountId = 0;
	
	public AccountId openAccount(String customerName) {
		if ((customerName == null) || (customerName.isEmpty())) {
			throw new IllegalArgumentException("customer name must be set");
		}
		int newAccountId = lastUsedAccountId + 1;
		AccountId accountId = new AccountId(newAccountId);
		accounts.put(accountId, new Account());
		lastUsedAccountId = newAccountId;
		return accountId;
	}

	public Account getAccount(AccountId accountId) {
		return accounts.get(accountId);
	}
}

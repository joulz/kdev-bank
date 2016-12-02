package bank;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Bank {
	private Map<AccountId, Account> accounts = new HashMap<>();
	private int lastUsedAccountId = 1;

	public AccountId openAccount(String customerName) {
		Account account = new Account(getNewAccountId(), customerName);
		addNewAccount(account);
		return account.getAccountId();
	}

	private Account addNewAccount(Account account) {
		return accounts.put(account.getAccountId(), account);
	}

	private AccountId getNewAccountId() {
		return new AccountId(lastUsedAccountId++);
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
	
	public String showAllAccounts(){
		AccountFormatter formatter = new AccountFormatter();
		return accounts.values().stream()
			.sorted((o1, o2) -> o1.getAccountId().compareTo(o2.getAccountId()))
			.map(formatter::formatAccountInformation)
			.collect(Collectors.joining("\n"));
	}

	public AccountId openAccount(String customerName, long initalBalance) {
		Account account = new Account(getNewAccountId(), customerName,
				initalBalance);
		addNewAccount(account);
		return account.getAccountId();
	}

	public long getTotalBalance() {
		long sum = 0l;
		for (Account account : accounts.values()) {
			sum += account.getBalance();
		}
		return sum;
	}
}

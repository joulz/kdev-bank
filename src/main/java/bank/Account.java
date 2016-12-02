package bank;

public class Account {
	private final AccountId accountId;
	private String name;
	private long balance = 0l;

	public Account(AccountId accountId, String name) {
		this.accountId = accountId;
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("invalid empty name");
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public AccountId getAccountId() {
		return accountId;
	}

	public long getBalance() {
		return balance;
	}
}

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
	
	public Account(AccountId accountId, String name, long balance) {
		this(accountId, name);
		deposit(balance);
	}
	
	public void deposit(long amount){
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must not be negative or zero");
		}
		this.balance += amount;
	}
	
	public void withdraw(long amount){
		if(amount <=  0){
			throw new IllegalArgumentException("amount must not be negative or zero");
		}
		if(amount > balance){
			throw new IllegalArgumentException("withdraw amount is higher than balance");
		}
		balance -= amount;
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

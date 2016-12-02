package bank;

import java.util.Objects;

public class AccountId implements Comparable<AccountId> {
	private final int id;

	public AccountId(int id) {
		if (id > 99999999) {
			throw new IllegalArgumentException(
					"account id has more than 8 digits");
		}
		if (id < 0) {
			throw new IllegalArgumentException(
					"account id must not be negative");
		}
		if (id == 0) {
			throw new IllegalArgumentException("account id must not be 0");
		}
		this.id = id;
	}

	public AccountId(String accountId) {
		this(Integer.parseInt(accountId));
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (!(other instanceof AccountId)) {
			return false;
		}

		return id == ((AccountId) other).id;
	}

	@Override
	public String toString() {
		return String.format("%08d", id);
	}

	public int compareTo(AccountId other) {
		return Integer.valueOf(this.id).compareTo(Integer.valueOf(other.id)); 
	}
}

package bank;

public class AccountId {
	final int id;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountId other = (AccountId) obj;
		if (id != other.id)
			return false;
		return true;
	}
}

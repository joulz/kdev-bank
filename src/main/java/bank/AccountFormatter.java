package bank;

public class AccountFormatter {
	public String formatAccountInformation(Account account) {
		return String.format("Id: %s; Name: %s; Balance: %3$.2f EUR", account.getAccountId().toString(), account.getName(), (float)account.getBalance() / 100);
	}
}

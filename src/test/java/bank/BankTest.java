package bank;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BankTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void opened_account_should_be_stored_in_the_bank() {
		Bank bank = new Bank();
		AccountId accountId = bank.openAccount("Customer Name");
		Account account = bank.getAccount(accountId);
		assertThat(account, is(not(nullValue())));
	}
	
	@Test
	public void multiple_opened_accounts_should_have_different_ids() {
		Bank bank = new Bank();
		AccountId accountId1 = bank.openAccount("Customer One");
		AccountId accountId2 = bank.openAccount("Customer Two");
		assertThat(accountId1, is(not(equalTo(accountId2))));
	}
	
	@Test
	public void customer_must_have_a_not_empty_name() {
		Bank bank = new Bank();
		expectedException.expect(IllegalArgumentException.class);
		bank.openAccount("");
	}
}

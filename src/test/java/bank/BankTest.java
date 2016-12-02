package bank;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BankTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private Bank bank;

	@Test
	public void opened_account_should_be_stored_in_the_bank() {
		AccountId accountId = bank.openAccount("Customer Name");
		Account account = bank.getAccount(accountId);
		assertThat(account, is(not(nullValue())));
		assertThat(account.getAccountId(), is(accountId));
		assertThat(account.getName(), is("Customer Name"));
	}

	@Test
	public void multiple_opened_accounts_should_have_different_ids() {
		AccountId accountId1 = bank.openAccount("Customer One");
		AccountId accountId2 = bank.openAccount("Customer Two");
		assertThat(accountId1, is(not(equalTo(accountId2))));
	}

	@Test
	public void customer_must_have_a_not_empty_name() {
		expectedException.expect(IllegalArgumentException.class);
		bank.openAccount("");
	}

	@Test
	public void accessing_account_by_unkownId_should_fail() {
		AccountId accountId = new AccountId(123);
		expectedException.expect(NoSuchElementException.class);
		bank.getAccount(accountId);
	}

	@Before
	public void setup() {
		bank = new Bank();
	}

	@Test
	public void a_closed_account_can_not_be_found_anymore() throws Exception {
		AccountId accountId = bank.openAccount("customer");
		bank.closeAccount(accountId);
		expectedException.expect(NoSuchElementException.class);
		bank.getAccount(accountId);
	}

	@Test
	public void account_can_only_by_closed_with_zero_balance() throws Exception {
		AccountId accountId = bank.openAccount("customer");
		Account account = bank.getAccount(accountId);
		account.deposit(1000l);
		expectedException.expect(IllegalStateException.class);
		bank.closeAccount(accountId);
	}
	
	@Test
	public void all_accounts_are_printed_out() throws Exception {
		bank.openAccount("customer1");
		bank.openAccount("customer2");
		String accountInformations = bank.ShowAllAccounts();
		assertThat(accountInformations, is(
				"Id: 00000001; Name: customer1; Balance: 0,00 EUR" + "\n"+
				"Id: 00000002; Name: customer2; Balance: 0,00 EUR"
				));
	}
	
	@Test
	public void new_bank_has_empty_account_information() throws Exception {
		String accountInformations = bank.ShowAllAccounts();
		assertThat(accountInformations, is(""));
	}
}

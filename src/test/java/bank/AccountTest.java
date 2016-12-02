package bank;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AccountTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void account_owner_must_not_be_empty() throws Exception {
		expectedException.expect(IllegalArgumentException.class);
		new Account(new AccountId(1), "");
	}

	@Test
	public void accounance_name_is_stored_in_account() throws Exception {
		Account account = new Account(new AccountId(1), "Jon Doe");
		assertThat(account.getName(), is("Jon Doe"));
	}

	@Test
	public void account_id_can_be_accessed_by_get_id() throws Exception {
		Account account = new Account(new AccountId(1), "Jon Doe");
		assertThat(account.getAccountId(), is(new AccountId(1)));
	}

	@Test
	public void a_new_account_has_balance_zero() throws Exception {
		Account account = new Account(new AccountId(1), "Jon Doe");
		assertThat(account.getBalance(), is(0l));
	}

	@Test
	public void initial_account_has_no_balance() {
		Account account = new Account(new AccountId(1), "Jon Doe");
		assertThat(account.getBalance(), is(0l));
	}

	@Test
	public void deposit_money_increases_balance() {
		Account account = new Account(new AccountId(1), "Jon Doe");
		account.deposit(100);
		assertThat(account.getBalance(), is(100l));
	}

	@Test
	public void deposit_negative_amount_fails() {
		Account account = new Account(new AccountId(1), "Jon Doe");
		expectedException.expect(IllegalArgumentException.class);
		account.deposit(-1);
	}
}

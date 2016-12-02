package bank;

import static org.hamcrest.CoreMatchers.is;
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
	public void account_owner_must_not_be_null() throws Exception {
		expectedException.expect(IllegalArgumentException.class);
		new Account(new AccountId(1), null);
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

	@Test
	public void deposit_zero_amount_fails() {
		Account account = new Account(new AccountId(1), "Jon Doe");
		expectedException.expect(IllegalArgumentException.class);
		account.deposit(0);
	}

	@Test
	public void withdraw_money_decreases_balance() {
		Account account = new Account(new AccountId(1), "Jon Doe");
		account.deposit(500);
		account.withdraw(100);
		assertThat(account.getBalance(), is(400l));
	}

	@Test
	public void withdraw_negative_amount_fails() {
		Account account = new Account(new AccountId(1), "Jon Doe");
		account.deposit(500);
		expectedException.expect(IllegalArgumentException.class);
		account.withdraw(-1);
	}

	@Test
	public void withdraw_more_than_balance_fails() {
		Account account = new Account(new AccountId(1), "Jon Doe");
		account.deposit(500);
		expectedException.expect(IllegalArgumentException.class);
		account.withdraw(501);
	}

	@Test
	public void withdraw_account_balance_succeeds() {
		Account account = new Account(new AccountId(1), "Jon Doe");
		account.deposit(500);
		account.withdraw(500);
		assertThat(account.getBalance(), is(0l));
	}

	@Test
	public void withdraw_zero_amount_fails() {
		Account account = new Account(new AccountId(1), "Jon Doe");
		account.deposit(500);
		expectedException.expect(IllegalArgumentException.class);
		account.withdraw(0);
	}

	@Test
	public void create_bank_account_with_initial_balance() {
		Account account = new Account(new AccountId(1), "Jon Doe", 500);
		assertThat(account.getBalance(), is(500l));
	}

	@Test
	public void create_bank_account_with_initial_negative_balance_fails() {
		expectedException.expect(IllegalArgumentException.class);
		new Account(new AccountId(1), "Jon Doe", -500);
	}

	@Test
	public void transferring_money_to_another_account_increases_receiver_balance()
			throws Exception {
		Account accountSource = new Account(new AccountId(1), "sender", 100l);
		Account accountSink = new Account(new AccountId(2), "receiver");

		accountSource.transferTo(50l, accountSink);

		assertThat(accountSink.getBalance(), is(50l));
	}

	@Test
	public void transferring_money_to_another_account_reduces_balance()
			throws Exception {
		Account accountSource = new Account(new AccountId(1), "sender", 100l);
		Account accountSink = new Account(new AccountId(2), "receiver");

		accountSource.transferTo(50l, accountSink);

		assertThat(accountSource.getBalance(), is(50l));
	}

	@Test
	public void only_positive_amounts_can_be_transferred() throws Exception {
		Account accountSource = new Account(new AccountId(1), "sender", 100l);
		Account accountSink = new Account(new AccountId(2), "receiver");

		expectedException.expect(IllegalArgumentException.class);
		accountSource.transferTo(-50l, accountSink);
	}

	@Test
	public void tranferring_more_than_balance_is_not_allowed() throws Exception {
		Account accountSource = new Account(new AccountId(1), "sender", 100l);
		Account accountSink = new Account(new AccountId(2), "receiver");

		expectedException.expect(IllegalArgumentException.class);
		accountSource.transferTo(-200l, accountSink);
	}
}

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
	public void initial_account_has_no_balance() {
		Account account = new Account();
		assertThat(account.getBalance(), is(0));
	}

	@Test
	public void deposit_money_increases_balance() {
		Account account = new Account();
		account.deposit(100);
		assertThat(account.getBalance(), is(100));
	}

	@Test
	public void deposit_negative_amount_fails() {
		Account account = new Account();
		expectedException.equals(IllegalArgumentException.class);
		account.deposit(-1);
	}
}

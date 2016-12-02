package bank;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AccountIdTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void accountId_must_not_have_more_than_8_digits() throws Exception {
		expectedException.expect(IllegalArgumentException.class);
		new AccountId(123456789);
	}

	@Test
	public void accountId_must_be_positive() throws Exception {
		expectedException.expect(IllegalArgumentException.class);
		new AccountId(-1234567);
	}

	@Test
	public void accountId_must_not_be_zero() throws Exception {
		expectedException.expect(IllegalArgumentException.class);
		new AccountId(0);
	}

	@Test
	public void two_objects_of_same_id_must_be_equal() throws Exception {
		AccountId accountId1 = new AccountId(1);
		AccountId accountId2 = new AccountId(1);

		assertThat(accountId1, is(equalTo(accountId2)));
	}

	@Test
	public void account_id_is_not_equal_to_null() throws Exception {
		AccountId accountId1 = new AccountId(1);

		assertThat(accountId1, is(not(equalTo(null))));
	}

	@Test
	public void account_id_is_not_equal_to_other_type() throws Exception {
		AccountId accountId1 = new AccountId(1);

		assertThat(accountId1, is(not(equalTo(new Integer(1)))));
	}

	@Test
	public void two_objects_of_different_ids_must_not_be_equal()
			throws Exception {
		AccountId accountId1 = new AccountId(1);
		AccountId accountId2 = new AccountId(2);

		assertThat(accountId1, is(not(equalTo(accountId2))));
	}

	@Test
	public void two_equal_account_ids_must_have_same_hash() throws Exception {
		AccountId accountId1 = new AccountId(123);
		AccountId accountId2 = new AccountId(123);

		assertThat(accountId1.hashCode(), is(accountId2.hashCode()));
	}

	@Test
	public void id_is_always_displayed_with_8_digits() throws Exception {
		AccountId accountId = new AccountId(123);
		assertThat(accountId.toString(), is("00000123"));
	}

	@Test
	public void id_created_by_extended_form_is_equal_to_numeric_form()
			throws Exception {
		AccountId accountId = new AccountId(123);
		AccountId accountIdExtened = new AccountId("00000123");
		assertThat(accountId, is(equalTo(accountIdExtened)));
	}
}

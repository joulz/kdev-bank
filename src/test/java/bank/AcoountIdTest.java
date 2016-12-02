package bank;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AcoountIdTest {
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
}

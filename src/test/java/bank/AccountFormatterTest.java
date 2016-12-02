package bank;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AccountFormatterTest {
	@Test
	public void formatter_outputs_all_information() {
		Account account = new Account(new AccountId(1), "Jon Doe", 505);
		AccountFormatter formatter = new AccountFormatter();
		assertThat(formatter.formatAccountInformation(account), 
				is("Id: 00000001; Name: Jon Doe; Balance: 5,05 EUR"));
	}

}

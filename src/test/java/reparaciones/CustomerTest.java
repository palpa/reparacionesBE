package reparaciones;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import reparaciones.domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
public abstract class CustomerTest {

	public static class CustomerBuilder {

		private static final Long CUSTOMER_ID = 1L;
		private static final String CUSTOMER_FIRST_NAME = "John";
		private static final String CUSTOMER_LAST_NAME = "Snow";
		private static final String CUSTOMER_ADDRESS = "Castle Black";

		@Test
		public void createACustomerWhenOnlyequiredAtributesGiven() {
			Customer createdCustomerWithAddress = Customer.newInstance(
					CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME)
					.build();

			assertEquals(CUSTOMER_ID, createdCustomerWithAddress.getId());
			assertEquals(CUSTOMER_FIRST_NAME,
					createdCustomerWithAddress.getFirstName());
			assertEquals(CUSTOMER_LAST_NAME,
					createdCustomerWithAddress.getLastName());
			assertNull(createdCustomerWithAddress.getAddress());
		}

		@Test
		public void createACustomerWhenRequiredAtributesAndAddressGiven() {
			Customer createdCustomerWithAddress = Customer.newInstance(
					CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME)
					.address(CUSTOMER_ADDRESS).build();

			//HamcrestAssertion
			assertThat(CUSTOMER_ID, is(equalTo(createdCustomerWithAddress.getId())));
		
			assertEquals(CUSTOMER_FIRST_NAME,
					createdCustomerWithAddress.getFirstName());
			assertEquals(CUSTOMER_LAST_NAME,
					createdCustomerWithAddress.getLastName());
			assertEquals(CUSTOMER_ADDRESS,
					createdCustomerWithAddress.getAddress());
		}
	}
}

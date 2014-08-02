package reparaciones;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;

import reparaciones.domain.Customer;

@RunWith(Enclosed.class)
public class CustomerTest {

	public static class CustomerBuilder {

		private static final String CUSTOMER_DNI = "33569857";
		private static final String CUSTOMER_FIRST_NAME = "John";
		private static final String CUSTOMER_LAST_NAME = "Snow";
		private static final String CUSTOMER_ADDRESS = "Castle Black";
		private Long customerId;

		@Before
		public void setUp() {
			this.customerId = Customer.getNextId();
		}

		@Test
		public void createACustomerWhenOnlyequiredAtributesGiven() {

			Customer createdCustomerWithAddress = Customer
					.getBuilder(
							CUSTOMER_DNI,
							CUSTOMER_FIRST_NAME,
							CUSTOMER_LAST_NAME)
					.build();

			assertEquals(customerId, createdCustomerWithAddress.getId());
			assertEquals(CUSTOMER_FIRST_NAME,
					createdCustomerWithAddress.getFirstName());
			assertEquals(CUSTOMER_LAST_NAME,
					createdCustomerWithAddress.getLastName());
			assertNull(createdCustomerWithAddress.getAddress());
		}

		@Test
		public void createACustomerWhenRequiredAtributesAndAddressGiven() {

			Customer createdCustomerWithAddress = Customer
					.getBuilder(
							CUSTOMER_DNI,
							CUSTOMER_FIRST_NAME,
							CUSTOMER_LAST_NAME)
					.address(CUSTOMER_ADDRESS)
					.build();

			// HamcrestAssertion
			assertThat(customerId,
					is(equalTo(createdCustomerWithAddress.getId())));

			assertEquals(CUSTOMER_FIRST_NAME,
					createdCustomerWithAddress.getFirstName());
			assertEquals(CUSTOMER_LAST_NAME,
					createdCustomerWithAddress.getLastName());
			assertEquals(CUSTOMER_ADDRESS,
					createdCustomerWithAddress.getAddress());
		}
	}

}

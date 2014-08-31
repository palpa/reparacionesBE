/**
 * 
 */
package reparaciones.e2e;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import reparaciones.domain.Customer;
import reparaciones.resources.CustomerResource;
import reparaciones.utils.E2eTestsBase;
import reparaciones.utils.RestfulHalClient;
import reparaciones.utils.RestfulHalClient.RestfulTraversalBuilder;

/**
 * @author damian.palpacelli
 *
 */
//@RunWith(Enclosed.class)
public abstract class ShopServiceGivenCustomerResource {

	private static final String CUSTOMERS_RESOURCE_RELATIONSHIP = "customers";
	private static final int NUMBER_OF_CUSTOMERS_ON_FIRST_PAGE = 10;

	private static class CustomerResources extends Resources<CustomerResource> {
	}

	private static RestfulTraversalBuilder traversalCustomerResource;

	private static RestfulTraversalBuilder getTraversalCustomerResource(
			RestfulHalClient shopResourceClient) {

		if (traversalCustomerResource == null)
		{
			traversalCustomerResource = shopResourceClient
					.follow(CUSTOMERS_RESOURCE_RELATIONSHIP);
		}

		return traversalCustomerResource;
	}

	public static class CreateNewCustomer extends E2eTestsBase {

		private static final String CUSTOMER_DNI = "10584325";
		private static final String CUSTOMER_FIRST_NAME = "John";
		private static final String CUSTOMER_LAST_NAME = "Doe";

		@Test
		public void returnResponseWithHttpStatusCodeCreated()
				throws Exception {

			Customer customer = Customer.getBuilder(
					CUSTOMER_DNI,
					CUSTOMER_FIRST_NAME,
					CUSTOMER_LAST_NAME
					).build();

			// CustomerResource customerResource = new
			// CustomerResourceAssembler().toResource(customer);

			ResponseEntity<Void> response = getTraversalCustomerResource(
					shopResourceClient)
					.post(customer, Void.class);

			assertThat(response.getStatusCode(),
					is(equalTo(HttpStatus.CREATED)));
			assertThat(response.getHeaders().getLocation(), is(notNullValue()));
		}
	}

	public static class NotEmptyCustomerList extends E2eTestsBase {

		private ResponseEntity<CustomerResources> response;

		@Before
		public void setUp() {
			response = getTraversalCustomerResource(shopResourceClient)
					.toEntity(CustomerResources.class);
		}

		@Test
		public void returnResponseWithHttpStatusCodeOK()
				throws Exception {

			assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
		}

		@Test
		public void returnXNumberOfCustomersForDefaultPageWhenWhenCustomersAreFoundAndParametersAreOmmited()
				throws Exception {

			CustomerResources customerResources = response.getBody();
			// System.out.println(customerResources.iterator().next().getFirstName());

			assertThat(customerResources.getContent().size(),
					is(equalTo(NUMBER_OF_CUSTOMERS_ON_FIRST_PAGE)));
		}
	}

	public static class EmptyCustomerList extends E2eTestsBase {

		@Test
		@Ignore
		public void returnResponseWithHttpStatusCodeNotFound()
				throws Exception {

			ResponseEntity<CustomerResources> response = getTraversalCustomerResource(
					shopResourceClient).toEntity(CustomerResources.class);

			assertThat(response.getStatusCode(),
					is(equalTo(HttpStatus.NOT_FOUND)));
		}
	}

}

package reparaciones;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import reparaciones.resources.CustomerResource;
import reparaciones.utils.RestfulHalClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
@DirtiesContext
public class CustomerRestResourceTest {

	@Value("${local.server.port}")
	private int port;

	private static final String CUSTOMERS_RESOURCE_RELATIONSHIP = "customers";

	private static final int NUMBER_OF_CUSTOMERS_ON_FIRST_PAGE = 10;

	private static class CustomerResources extends Resources<CustomerResource> {
	}

	private static ResponseEntity<CustomerResources> response;

	@Before
	public void setUp() {

		RestfulHalClient shopResourceClient = TestHelper
				.getRestfulClientOnApiRoot(this.port);

		response = shopResourceClient.toEntity(CUSTOMERS_RESOURCE_RELATIONSHIP,
				CustomerResources.class);
	}

	@Test
	public void getStatusNotFoundWhenNoCustomersAreFound()
			throws Exception {

		assertThat(response.getStatusCode(), is(equalTo(HttpStatus.NOT_FOUND)));
	}

	@Test
	public void getStatusOKWhenCustomersAreFound()
			throws Exception {

		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.OK)));
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

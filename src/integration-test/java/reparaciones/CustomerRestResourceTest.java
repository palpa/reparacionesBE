package reparaciones;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import reparaciones.resources.CustomerResource;
import reparaciones.utils.RestfulHalResource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
@DirtiesContext
public class CustomerRestResourceTest {

	private static final int NUMBER_OF_CUSTOMERS_ON_FIRST_PAGE = 10;

	@Value("${local.server.port}")
	private int port;
		
	@Test
	public void requestCustomerResourcesUsingMyHalClient() throws Exception {

		RestfulHalResource shopResourceClient = TestHelper.getShopRestResource(this.port);
		
		ParameterizedTypeReference<Resources<CustomerResource>> typeReference = new ParameterizedTypeReference<Resources<CustomerResource>>() {};
		Resources<CustomerResource> customerResources = shopResourceClient.follow(typeReference, "customers");

		//System.out.println(customerResources.iterator().next().getFirstName());
		
		assertThat(customerResources.getContent().size(), is(equalTo(NUMBER_OF_CUSTOMERS_ON_FIRST_PAGE)));
	}
	
	@Test
	public void requestCustomerResources() throws Exception {

		Traverson traverson = TestHelper.getShopRestResourceTraversonCLient(this.port);

		ParameterizedTypeReference<Resources<CustomerResource>> typeReference = new ParameterizedTypeReference<Resources<CustomerResource>>() {};
		Resources<CustomerResource> customerResources = traverson.follow("customers").toObject(typeReference);
		
		assertThat(customerResources.getContent().size(), is(equalTo(NUMBER_OF_CUSTOMERS_ON_FIRST_PAGE)));
	}
	
}

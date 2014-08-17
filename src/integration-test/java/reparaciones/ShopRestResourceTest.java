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
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.client.Traverson.TraversalBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import reparaciones.resources.ShopResource;
import reparaciones.utils.RestfulHalResource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
@DirtiesContext
public class ShopRestResourceTest {

	@Value("${shop.name}")
	String shopName;

	@Value("${local.server.port}")
	private int port;
		
	@Test
	public void requestShopResourceUsingMyHalClient() throws Exception {
		
		RestfulHalResource shopResourceClient = TestHelper.getShopRestResource(this.port);
		
		ParameterizedTypeReference<ShopResource> typeReference = new ParameterizedTypeReference<ShopResource>() {};
		ShopResource shopResource = shopResourceClient.follow(typeReference, "self");
		
		assertThat(shopResource.getName(), is(equalTo(this.shopName)));
		assertThat(shopResource.isApiRoot(), is(true));
	}
	
	@Test
	public void requestShopResourceAsResource() throws Exception {

		Traverson traverson = TestHelper.getShopRestResourceTraversonCLient(this.port);

		ParameterizedTypeReference<ShopResource> typeReference = new ParameterizedTypeReference<ShopResource>() {};
		ShopResource shopResource = traverson.follow("self").toObject(typeReference);

		assertThat(shopResource.getName(), is(equalTo(this.shopName)));
		assertThat(shopResource.isApiRoot(), is(true));
	}

	@Test
	public void requestShopResourceJsonPath() throws Exception {
		
		Traverson traverson = TestHelper.getShopRestResourceTraversonCLient(this.port);

		TraversalBuilder selfRel = traverson.follow("self");

		String shopName = selfRel.toObject("$.name");
		boolean apiRoot = selfRel.toObject("$.apiRoot");

		assertThat(shopName, is(equalTo(this.shopName)));
		assertThat(apiRoot, is(true));
	}


}

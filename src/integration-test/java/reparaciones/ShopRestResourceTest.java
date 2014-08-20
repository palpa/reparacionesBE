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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import reparaciones.resources.ShopResource;
import reparaciones.utils.RestfulHalClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
@DirtiesContext
public class ShopRestResourceTest {

	private static final String SHOP_RESOURCE_SELF_RELATIONSHIP = "self";

	@Value("${shop.name}")
	private String shopName;

	@Value("${local.server.port}")
	private int port;

	private static ResponseEntity<ShopResource> response;

	@Before
	public void setUp() {
		RestfulHalClient shopResourceClient = TestHelper
				.getRestfulClientOnApiRoot(this.port);

		response = shopResourceClient.follow(SHOP_RESOURCE_SELF_RELATIONSHIP)
				.toEntity(ShopResource.class);
	}

	@Test
	public void getStatusOKWhenRequestShopResource()
			throws Exception {

		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.OK)));
	}

	@Test
	public void beApiRootForThisShop() throws Exception {

		ShopResource shopResource = response.getBody();

		assertThat(shopResource.getName(), is(equalTo(this.shopName)));
		assertThat(shopResource.isApiRoot(), is(true));
	}

}

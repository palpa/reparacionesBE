package reparaciones;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import reparaciones.resources.ShopResource;
import reparaciones.utils.E2eTestsBase;

public class ShopServiceGivenShopRestResource extends E2eTestsBase {

	private static final String SHOP_RESOURCE_SELF_RELATIONSHIP = "self";

	@Value("${shop.name}")
	private String shopName;

	private ResponseEntity<ShopResource> response;

	@Before
	public void setUp() {

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

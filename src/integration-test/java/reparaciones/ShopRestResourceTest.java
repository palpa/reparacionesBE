package reparaciones;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.client.Traverson.TraversalBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
@DirtiesContext
public class ShopRestResourceTest {

	@Value("${local.server.port}")
	private int port;

	@Value("${shop.name}")
	private String configuredShopName;

	@Test
	public void requestShopResource() throws Exception {

		Traverson traverson = new Traverson(
				new URI("http://localhost:" + this.port + "/api"),
				MediaTypes.HAL_JSON);
		
		TraversalBuilder selfRel = traverson.follow("self");

		String shopName = selfRel.toObject("$.name");
		boolean apiRoot = selfRel.toObject("$.apiRoot");

		assertThat(shopName, is(equalTo(this.configuredShopName)));
		assertThat(apiRoot, is(true));
	}

}

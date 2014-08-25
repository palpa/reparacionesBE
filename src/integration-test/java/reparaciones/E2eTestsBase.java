package reparaciones;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import reparaciones.utils.RestfulHalClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
//@DirtiesContext
public abstract class E2eTestsBase {

	@Value("${local.server.port}")
	private int port;

	protected RestfulHalClient shopResourceClient;

	@Before
	public void setUp() {

		shopResourceClient = getRestfulClientOnApiRoot(this.port);
	}

	private RestfulHalClient getRestfulClientOnApiRoot(int port) {

		return RestfulHalClient.newInstance(getShopApiRootUUri(port));
	}

	private String getShopApiRootUUri(int port) {
		return "http://localhost:" + port + "/api";
	}
}

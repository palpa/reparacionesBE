package reparaciones.utils;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import reparaciones.Application;
import reparaciones.utils.RestfulHalClient;

@RunWith(SpringInstanceTestClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
// @DirtiesContext
public abstract class E2eTestsBase implements InstanceTestClassListener {

	@Value("${local.server.port}")
	private int port;

	protected static RestfulHalClient shopResourceClient;

	@Override
	public void beforeClassSetup() {

		shopResourceClient = getRestfulClientOnApiRoot(this.port);
	}

	@Override
	public void afterClassSetup() {
	}

	private RestfulHalClient getRestfulClientOnApiRoot(int port) {

		return RestfulHalClient.newInstance(getShopApiRootUUri(port));
	}

	private String getShopApiRootUUri(int port) {
		return "http://localhost:" + port + "/api";
	}
}

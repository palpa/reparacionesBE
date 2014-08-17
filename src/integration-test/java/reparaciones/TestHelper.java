package reparaciones;


import java.net.URI;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;

import reparaciones.utils.RestfulHalResource;


public class TestHelper {
	
	public static RestfulHalResource getShopRestResource(int port) {
		
		return RestfulHalResource.newInstance(getShopApiRootUUri(port));
	}
	
	public static Traverson getShopRestResourceTraversonCLient(int port) {

		Traverson traverson = new Traverson(
				URI.create(getShopApiRootUUri(port)),
				MediaTypes.HAL_JSON);
		return traverson;
	}
	
	private static String getShopApiRootUUri(int port) {
		return "http://localhost:" + port + "/api";
	}
}

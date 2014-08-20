package reparaciones;


import reparaciones.utils.RestfulHalClient;


public class TestHelper {
	
	public static RestfulHalClient getRestfulClientOnApiRoot(int port) {
		
		return RestfulHalClient.newInstance(getShopApiRootUUri(port));
	}
	
	private static String getShopApiRootUUri(int port) {
		return "http://localhost:" + port + "/api";
	}
}

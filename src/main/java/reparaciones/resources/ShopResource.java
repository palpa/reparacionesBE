package reparaciones.resources;

import org.springframework.hateoas.ResourceSupport;

public final class ShopResource extends ResourceSupport {

	private String name;
	private boolean apiRoot;

	public static ShopResource newInstance(String shopName) {
		
		ShopResource shopResource = new ShopResource();
		shopResource.setName(shopName);
		shopResource.setApiRoot(true);
		
		return shopResource;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isApiRoot() {
		return apiRoot;
	}

	public void setApiRoot(boolean apiRoot) {
		this.apiRoot = apiRoot;
	}

}

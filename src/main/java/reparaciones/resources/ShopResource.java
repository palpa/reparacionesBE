package reparaciones.resources;

import org.springframework.hateoas.ResourceSupport;

public final class ShopResource extends ResourceSupport {

	private final String name;
	private final boolean apiRoot = true;

	private ShopResource(String shopName) {
		super();
		this.name = shopName;
	}

	public String getName() {
		return name;
	}

	public boolean isApiRoot() {
		return apiRoot;
	}
	
	public static ShopResource newInstance (String shopName){
		return new ShopResource (shopName);
	}
}

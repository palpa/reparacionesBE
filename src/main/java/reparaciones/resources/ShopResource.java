package reparaciones.resources;

import org.springframework.hateoas.ResourceSupport;

public class ShopResource extends ResourceSupport {
	private String name;
	private boolean apiRoot;

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

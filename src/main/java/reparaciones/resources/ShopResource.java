package reparaciones.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

@Component
public final class ShopResource extends ResourceSupport {

	private final String name;
	private final boolean apiRoot = true;

	@Autowired
	private ShopResource(@Value("${shop.name}") String shopName) {
		super();
		this.name = shopName;
	}

	public String getName() {
		return name;
	}

	public boolean isApiRoot() {
		return apiRoot;
	}
}

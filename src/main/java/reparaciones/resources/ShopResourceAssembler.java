package reparaciones.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityLinks;
import org.springframework.stereotype.Component;

import reparaciones.controllers.ShopController;
import reparaciones.utils.RestfulPagedResourcesAssembler;

@Component
public class ShopResourceAssembler {

	@Autowired
	private EntityLinks entityLinks;

	@Value("${shop.name}")
	private String shopName;

	public ShopResource toResource() {

		ShopResource shopResource = ShopResource.newInstance(shopName);

		shopResource.add(linkTo(methodOn(ShopController.class).shop())
				.withSelfRel());

		shopResource.add(RestfulPagedResourcesAssembler
				.addPaginationTemplateToLink(entityLinks.linkToCollectionResource(
						CustomerResource.class).withRel("customers")));

		return shopResource;
	}
}
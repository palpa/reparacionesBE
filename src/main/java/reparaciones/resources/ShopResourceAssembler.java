package reparaciones.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import reparaciones.controllers.ShopController;
import reparaciones.domain.Customer;
import reparaciones.domain.Shop;

@Component
public class ShopResourceAssembler extends
		ResourceAssemblerSupport<Shop, ShopResource> {
	
	@Autowired EntityLinks entityLinks;

	public ShopResourceAssembler() {
		super(ShopController.class, ShopResource.class);
	}

	@Override
	public ShopResource toResource(Shop entity) {

		ShopResource resource = instantiateResource(entity);

		resource.setApiRoot(true);
		resource.setName("MyM");
		
		resource.add(linkTo(methodOn(ShopController.class).shop()).withSelfRel());
		
		resource.add(entityLinks.linkToCollectionResource(Customer.class).withRel("customers"));

		return resource;
	}
}
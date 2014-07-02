package reparaciones.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import reparaciones.resources.CustomerResource;
import reparaciones.resources.ShopResource;

@Controller
@RequestMapping(value = "/api")
public class ShopController {

	@Autowired
	EntityLinks entityLinks;

//	@Autowired
//	private ShopResource shopResource;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<ShopResource> shop() {

		ShopResource shopResource = ShopResource.newInstance("MyM");
		
		shopResource.add(linkTo(methodOn(ShopController.class).shop())
				.withSelfRel());
		shopResource.add(new Link(entityLinks.linkToCollectionResource(
				CustomerResource.class).getHref() + "{?offset,limit}", "customers"));

		return new ResponseEntity<ShopResource>(shopResource, HttpStatus.OK);
	}
}

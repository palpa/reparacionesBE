package reparaciones.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
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

	@Autowired
	private ShopResource shopResource;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<ShopResource> shop() {

		shopResource.add(linkTo(methodOn(ShopController.class).shop())
				.withSelfRel());
		shopResource.add(entityLinks.linkToCollectionResource(
				CustomerResource.class).withRel("customers"));

		return new ResponseEntity<ShopResource>(shopResource, HttpStatus.OK);
	}
}

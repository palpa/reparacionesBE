package reparaciones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import reparaciones.resources.ShopResource;
import reparaciones.resources.ShopResourceAssembler;

@Controller
@RequestMapping(value = "/api")
public class ShopController {

	@Autowired
	private ShopResourceAssembler shopResourceAssembler;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<ShopResource> shop() {

		return new ResponseEntity<ShopResource>(
				shopResourceAssembler.toResource(), HttpStatus.OK);
	}
}

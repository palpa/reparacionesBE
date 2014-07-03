package reparaciones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;

import reparaciones.resources.CustomerResource;
import reparaciones.services.CustomerResourcesService;
import reparaciones.utils.RestfulPageable;

@Controller
@ExposesResourceFor(CustomerResource.class)
@RequestMapping(value = "/api/customers")
public class CustomersController {

	@Autowired
	CustomerResourcesService customerResourcesService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Resources<CustomerResource>> showAllPaged(
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageOffset,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int pageLimit) {

		RestfulPageable pageable = RestfulPageable.newInstance()
				.offset(pageOffset).limit(pageLimit).build();

		Resources<CustomerResource> resources = customerResourcesService
				.getCustomerResources(pageable);

		return new ResponseEntity<Resources<CustomerResource>>(resources,
				HttpStatus.OK);
	}
}

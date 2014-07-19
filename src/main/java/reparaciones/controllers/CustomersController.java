package reparaciones.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createCustomer(
			@RequestBody CustomerResource customerResource) {

		System.out.println(customerResource);

		URI location = customerResourcesService
				.createCustomer(customerResource);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<Object>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {

		System.out.println("Trying to delete Customer ID: " + id);

		HttpStatus httpStatus = HttpStatus.NOT_FOUND;

		if (customerResourcesService.deleteCustomer(id))
			httpStatus = HttpStatus.NO_CONTENT;

		return new ResponseEntity<Object>(httpStatus);
	}
}

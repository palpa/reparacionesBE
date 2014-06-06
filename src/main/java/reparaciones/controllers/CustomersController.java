package reparaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;

import reparaciones.domain.Customer;
import reparaciones.domain.Shop;
import reparaciones.resources.CustomerResourceAssembler;
import reparaciones.resources.CustomerResource;

@Controller
@ExposesResourceFor(Customer.class)
@RequestMapping(value = "/api/customers", produces = "application/hal+json")
public class CustomersController {

	@Autowired
	private CustomerResourceAssembler customerResourceAssembler;

//	@Autowired
//	CustomerAccess customerAccess;
	
	@Autowired
	Shop shop;
	
	@RequestMapping(method = RequestMethod.GET)
	public HttpEntity<Resources<CustomerResource>> showAllPaged(
			@RequestParam(value = "offset", required = false, defaultValue = "1") long pageOffset,
			@RequestParam(value = "limit", required = false, defaultValue = "5") long pageLimit) {
				
		List<CustomerResource> resources = customerResourceAssembler
				.toResources(shop.getCustomers());
		
		return new HttpEntity<Resources<CustomerResource>>(new Resources<CustomerResource>(resources));
	}
	
//	@RequestMapping(method = RequestMethod.GET)
//	public HttpEntity<PagedCustomerResources> showAllPaged(
//			@RequestParam(value = "size", required = false, defaultValue = "5") long size,
//			@RequestParam(value = "page", required = false, defaultValue = "1") long page) {
//		Iterable<? extends Customer> customers = customerAccess.getCustomers();
//		List<CustomerResource> resources = customerResourceAssembler
//				.toResources(customers);
//		long totalElements = resources.size();
//		// long totalPages = totalElements / size;
//		PageMetadata pageMetadata = new PageMetadata(size, page,
//				totalElements);
//		PagedCustomerResources pagedResources = new PagedCustomerResources(
//				resources, pageMetadata);
//		System.out.println(pagedResources.getNextLink());
//		return new HttpEntity<PagedCustomerResources>(pagedResources);
//	}

	// @RequestMapping(value = "/test", method = RequestMethod.GET)
	// public HttpEntity<PagedCustomerResources>
	// showAllPaged2(PagedResourcesAssembler assembler) {
	// Iterable<? extends Customer> customers = customerAccess.getCustomers();
	// List<CustomerResource> resources =
	// customerResourceAssembler.toResources(customers);
	// long size = 5;
	// long number = 1;
	// long totalElements = resources.size();
	// long totalPages = totalElements / size;
	// PageMetadata pageMetadata = new PageMetadata(size, number, totalElements,
	// totalPages);
	// PagedCustomerResources pagedResources = new
	// PagedCustomerResources(resources, pageMetadata);
	// System.out.println(pagedResources.getNextLink());
	// return new HttpEntity<PagedCustomerResources>(pagedResources);
	// }
}

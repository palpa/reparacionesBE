package reparaciones.services.impl;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import reparaciones.domain.Customer;
import reparaciones.domain.Shop;
import reparaciones.resources.CustomerResource;
import reparaciones.resources.CustomerResourceAssembler;
import reparaciones.services.CustomerResourcesService;
import reparaciones.utils.RestfulPagedResourcesAssembler;
import reparaciones.utils.RestfulPage;
import reparaciones.utils.RestfulPageable;

@Service("customerResourcesService")
public class CustomerResourcesServiceImpl implements CustomerResourcesService {

	@Autowired
	Shop shop;

	@Autowired
	private CustomerResourceAssembler assembler;

	@Autowired
	private RestfulPagedResourcesAssembler pagedResourceAssembler;

	@Override
	public Resources<CustomerResource> getCustomerResources(
			RestfulPageable pageable) {

		RestfulPage<Customer> page = RestfulPage.createPage(
				shop.getCustomers(), pageable);

		return pagedResourceAssembler.toResource(page, assembler);
	}

	@Override
	public URI createCustomer(CustomerResource customerResource) {

		Customer customer = Customer
				.newInstance(customerResource.getFirstName(),
						customerResource.getLastName()).build();

		shop.addCustomer(customer);

		URI location = ServletUriComponentsBuilder.fromCurrentServletMapping()
				.path("/customers/{id}").build().expand(customer.getId()).toUri();

		return location;
	}

	@Override
	public boolean deleteCustomer(Long id) {

		return shop.removeCustomer(id);
	}

}

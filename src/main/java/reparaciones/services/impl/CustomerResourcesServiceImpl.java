package reparaciones.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import reparaciones.domain.Customer;
import reparaciones.domain.Shop;
import reparaciones.resources.CustomerResource;
import reparaciones.resources.CustomerResourceAssembler;
import reparaciones.resources.PagedResourcesAssembler;
import reparaciones.services.CustomerResourcesService;
import reparaciones.utils.RestfulPage;
import reparaciones.utils.RestfulPageable;

@Service("customerResourcesService")
public class CustomerResourcesServiceImpl implements CustomerResourcesService {

	@Autowired
	Shop shop;

	@Autowired
	private CustomerResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler pagedResourceAssembler;

	@Override
	public Resources<CustomerResource> getCustomerResources(RestfulPageable pageable) {

		RestfulPage<Customer> page = RestfulPage.createPage(
				shop.getCustomers(), pageable);

		return pagedResourceAssembler.toResource(page, assembler);
	}

}

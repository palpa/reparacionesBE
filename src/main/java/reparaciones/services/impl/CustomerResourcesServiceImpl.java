package reparaciones.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import reparaciones.domain.Shop;
import reparaciones.resources.CustomerResource;
import reparaciones.resources.CustomerResourceAssembler;
import reparaciones.services.CustomerResourcesService;
import reparaciones.utils.RestfulPageable;

@Service("customerResourcesService")
public class CustomerResourcesServiceImpl implements CustomerResourcesService {

	@Autowired
	Shop shop;

	@Autowired
	private CustomerResourceAssembler customerResourceAssembler;

	@Override
	public Resources<CustomerResource> getCustomers(RestfulPageable pageable) {

		return new Resources<CustomerResource>(
				customerResourceAssembler.toResources(shop.getCustomers()));
	}

}

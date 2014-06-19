package reparaciones.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import reparaciones.domain.Customer;
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
		
		List<List<Customer>> pages = Lists.partition(shop.getCustomers(), pageable.getLimit());

		return new Resources<CustomerResource>(
				customerResourceAssembler.toResources(pages.get(pageable.getOffset())));
	}

}

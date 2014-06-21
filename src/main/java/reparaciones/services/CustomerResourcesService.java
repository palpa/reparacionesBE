package reparaciones.services;

import org.springframework.hateoas.Resources;

import reparaciones.resources.CustomerResource;
import reparaciones.utils.RestfulPageable;

public interface CustomerResourcesService {

	public Resources<CustomerResource> getCustomerResources(RestfulPageable pageable);
}

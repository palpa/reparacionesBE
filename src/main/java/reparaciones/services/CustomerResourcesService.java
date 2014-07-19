package reparaciones.services;

import java.net.URI;

import org.springframework.hateoas.Resources;

import reparaciones.resources.CustomerResource;
import reparaciones.utils.RestfulPageable;

public interface CustomerResourcesService {

	public Resources<CustomerResource> getCustomerResources(RestfulPageable pageable);
	
	public URI createCustomer (CustomerResource customerResource);

	public boolean deleteCustomer(Long id);

	public CustomerResource getCustomer(Long id);
}

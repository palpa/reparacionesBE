package reparaciones.resources;


import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import reparaciones.controllers.CustomersController;
import reparaciones.domain.Customer;

@Component
public class CustomerResourceAssembler extends ResourceAssemblerSupport<Customer, CustomerResource> {
	
	public CustomerResourceAssembler() {
		super(CustomersController.class, CustomerResource.class);
	}

	@Override
	public CustomerResource toResource(Customer entity) {

		CustomerResource resource = createResourceWithId(entity.getId(), entity);
		
		resource.setFirstName(entity.getFirstName());
		resource.setLastName(entity.getLastName());
		
		//resource.add(linkTo(methodOn(CustomerController.class).customers()).withSelfRel());
		
		return resource;
	}
}




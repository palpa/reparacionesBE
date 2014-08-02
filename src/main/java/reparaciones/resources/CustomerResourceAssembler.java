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
		
		resource.setDni(entity.getDni());
		resource.setFirstName(entity.getFirstName());
		resource.setLastName(entity.getLastName());
		resource.setAddress(entity.getAddress());
		resource.setEMail(entity.getEMail());
		resource.setContactNumber(entity.getContactNumber());
		
		return resource;
	}
}




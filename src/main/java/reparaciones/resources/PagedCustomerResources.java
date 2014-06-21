package reparaciones.resources;
import org.springframework.hateoas.PagedResources;

public class PagedCustomerResources extends PagedResources<CustomerResource>{
	
	private final boolean customersRoot = true;

	public boolean isCustomersRoot() {
		return customersRoot;
	}
}

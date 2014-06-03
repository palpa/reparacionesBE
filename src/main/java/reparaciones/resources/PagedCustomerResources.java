package reparaciones.resources;

import java.util.Collection;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;

public class PagedCustomerResources extends PagedResources<CustomerResource>{
	private boolean customersRoot;

	public boolean isCustomersRoot() {
		return customersRoot;
	}

	public void setCustomersRoot(boolean customersRoot) {
		this.customersRoot = customersRoot;
	}

	public PagedCustomerResources() {
		super();
		// TODO Auto-generated constructor stub
		this.customersRoot = true;
	}

	public PagedCustomerResources(Collection<CustomerResource> content,
			org.springframework.hateoas.PagedResources.PageMetadata metadata,
			Iterable<Link> links) {
		super(content, metadata, links);
		// TODO Auto-generated constructor stub
		this.customersRoot = true;
	}

	public PagedCustomerResources(Collection<CustomerResource> content,
			org.springframework.hateoas.PagedResources.PageMetadata metadata,
			Link... links) {
		super(content, metadata, links);
		// TODO Auto-generated constructor stub
		this.customersRoot = true;
	}
	
	
}

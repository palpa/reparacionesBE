package reparaciones.resources;

import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;

public class PagedResourcesAssembler<T> implements ResourceAssembler<Resource<T>, PagedResources<Resource<T>>> {

	@Override
	public PagedResources<Resource<T>> toResource(Resource<T> entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

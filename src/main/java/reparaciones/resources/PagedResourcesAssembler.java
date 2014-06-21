package reparaciones.resources;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import reparaciones.utils.RestfulPage;
import reparaciones.utils.RestfulPageable;

@Component
public class PagedResourcesAssembler<T>
		implements
			ResourceAssembler<RestfulPage<T>, PagedResources<Resource<T>>> {

	@Override
	public PagedResources<Resource<T>> toResource(RestfulPage<T> entity) {
		//return createResource(entity, new SimplePagedResourceAssembler<T>());
		return null;
	}

	public <R extends ResourceSupport> PagedResources<R> toResource(
			RestfulPage<T> page, ResourceAssemblerSupport<T, R> assembler) {
		return createResource(page, assembler);
	}

	private <S, R extends ResourceSupport> PagedResources<R> createResource(
			RestfulPage<S> page, ResourceAssemblerSupport<S, R> assembler) {

		Assert.notNull(page, "Page must not be null!");
		Assert.notNull(assembler, "ResourceAssembler must not be null!");

		List<R> resources = assembler.toResources(page);

		PagedResources<R> pagedResources = new PagedResources<R>(resources,
				asPageMetadata(page));
		pagedResources.add(createLink(page.getPageable(), Link.REL_SELF));

		if (page.hasNext()) {
			pagedResources.add(createLink(page.nextPageable(), Link.REL_NEXT));
		}

		if (page.hasPrevious()) {
			pagedResources.add(createLink(page.previousPageable(),
					Link.REL_PREVIOUS));
		}

		return pagedResources;
	}

	private static <T> PageMetadata asPageMetadata(RestfulPage<T> page) {

		Assert.notNull(page, "Page must not be null!");
		return new PageMetadata(page.getSize(), page.getNumber(),
				page.getTotalElements());
	}

	private Link createLink(RestfulPageable pageable, String rel) {

		return new Link(pageable.getUri(), rel);
	}
	
//	private static class SimplePagedResourceAssembler<T> implements ResourceAssembler<T, Resource<T>> {
//
//		@Override
//		public Resource<T> toResource(T entity) {
//			return new Resource<T>(entity);
//		}
//		
//		public List<Resource<T>> toResources(Iterable<? extends T> entities) {
//
//			Assert.notNull(entities);
//			List<Resource<T>> result = new ArrayList<Resource<T>>();
//
//			for (T entity : entities) {
//				result.add(toResource(entity));
//			}
//
//			return result;
//		}
//	}
}

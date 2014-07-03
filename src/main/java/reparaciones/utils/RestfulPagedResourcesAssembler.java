package reparaciones.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class RestfulPagedResourcesAssembler {

	public <T, R extends ResourceSupport> PagedResources<R> toResource(
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

	private <T> PageMetadata asPageMetadata(RestfulPage<T> page) {

		Assert.notNull(page, "Page must not be null!");

		return new PageMetadata(page.getSize(), page.getNumber(),
				page.getTotalElements());
	}

	private Link createLink(RestfulPageable pageable, String rel) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("offset", pageable.getOffset());
		parameters.put("limit", pageable.getLimit());

		return addPaginationTemplateToLink(
				new Link(getHrefFromCurrentRequestUri(), rel)).expand(
				parameters);
	}

	private String getHrefFromCurrentRequestUri() {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().build()
				.toString();
	}

	public static Link addPaginationTemplateToLink(Link link) {
		return new Link(link.getHref() + "{?offset,limit}", link.getRel());
	}

}

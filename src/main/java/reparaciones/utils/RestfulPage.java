package reparaciones.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.Assert;

import com.google.common.collect.Lists;

public class RestfulPage<T> implements Iterable<T> {

	private final List<T> content = new ArrayList<T>();
	private final RestfulPageable pageable;
	private final long totalElements;

	private RestfulPage(List<T> content, RestfulPageable pageable, long totalElements) {
		this.content.addAll(content);
		this.pageable = pageable;
		this.totalElements = totalElements;
	}

	public int getNumber() {
		return (pageable.getOffset() + 1);
	}

	public int getSize() {
		return pageable.getLimit();
	}
	
	public int getNumberOfElements() {
		return content.size();
	}

	public long getTotalElements() {
		return totalElements;
	}
	
	public int getTotalPages() {
		return getSize() == 0 ? 1 : (int) Math.ceil((double) totalElements / (double) getSize());
	}


	public static <T> RestfulPage<T> createPage(List<T> elements,
			RestfulPageable pageable) {

		Assert.notNull(elements, "Content must not be null!");

		List<List<T>> pages = Lists.partition(elements, pageable.getLimit());

		return new RestfulPage<T>(pages.get(pageable.getOffset()), pageable, elements.size());
	}

	@Override
	public Iterator<T> iterator() {
		return content.iterator();
	}

	public boolean hasNext() {
		return getNumber() < getTotalPages();
	}

	public RestfulPageable getPageable() {
		return pageable;
	}

	public RestfulPageable nextPageable() {
		return pageable.newWithOffset(pageable.getOffset() + 1);
	}

	public boolean hasPrevious() {
		return getNumber() > 1;
	}

	public RestfulPageable previousPageable() {
		return pageable.newWithOffset(pageable.getOffset() - 1);
	}

}

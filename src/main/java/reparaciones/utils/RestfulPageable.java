package reparaciones.utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RestfulPageable {
	private final int offset;
	private final int limit;
	private final Map<String, String> filters;
	private final List<Sorting> sortings;

	private RestfulPageable(RestfulPageableBuilder builder) {
		this.offset = builder.offset;
		this.limit = builder.limit;
		this.filters = builder.filters;
		this.sortings = builder.sortings;
	}

	public int getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}

	public Map<String, String> getFilters() {
		return filters;
	}

	public List<Sorting> getSortings() {
		return sortings;
	}

	public static class RestfulPageableBuilder {
		private int offset = 0;
		private int limit = 10;
		private Map<String, String> filters = Collections.emptyMap();
		private List<Sorting> sortings = Collections.emptyList();

		public RestfulPageableBuilder offset(int offset) {
			this.offset = offset;
			return this;
		}

		public RestfulPageableBuilder limit(int limit) {
			this.limit = limit;
			return this;
		}

		public RestfulPageableBuilder filtersQueryString(String filtersQueryString) {
			this.filters = this.filtersQueryStringToMap(filtersQueryString);
			return this;
		}

		public RestfulPageableBuilder sortingsQueryString(String sortingsQueryString) {
			this.sortings = this.sortingsQueryStringToMap(sortingsQueryString);
			return this;
		}

		private List<Sorting> sortingsQueryStringToMap(
				String sortingsQueryString) {
			// TODO implement this method
			return Collections.emptyList();
		}

		private Map<String, String> filtersQueryStringToMap(
				String filtersQueryString) {
			// TODO implement this method
			return Collections.emptyMap();
		}

		public RestfulPageable build() {
			return new RestfulPageable(this);
		}

	}
	
	public static RestfulPageableBuilder newInstance() {
		return new RestfulPageableBuilder();
	}
	
	public final static class Sorting {
		private final Direction direction;
		private final String property;

		private Sorting(Direction direction, String property) {
			this.direction = direction;
			this.property = property;
		}

		public static Sorting newWithAscendantProperty(String property) {
			return new Sorting(Direction.ASCENDANT, property);
		}
		
		public static Sorting newWithDescendantProperty(String property) {
			return new Sorting(Direction.DESCENDANT, property);
		}

		public boolean isAscendant() {
			return this.direction == Direction.ASCENDANT;
		}

		public boolean isDescendant() {
			return this.direction == Direction.DESCENDANT;
		}

		public String getProperty() {
			return property;
		}

		private enum Direction {
			ASCENDANT, DESCENDANT
		}
	}

}

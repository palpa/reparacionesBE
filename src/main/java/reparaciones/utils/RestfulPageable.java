package reparaciones.utils;

public class RestfulPageable {
	private long offset;
	private long limit;
	private String[] filters;
	private String[] sortings;
	
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	public long getLimit() {
		return limit;
	}
	public void setLimit(long limit) {
		this.limit = limit;
	}
	public String[] getFilters() {
		return filters;
	}
	public void setFilters(String[] filters) {
		this.filters = filters;
	}
	public String[] getSortings() {
		return sortings;
	}
	public void setSortings(String[] sortings) {
		this.sortings = sortings;
	}
}

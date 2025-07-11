package paging;

public class PageRequest implements Pageable{
	
	private Integer page;
	private Integer limit;
	private Sort sort;
	
	public PageRequest(Integer page, Integer limit, Sort sort) {
		super();
		this.page = page;
		this.limit = limit;
		this.sort = sort;
	}

	@Override
	public Integer getPage() {
		return this.page;
	}

	@Override
	public Integer getOffset() {
		if (this.page != null && this.limit != null) {
			return (this.page - 1) * this.limit;
		}
		return null;
	}

	@Override
	public Integer getLimit() {
		return this.limit;
	}

	@Override
	public Sort getSort() {
		if (this.sort != null) {
			return this.sort;
		}
		return null;
	}

}

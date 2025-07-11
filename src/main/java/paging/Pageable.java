package paging;

public interface Pageable {
	Integer getPage();
	Integer getOffset();
	Integer getLimit();
	Sort getSort();
}

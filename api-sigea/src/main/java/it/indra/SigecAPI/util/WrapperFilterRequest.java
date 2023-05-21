package it.indra.SigecAPI.util;

public class WrapperFilterRequest<CustomFilter>{
	
	//private Integer draw;
	
	//private Integer start;
	
	//private Integer length;
	
	//private List<WrapperFilterOrder> order;
	
	//private WrapperFilterSearch search;
	
	private WrapperFilterPaginationDetail detail;
	
	private CustomFilter filter;

	public WrapperFilterPaginationDetail getDetail() {
		return detail;
	}

	public void setDetail(WrapperFilterPaginationDetail detail) {
		this.detail = detail;
	}

	public CustomFilter getFilter() {
		return filter;
	}

	public void setFilter(CustomFilter filter) {
		this.filter = filter;
	}

}

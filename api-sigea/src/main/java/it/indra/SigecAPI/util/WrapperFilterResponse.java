package it.indra.SigecAPI.util;

import java.util.ArrayList;
import java.util.List;

public class WrapperFilterResponse<Data> {
	
	private Integer draw;
	
	private Integer recordsTotal;
	
	private Integer recordsFiltered;

	private List<Data> data = new ArrayList<>();

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}
	
}

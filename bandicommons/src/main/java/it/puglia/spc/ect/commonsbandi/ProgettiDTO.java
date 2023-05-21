package it.puglia.spc.ect.commonsbandi;

import java.util.List;

public class ProgettiDTO {

	List<ProgettoDTO> items;

	public List<ProgettoDTO> getItems() {
		return items;
	}

	public void setItems(List<ProgettoDTO> items) {
		this.items = items;
	}

	@Override
	public String toString() {

		String stamp = "";
		if (items != null) {
			for (ProgettoDTO p : items) {
				stamp += p.toString() + " ";
			}
		}
		return stamp;
	}

}

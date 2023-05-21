package it.puglia.spc.ect.commonsbandi;

import java.util.List;

public class BandiDTO {

	List<BandoDTO> items;

	public List<BandoDTO> getItems() {
		return items;
	}

	public void setItems(List<BandoDTO> items) {
		this.items = items;
	}

	@Override
	public String toString() {

		String stamp = "";
		if (items != null) {
			for (BandoDTO p : items) {
				stamp += p.toString() + " ";
			}
		}
		return stamp;
	}

}

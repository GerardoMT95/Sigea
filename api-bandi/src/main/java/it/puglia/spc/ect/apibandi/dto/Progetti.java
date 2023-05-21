package it.puglia.spc.ect.apibandi.dto;

import java.util.List;

public class Progetti {

	List<Progetto> items;

	public List<Progetto> getItems() {
		return items;
	}

	public void setItems(List<Progetto> items) {
		this.items = items;
	}

	@Override
	public String toString() {

		String stamp = "";
		if (items != null) {
			for (Progetto p : items) {
				stamp += p.toString() + " ";
			}
		}
		return stamp;
	}

}

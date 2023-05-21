package it.puglia.spc.ect.apibandi.dto;

import java.util.List;

public class Bandi {

	List<Bando> items;

	public List<Bando> getItems() {
		return items;
	}

	public void setItems(List<Bando> items) {
		this.items = items;
	}

	@Override
	public String toString() {

		String stamp = "";
		if (items != null) {
			for (Bando p : items) {
				stamp += p.toString() + " ";
			}
		}
		return stamp;
	}

}

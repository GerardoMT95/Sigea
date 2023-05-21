package it.puglia.spc.ect.apibandi.dto;

public class Bando {

	String id_bando;
	String nome_bando;

	public String getId_bando() {
		return id_bando;
	}

	public void setId_bando(String id_bando) {
		this.id_bando = id_bando;
	}

	public String getNome_bando() {
		return nome_bando;
	}

	public void setNome_bando(String nome_bando) {
		this.nome_bando = nome_bando;
	}

	@Override
	public String toString() {
		return "Bando [id_bando=" + id_bando + ", nome_bando=" + nome_bando + "]";
	}

}

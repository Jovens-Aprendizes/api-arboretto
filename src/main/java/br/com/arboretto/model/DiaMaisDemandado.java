package br.com.arboretto.model;

import java.util.List;

public class DiaMaisDemandado {
    private String nomeDiaDaSemana;
    private int totalReservas;
    private List<String> datasMarcadas;

    public DiaMaisDemandado(String nomeDiaDaSemana, int totalReservas, List<String> datasMarcadas) {
        this.nomeDiaDaSemana = nomeDiaDaSemana;
        this.totalReservas = totalReservas;
        this.datasMarcadas = datasMarcadas;
    }

	public String getNomeDiaDaSemana() {
		return nomeDiaDaSemana;
	}

	public void setNomeDiaDaSemana(String nomeDiaDaSemana) {
		this.nomeDiaDaSemana = nomeDiaDaSemana;
	}

	public int getTotalReservas() {
		return totalReservas;
	}

	public void setTotalReservas(int totalReservas) {
		this.totalReservas = totalReservas;
	}

	public List<String> getDatasMarcadas() {
		return datasMarcadas;
	}

	public void setDatasMarcadas(List<String> datasMarcadas) {
		this.datasMarcadas = datasMarcadas;
	}

   
}

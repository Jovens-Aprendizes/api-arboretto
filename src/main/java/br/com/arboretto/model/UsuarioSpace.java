package br.com.arboretto.model;

public class UsuarioSpace {

	private String id;
	private String usuarioId;
	private String spaceId;
	private String dataMarcada;
	private String observacao;
	private Boolean status;
	private String autorizacao;
	private String nomeUsuario;
	private String nomeSpace;
	private String totalReservas;

	public UsuarioSpace() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}

	public String getDataMarcada() {
		return dataMarcada;
	}

	public void setDataMarcada(String dataMarcada) {
		this.dataMarcada = dataMarcada;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(String autorizacao) {
		this.autorizacao = autorizacao;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeSpace() {
		return nomeSpace;
	}

	public void setNomeSpace(String nomeSpace) {
		this.nomeSpace = nomeSpace;
	}

	public String getTotalReservas() {
		return totalReservas;
	}

	public void setTotalReservas(String totalReservas) {
		this.totalReservas = totalReservas;
	}

	
	
}

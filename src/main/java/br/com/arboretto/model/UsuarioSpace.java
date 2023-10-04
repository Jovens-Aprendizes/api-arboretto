package br.com.arboretto.model;

public class UsuarioSpace {
	
	private String id;
	private String usuarioId;
	private String spaceId;
	private String dataMarcada;
	private String observacao;
	private Boolean status;
	
	public UsuarioSpace () {
		
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

	
	
	
}

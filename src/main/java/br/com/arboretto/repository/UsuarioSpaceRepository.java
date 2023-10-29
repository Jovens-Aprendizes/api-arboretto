package br.com.arboretto.repository;


import java.util.List;


import br.com.arboretto.model.UsuarioSpace;

public interface UsuarioSpaceRepository {
	
	public long salvar(UsuarioSpace usuarioSpace);
	
	public UsuarioSpace getPorId(String id);
	
	public int atualizar(UsuarioSpace usuarioSpace);
	
	public List<UsuarioSpace> listarUsuarioSpace();
	
	public void delete(String id); 
	
	public List<UsuarioSpace> listarUsuarioSpacePorUsuarioId(String usuarioId);
	
	public List<UsuarioSpace> listarSpacePorId(String spaceId);
	
	 public List<String> obterDiasMaisDemandadosComDiaDaSemana(int mes, int ano);

}

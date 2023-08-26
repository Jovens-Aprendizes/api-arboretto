package br.com.arboretto.repository;

import java.util.List;

import br.com.arboretto.model.Usuario;

public interface UsuarioRepository {
	
	
	public long salvar(Usuario usuario);
	
	public Usuario getPorId(String id);
	
	public int atualizar (Usuario usuario);
	
	public String getLogin (String cpf,String senha);
	
	public List<Usuario> listarUsuario();
	
	public void delete(String id);
}

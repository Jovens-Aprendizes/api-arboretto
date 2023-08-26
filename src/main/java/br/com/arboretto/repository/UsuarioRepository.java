package br.com.arboretto.repository;

import br.com.arboretto.model.Usuario;

public interface UsuarioRepository {
	public long salvar(Usuario usuario);
	public Usuario getPorId(String id);
}
